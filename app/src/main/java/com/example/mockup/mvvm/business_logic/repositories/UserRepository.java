package com.example.mockup.mvvm.business_logic.repositories;

import com.example.mockup.mvvm.business_logic.dao.UserDAO;
import com.example.mockup.mvvm.business_logic.data.User;
import com.example.mockup.mvvm.business_logic.data.UserResponse;
import com.example.mockup.mvvm.business_logic.network.webservices.MobileWebService;
import com.example.mockup.utils.ConnectivityUtils;
import com.example.mockup.utils.containers.UserDataContainer;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserRepository implements IUserRepository {
    //network service
    private MobileWebService mobileWebService;
    //local database service
    private UserDAO userDAO;
    //disposable
    private CompositeDisposable disposable;
    //live data
    private MutableLiveData<User> userMutableLiveData;
    private MutableLiveData<Throwable> errorMutableLiveData;

    @Inject
    public UserRepository(MobileWebService mobileWebService, UserDAO userDAO, Disposable disposable) {
        this.mobileWebService = mobileWebService;
        this.userDAO = userDAO;
        this.disposable = (CompositeDisposable) disposable;
        userMutableLiveData = new MutableLiveData<>();
        errorMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void clear() {
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
        if (userMutableLiveData != null) userMutableLiveData = null;
        if (errorMutableLiveData != null) errorMutableLiveData = null;
    }

    @Override
    public User getValue() {
        return userMutableLiveData.getValue();
    }

    @Override
    public void setValue(User value) {
        userMutableLiveData.setValue(value);
    }

    @Override
    public MutableLiveData<Throwable> error() {
        return errorMutableLiveData;
    }

    @Override
    public MutableLiveData<User> findUser() {
        User user;
        if (UserDataContainer.isEmpty()) {
            user = userDAO.selectUser();
            System.out.println("User is " + user);
            UserDataContainer.setUser(user);
        }
        else user = UserDataContainer.getUser();
        System.out.println("User is " + user);
        userMutableLiveData.setValue(user);
        return userMutableLiveData;
    }

    @Override
    public MutableLiveData<User> saveUser(User user) {
        if (ConnectivityUtils.isInternetConnected()) disposePostUser(mobileWebService.postUser(user));
        return userMutableLiveData;
    }

    public MutableLiveData<User> saveToLocalStorage(User user) {
        userDAO.insert(user);
        userMutableLiveData.setValue(user);
        return userMutableLiveData;
    }

    private synchronized void disposePostUser(@NotNull Single<UserResponse> userResponseSingle) {
        disposable.add(userResponseSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserResponse>() {
                    @Override
                    public void onSuccess(UserResponse userResponse) {
                        errorMutableLiveData.setValue(null);
                        if (userResponse.isCreate()) saveToLocalStorage(userResponse.getUser());
                        else UserDataContainer.setUser(userResponse.getUser());
                        userMutableLiveData.setValue(userResponse.getUser());
                    }
                    @Override
                    public void onError(Throwable e) {
                        errorMutableLiveData.setValue(e);
                    }
                }));
    }
}

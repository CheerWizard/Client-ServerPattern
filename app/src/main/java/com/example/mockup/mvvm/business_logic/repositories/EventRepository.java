package com.example.mockup.mvvm.business_logic.repositories;

import android.util.Log;

import com.example.mockup.mvvm.business_logic.dao.Events4UserDAO;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.business_logic.data.Events4UserResponse;
import com.example.mockup.mvvm.business_logic.network.webservices.MobileWebService;
import com.example.mockup.utils.ConnectivityUtils;
import com.example.mockup.utils.containers.UserDataContainer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EventRepository implements IEventRepository {
    //network service
    private MobileWebService mobileWebService;
    //local database service
    private Events4UserDAO events4UserDAO;
    //disposable
    private CompositeDisposable disposable;
    //live data
    private MutableLiveData<List<Event>> eventListMutableLiveData;
    private MutableLiveData<Event> eventMutableLiveData;
    private MutableLiveData<Throwable> errorMutableLiveData;

    @Inject
    public EventRepository(MobileWebService mobileWebService, Events4UserDAO events4UserDAO, Disposable disposable) {
        this.mobileWebService = mobileWebService;
        this.events4UserDAO = events4UserDAO;
        this.disposable = (CompositeDisposable) disposable;
        eventListMutableLiveData = new MutableLiveData<>();
        eventMutableLiveData = new MutableLiveData<>();
        errorMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public Event getValue() {
        return eventMutableLiveData.getValue();
    }

    @Override
    public void setValue(Event value) {
        eventMutableLiveData.setValue(value);
    }

    @Override
    public MutableLiveData<Throwable> error() {
        return errorMutableLiveData;
    }

    @Override
    public MutableLiveData<List<Event>> findEvents() {
        int user_id;
        if (UserDataContainer.isEmpty()) {
            user_id = events4UserDAO.selectUserId();
            UserDataContainer.setUserId(user_id);
        }
        else user_id = UserDataContainer.getUserId();
        //logs
        Log.d(getClass().getName(), "user_id " + user_id + " !!!!!!!!!!!!");

        if (ConnectivityUtils.isInternetConnected())
            disposeEvents4UserResponse(mobileWebService.getAllEventsForUser(user_id));
        else eventListMutableLiveData.setValue(events4UserDAO.selectAllForUser(user_id).getEvent());
        return eventListMutableLiveData;
    }

    @Override
    public MutableLiveData<Event> findEvent() {
        return eventMutableLiveData;
    }

    @Override
    public void deleteEvent(int codeId) {
        events4UserDAO.delete(codeId);
        mobileWebService.
    }

    @Override
    public void clear() {
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
        if (eventMutableLiveData != null) eventMutableLiveData = null;
        if (eventListMutableLiveData != null) eventListMutableLiveData = null;
        if (errorMutableLiveData != null) errorMutableLiveData = null;
    }

    private synchronized void disposeEvents4UserResponse(@NotNull Single<Events4UserResponse> events4UserResponseSingle) {
        disposable.add(events4UserResponseSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Events4UserResponse>() {
                    @Override
                    public void onSuccess(Events4UserResponse events4UserResponse) {
                        errorMutableLiveData.setValue(null);
                        for (Event event : events4UserResponse.getEvent()) events4UserDAO.insert(event);
                        eventListMutableLiveData.setValue(events4UserResponse.getEvent());
                    }
                    @Override
                    public void onError(Throwable e) {
                        errorMutableLiveData.setValue(e);
                    }
                }));
    }
}


package com.example.mockup.mvvm.business_logic.repositories;

import android.util.Log;

import com.example.mockup.mvvm.business_logic.dao.MatchDAO;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.business_logic.data.EventResponse;
import com.example.mockup.mvvm.business_logic.data.Match;
import com.example.mockup.mvvm.business_logic.network.webservices.MobileWebService;
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

public class MatchRepository implements IMatchRepository {
    //network service
    private MobileWebService mobileWebService;
    //local database service
    private MatchDAO matchDAO;
    //disposable
    private CompositeDisposable disposable;
    //live data
    private MutableLiveData<Event> eventMutableLiveData;
    private MutableLiveData<Throwable> errorMutableLiveData;

    public MutableLiveData<Event> getEventMutableLiveData() {
        return eventMutableLiveData;
    }

    @Inject
    public MatchRepository(MobileWebService mobileWebService, MatchDAO matchDAO, Disposable disposable) {
        this.mobileWebService = mobileWebService;
        this.matchDAO = matchDAO;
        this.disposable = (CompositeDisposable) disposable;
        eventMutableLiveData = new MutableLiveData<>();
        errorMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public Match getValue() {
        return null;
    }

    @Override
    public void setValue(Match match) {
    }

    @Override
    public MutableLiveData<Throwable> error() {
        return errorMutableLiveData;
    }

    @Override
    public MutableLiveData<Event> addMatch(Match match) {
        int user_id;
        if (UserDataContainer.isEmpty()) {
            user_id = matchDAO.selectUserId();
            UserDataContainer.setUserId(user_id);
        }
        else user_id = UserDataContainer.getUserId();
        //logs
        Log.d(getClass().getName() , "user_id : " + user_id + " !!!!!!!!!!");
        match.setUserId(user_id);
        disposeMatchResponse(mobileWebService.postMatch(match));
        return eventMutableLiveData;
    }

    @Override
    public void clear() {
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
        if (eventMutableLiveData != null) eventMutableLiveData = null;
        if (errorMutableLiveData != null) errorMutableLiveData = null;
    }

    private synchronized void disposeMatchResponse(@NotNull Single<EventResponse> matchResponseSingle) {
        eventMutableLiveData.setValue(null);
        disposable.add(matchResponseSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<EventResponse>() {
                    @Override
                    public void onSuccess(EventResponse eventResponse) {
                        errorMutableLiveData.setValue(null);
                        eventMutableLiveData.setValue(eventResponse.getEvent());
                    }
                    @Override
                    public void onError(Throwable e) {
                        errorMutableLiveData.setValue(e);
                    }
                }));
    }
}

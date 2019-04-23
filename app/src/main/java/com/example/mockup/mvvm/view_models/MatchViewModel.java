package com.example.mockup.mvvm.view_models;

import android.os.Bundle;

import com.example.mockup.constants.BundleKeys;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.business_logic.data.Match;
import com.example.mockup.mvvm.business_logic.repositories.IMatchRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MatchViewModel extends ViewModel {
    //repository
    private IMatchRepository matchRepository;

    @Inject
    public MatchViewModel(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MutableLiveData<Event> addMatch(Match match) {
        return matchRepository.addMatch(match);
    }

    public MutableLiveData<Throwable> error() {
        return matchRepository.error();
    }

    public void saveToBundle(Bundle outState) {
        outState.putParcelable(BundleKeys.selected_match , matchRepository.getValue());
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.containsKey(BundleKeys.selected_match))
            matchRepository.setValue(savedInstanceState.getParcelable(BundleKeys.selected_match));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        matchRepository.clear();
    }
}

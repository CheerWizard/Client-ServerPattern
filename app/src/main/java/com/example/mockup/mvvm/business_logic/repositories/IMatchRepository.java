package com.example.mockup.mvvm.business_logic.repositories;

import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.business_logic.data.Match;

import androidx.lifecycle.MutableLiveData;

public interface IMatchRepository extends IRepository<Match, Integer> {
    MutableLiveData<Event> addMatch(Match match);
}

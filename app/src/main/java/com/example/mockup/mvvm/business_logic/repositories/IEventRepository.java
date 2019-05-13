package com.example.mockup.mvvm.business_logic.repositories;

import com.example.mockup.mvvm.business_logic.data.Event;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface IEventRepository extends IRepository<Event, Integer> {
    MutableLiveData<List<Event>> findEvents();
    MutableLiveData<Event> findEvent();
    void deleteEvent(int codeId);
}
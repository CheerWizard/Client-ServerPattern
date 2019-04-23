package com.example.mockup.mvvm.view_models;

import android.os.Bundle;

import com.example.mockup.constants.BundleKeys;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.business_logic.repositories.IEventRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventViewModel extends ViewModel {
    //repository
    private IEventRepository eventRepository;

    @Inject
    public EventViewModel(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public MutableLiveData<Event> getEventMutableLiveData() {
        return eventRepository.findEvent();
    }

    public MutableLiveData<List<Event>> getAllEvents() {
        return eventRepository.findEvents();
    }

    public MutableLiveData<Throwable> error() {
        return eventRepository.error();
    }

    public void setEvent(Event event) {
        eventRepository.setValue(event);
    }

    public Event getEvent() {
        return eventRepository.getValue();
    }
    public void saveToBundle(Bundle outState) {
        if (outState != null)
            outState.putParcelable(BundleKeys.selected_event , eventRepository.getValue());
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.containsKey(BundleKeys.selected_event))
            eventRepository.setValue(savedInstanceState.getParcelable(BundleKeys.selected_event));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        eventRepository.clear();
    }
}

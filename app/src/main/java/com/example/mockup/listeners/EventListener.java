package com.example.mockup.listeners;

import com.example.mockup.mvvm.business_logic.data.Event;

public interface EventListener extends ItemListener<Event> {
    void onDeleteEvent(Event event);
}

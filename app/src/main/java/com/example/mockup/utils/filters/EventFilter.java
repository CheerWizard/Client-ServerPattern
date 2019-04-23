package com.example.mockup.utils.filters;

import com.example.mockup.constants.DateTimeZones;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.utils.DateTimeUtils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public final class EventFilter {
    @Contract("_ -> param1")
    public static synchronized List<Event> filterExpiredEvents(@NotNull List<Event> eventList) throws ParseException {
        final List<Event> events = new ArrayList<>();
        for (Event event : eventList)
            if (DateTimeUtils.isAfter(event.getEventDate() , DateTimeZones.UTC))
                events.add(event);
        return events;
    }
}

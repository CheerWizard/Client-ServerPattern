package com.example.mockup.utils;

import com.example.mockup.constants.DateTimeFormats;
import com.example.mockup.constants.DateTimeZones;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class DateTimeUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormats.default_format);
    //optionally
    public static synchronized boolean isEquals(String dateTime , @NotNull DateTimeZones dateTimeZones) throws ParseException {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(dateTimeZones.toString()));
        return simpleDateFormat.parse(dateTime).equals(new Date(System.currentTimeMillis()));
    }
    //optionally
    public static synchronized boolean isBefore(String dateTime , @NotNull DateTimeZones dateTimeZones) throws ParseException {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(dateTimeZones.toString()));
        return simpleDateFormat.parse(dateTime).before(new Date(System.currentTimeMillis()));
    }
    //main function
    public static synchronized boolean isAfter(String dateTime , @NotNull DateTimeZones dateTimeZones) throws ParseException {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(dateTimeZones.toString()));
        return simpleDateFormat.parse(dateTime).after(new Date(System.currentTimeMillis()));
    }
}

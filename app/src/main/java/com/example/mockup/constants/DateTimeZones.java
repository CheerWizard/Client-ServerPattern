package com.example.mockup.constants;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum DateTimeZones {
    UTC ,
    GMT;
    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        String timeZone = "";
        switch (this) {
            case GMT:
                timeZone = "GMT";
                break;
            case UTC:
                timeZone = "UTC";
                break;
        }
        return timeZone;
    }
}

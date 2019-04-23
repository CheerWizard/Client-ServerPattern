package com.example.mockup.mvvm.business_logic.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;

import androidx.annotation.Nullable;

/**Just a simple model that serializes and deserializes for client-server data's transfer*/
public class EventResponse {
    @SerializedName("success")
    @Expose(serialize = false)
    private boolean success;
    @SerializedName("message")
    @Expose(serialize = false)
    private String message;
    @SerializedName("data")
    @Expose
    private Event event;
    @SerializedName("isCreate")
    @Expose(serialize = false)
    private boolean isCreate;

    public EventResponse() {}

    public EventResponse(boolean success, String message, Event event, boolean isCreate) {
        this.success = success;
        this.message = message;
        this.event = event;
        this.isCreate = isCreate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isCreate() {
        return isCreate;
    }

    public void setCreate(boolean create) {
        isCreate = create;
    }

    public void setIsCreate(boolean isCreate) {
        this.isCreate = isCreate;
    }

    @Override
    public int hashCode() {
        return event.hashCode();
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(@Nullable Object obj) {
        boolean equals = false;
        if (obj != null)
            if (getClass() == obj.getClass())
                equals = this.hashCode() == obj.hashCode();
        return equals;
    }
}

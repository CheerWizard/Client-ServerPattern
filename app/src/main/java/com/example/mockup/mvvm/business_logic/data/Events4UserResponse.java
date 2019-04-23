package com.example.mockup.mvvm.business_logic.data;

import com.example.mockup.constants.SqlStorage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

public class Events4UserResponse {
    @Embedded
    private User user;

    @Ignore
    @SerializedName("success")
    @Expose(serialize = false)
    private boolean success;

    @Ignore
    @SerializedName("message")
    @Expose(serialize = false)
    private String message;

    @SerializedName("data")
    @Expose
    @Relation(parentColumn = SqlStorage.Columns.user_id ,
                entity = Event.class ,
                entityColumn = SqlStorage.Columns.event_userId)
    private List<Event> event;

    public Events4UserResponse() {}

    @Ignore
    public Events4UserResponse(User user, boolean success, String message, List<Event> event) {
        this.user = user;
        this.success = success;
        this.message = message;
        this.event = event;
    }

    @Ignore
    public Events4UserResponse(boolean success, String message, List<Event> event) {
        this.success = success;
        this.message = message;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        return user.hashCode();
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

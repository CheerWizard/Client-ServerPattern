package com.example.mockup.mvvm.business_logic.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;
/**Just a simple model that serializes and de serializes for client-server data's transfer*/
public class UserResponse {
    @SerializedName("success")
    @Expose(serialize = false)
    private boolean success;

    @SerializedName("message")
    @Expose(serialize = false)
    private String message;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("isCreate")
    @Expose(serialize = false)
    private boolean isCreate;

    public UserResponse() {}

    public UserResponse(boolean success, String message, User user, boolean isCreate) {
        this.success = success;
        this.message = message;
        this.user = user;
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

    public boolean isIsCreate() {
        return isCreate;
    }

    public void setIsCreate(boolean isCreate) {
        this.isCreate = isCreate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCreate() {
        return isCreate;
    }

    public void setCreate(boolean create) {
        isCreate = create;
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object other) {
        boolean equals = false;
        if (other != null)
            if (getClass() == other.getClass())
                equals = (this.hashCode() == other.hashCode());
        return equals;
    }
}

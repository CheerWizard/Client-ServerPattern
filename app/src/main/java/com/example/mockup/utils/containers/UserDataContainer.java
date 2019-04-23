package com.example.mockup.utils.containers;

import com.example.mockup.mvvm.business_logic.data.User;

import org.jetbrains.annotations.Contract;

public final class UserDataContainer {

    private static User user;

    @Contract(pure = true)
    public static int getUserId() {
        return user.getUserId();
    }

    @Contract(pure = true)
    public static User getUser() {
        return user;
    }

    public static void setUserId(int userId) {
        if (user == null) user = new User();
        user.setUserId(userId);
    }

    public static void setUser(User user) {
        UserDataContainer.user = user;
    }

    @Contract(pure = true)
    public static synchronized boolean isEmpty() {
        return user == null;
    }
}

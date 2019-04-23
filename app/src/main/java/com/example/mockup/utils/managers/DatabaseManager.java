package com.example.mockup.utils.managers;

import android.content.Context;

import org.jetbrains.annotations.Contract;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public final class DatabaseManager {

    private static RoomDatabase roomDataBase;
    private static boolean userExists;

    public static synchronized void open(Context context , Class<? extends RoomDatabase> roomDatabaseClass , String db_name) {
        roomDataBase = Room
                .databaseBuilder(context , roomDatabaseClass , db_name)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized void close() {
        if (isOpen()) roomDataBase.close();
    }

    public static synchronized boolean isOpen() {
        return roomDataBase.isOpen();
    }

    @Contract(pure = true)
    public static RoomDatabase getRoomDataBase() {
        return roomDataBase;
    }
}
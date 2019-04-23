package com.example.mockup.mvvm.business_logic.databases;

import com.example.mockup.constants.SqlStorage;
import com.example.mockup.mvvm.business_logic.dao.Events4UserDAO;
import com.example.mockup.mvvm.business_logic.dao.MatchDAO;
import com.example.mockup.mvvm.business_logic.dao.UserDAO;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.business_logic.data.User;

import javax.inject.Singleton;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Singleton
@Database(entities = {Event.class , User.class} ,
        version = SqlStorage.Version.v_1 , exportSchema = false)
public abstract class WedCodesDatabase extends RoomDatabase {
    public abstract Events4UserDAO weddingDAO();
    public abstract UserDAO userDAO();
    public abstract MatchDAO matchDAO();
}

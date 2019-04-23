package com.example.mockup.dependency_injection.modules;

import android.content.Context;
import android.util.Log;

import com.example.mockup.constants.SqlStorage;
import com.example.mockup.mvvm.business_logic.databases.WedCodesDatabase;
import com.example.mockup.utils.managers.DatabaseManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = ContextModule.class)
public class DatabaseModule {
    @Singleton
    @Provides
    static WedCodesDatabase provideWedCodesDatabase(Context context) {
        Log.d("database module" , "started");
        DatabaseManager.open(context , WedCodesDatabase.class , SqlStorage.Databases.wed_codes_db);
        return (WedCodesDatabase) DatabaseManager.getRoomDataBase();
    }
}
package com.example.mockup.dependency_injection.modules;

import android.util.Log;

import com.example.mockup.mvvm.business_logic.dao.Events4UserDAO;
import com.example.mockup.mvvm.business_logic.dao.MatchDAO;
import com.example.mockup.mvvm.business_logic.dao.UserDAO;
import com.example.mockup.mvvm.business_logic.databases.WedCodesDatabase;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = {DatabaseModule.class})
public class DaoModule {
    @Singleton
    @Provides
    static Events4UserDAO provideEvents4UserDAO(@NonNull WedCodesDatabase wedCodesDatabase) {
        Log.d("dao module" , "started");
        return wedCodesDatabase.weddingDAO();
    }
    @Singleton
    @Provides
    static UserDAO provideUserDAO(@NonNull WedCodesDatabase wedCodesDatabase) {
        Log.d("dao module" , "started");
        return wedCodesDatabase.userDAO();
    }
    @Singleton
    @Provides
    static MatchDAO provideMatchDAO(@NonNull WedCodesDatabase wedCodesDatabase) {
        Log.d("dao module" , "started");
        return wedCodesDatabase.matchDAO();
    }
}
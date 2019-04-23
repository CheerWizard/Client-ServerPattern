package com.example.mockup.dependency_injection.modules;

import android.util.Log;

import com.example.mockup.mvvm.business_logic.dao.Events4UserDAO;
import com.example.mockup.mvvm.business_logic.dao.MatchDAO;
import com.example.mockup.mvvm.business_logic.dao.UserDAO;
import com.example.mockup.mvvm.business_logic.network.webservices.MobileWebService;
import com.example.mockup.mvvm.business_logic.repositories.IEventRepository;
import com.example.mockup.mvvm.business_logic.repositories.EventRepository;
import com.example.mockup.mvvm.business_logic.repositories.IMatchRepository;
import com.example.mockup.mvvm.business_logic.repositories.IUserRepository;
import com.example.mockup.mvvm.business_logic.repositories.MatchRepository;
import com.example.mockup.mvvm.business_logic.repositories.UserRepository;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.Disposable;

@Singleton
@Module(includes = {NetworkModule.class , StorageModule.class , DisposableModule.class})
public class RepositoryModule {
    @NotNull
    @Contract("_, _, _ -> new")
    @Singleton
    @Provides
    static IEventRepository provideMovieRepository(MobileWebService mobileWebService , Events4UserDAO events4UserDAO , Disposable disposable) {
        Log.d("repository module" , "started");
        return new EventRepository(mobileWebService, events4UserDAO , disposable);
    }
    @NotNull
    @Contract("_, _, _ -> new")
    @Singleton
    @Provides
    static IUserRepository provideUserRepository(MobileWebService mobileWebService , UserDAO userDAO , Disposable disposable) {
        Log.d("repository module" , "started");
        return new UserRepository(mobileWebService, userDAO , disposable);
    }
    @NotNull
    @Contract("_, _, _ -> new")
    @Singleton
    @Provides
    static IMatchRepository provideMatchRepository(MobileWebService mobileWebService , MatchDAO matchDAO , Disposable disposable) {
        Log.d("repository module" , "started");
        return new MatchRepository(mobileWebService, matchDAO , disposable);
    }
}
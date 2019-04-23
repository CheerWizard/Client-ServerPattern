package com.example.mockup.dependency_injection.modules;

import android.util.Log;

import com.example.mockup.mvvm.business_logic.network.webservices.MobileWebService;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Singleton
@Module(includes = {RetrofitModule.class})
public class NetworkModule {
    @Singleton
    @Provides
    static MobileWebService provideTmdbWebService(@NonNull Retrofit retrofit) {
        Log.d("network module" , "started");
        return retrofit.create(MobileWebService.class);
    }
}
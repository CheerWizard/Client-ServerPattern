package com.example.mockup.dependency_injection.modules;

import android.util.Log;

import com.example.mockup.constants.ApiConstants;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = {OkHttpClientModule.class})
public class RetrofitModule {
    @NonNull
    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Log.d("retrofit module" , "started");
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

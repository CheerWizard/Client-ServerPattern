package com.example.mockup.dependency_injection.modules;

import android.util.Log;

import com.example.mockup.constants.ApiConstants;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Singleton
@Module
public class OkHttpClientModule {
    @Singleton
    @Provides
    @NotNull
    static OkHttpClient provideOkHttpClient() {
        Log.d("ok http client module" , "started");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            //init here all headers that will be static
            final Request request = chain.request().newBuilder()
                    .header(ApiConstants.api_key_name, ApiConstants.api_key)
                    .header(ApiConstants.content_type_name , ApiConstants.content_type)
                    .build();
            return chain.proceed(request);
        });
        return builder.build();
    }
}

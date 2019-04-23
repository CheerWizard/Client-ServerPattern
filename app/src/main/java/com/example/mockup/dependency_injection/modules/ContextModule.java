package com.example.mockup.dependency_injection.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Singleton
@Module
public abstract class ContextModule {
    @Binds
    abstract Context provideContext(Application application);
}

package com.example.mockup.mvvm.ui;

import com.example.mockup.dependency_injection.components.ApplicationComponent;
import com.example.mockup.dependency_injection.components.DaggerApplicationComponent;
import com.example.mockup.utils.ConnectivityUtils;
import com.example.mockup.utils.managers.DatabaseManager;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class WedCodesApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ConnectivityUtils.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (DatabaseManager.isOpen()) DatabaseManager.close();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}

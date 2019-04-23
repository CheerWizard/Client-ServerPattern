package com.example.mockup.dependency_injection.modules;

import com.example.mockup.mvvm.ui.activites.WeddingActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Singleton
@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = FragmentBindingModule.class)
    abstract WeddingActivity bindWeddingActivity();
}

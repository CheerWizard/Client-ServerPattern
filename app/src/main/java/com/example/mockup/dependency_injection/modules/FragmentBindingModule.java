package com.example.mockup.dependency_injection.modules;

import com.example.mockup.mvvm.ui.fragments.AddWeddingFragment;
import com.example.mockup.mvvm.ui.fragments.RegistrationFragment;
import com.example.mockup.mvvm.ui.fragments.WeddingContentFragment;
import com.example.mockup.mvvm.ui.fragments.WeddingListFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Singleton
@Module
public abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract RegistrationFragment provideRegistrationFragment();
    @ContributesAndroidInjector
    abstract AddWeddingFragment provideAddWeddingFragment();
    @ContributesAndroidInjector
    abstract WeddingContentFragment provideWeddingContentFragment();
    @ContributesAndroidInjector
    abstract WeddingListFragment provideWeddingListFragment();
}
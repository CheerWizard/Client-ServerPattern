package com.example.mockup.dependency_injection.modules;


import com.example.mockup.annotations.ViewModelKey;
import com.example.mockup.mvvm.view_models.EventViewModel;
import com.example.mockup.mvvm.view_models.MatchViewModel;
import com.example.mockup.mvvm.view_models.UserViewModel;
import com.example.mockup.utils.factories.ViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module(includes = {RepositoryModule.class})
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel.class)
    abstract ViewModel bindEventViewModel(EventViewModel eventViewModel);
    @Binds
    @IntoMap
    @ViewModelKey(MatchViewModel.class)
    abstract ViewModel bindMatchViewModel(MatchViewModel matchViewModel);
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
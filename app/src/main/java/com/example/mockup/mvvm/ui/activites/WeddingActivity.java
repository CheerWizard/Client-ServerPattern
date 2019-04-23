package com.example.mockup.mvvm.ui.activites;

import android.os.Bundle;

import com.example.mockup.R;
import com.example.mockup.mvvm.business_logic.data.User;
import com.example.mockup.mvvm.ui.fragments.RegistrationFragment;
import com.example.mockup.mvvm.ui.fragments.WeddingListFragment;
import com.example.mockup.mvvm.view_models.UserViewModel;
import com.example.mockup.utils.dispatchers.FragmentDispatcher;
import com.example.mockup.utils.factories.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class WeddingActivity extends BaseActivity {
    //view model
    private UserViewModel userViewModel;
    @Inject
    ViewModelFactory viewModelFactory;
    //global var
    private Observer<User> userObserver;
    private LiveData<User> userLiveData;

    @Override
    protected int layoutResPortrait() {
        return R.layout.activity_wedding;
    }

    @Override
    protected int layoutResLandscape() {
        return R.layout.activity_wedding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModels();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initObservers();
        observeUser();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (userLiveData != null) userLiveData.removeObserver(userObserver);
    }

    private synchronized void initViewModels() {
        userViewModel = ViewModelProviders.of(this , viewModelFactory).get(UserViewModel.class);
    }

    private synchronized void observeUser() {
        userLiveData = userViewModel.getUser();
        userLiveData.observe(this , userObserver);
    }

    private synchronized void initObservers() {
        userObserver = user -> {
            if (user != null && user.getEmail() != null) FragmentDispatcher.dispatchAndMoveTo(this , new WeddingListFragment());
            else FragmentDispatcher.dispatchAndMoveTo(this , new RegistrationFragment());
        };
    }
}

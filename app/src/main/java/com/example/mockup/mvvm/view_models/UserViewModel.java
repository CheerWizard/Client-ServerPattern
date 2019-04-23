package com.example.mockup.mvvm.view_models;

import android.os.Bundle;

import com.example.mockup.constants.BundleKeys;
import com.example.mockup.mvvm.business_logic.data.User;
import com.example.mockup.mvvm.business_logic.repositories.IUserRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private IUserRepository userRepository;

    @Inject
    public UserViewModel(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<User> getUser() {
        return userRepository.findUser();
    }

    public MutableLiveData<User> registUser(User user) {
        return userRepository.saveUser(user);
    }

    public MutableLiveData<User> saveToLocalStorage(User user) {
        return userRepository.saveToLocalStorage(user);
    }

    public MutableLiveData<Throwable> error() {
        return userRepository.error();
    }

    public void saveToBundle(Bundle outState) {
        outState.putParcelable(BundleKeys.selected_user , userRepository.getValue());
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.containsKey(BundleKeys.selected_user))
            userRepository.setValue(savedInstanceState.getParcelable(BundleKeys.selected_user));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        userRepository.clear();
    }
}

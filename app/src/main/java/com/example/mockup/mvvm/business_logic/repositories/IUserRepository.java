package com.example.mockup.mvvm.business_logic.repositories;

import com.example.mockup.mvvm.business_logic.data.User;

import androidx.lifecycle.MutableLiveData;

public interface IUserRepository extends IRepository<User , Integer> {
    MutableLiveData<User> findUser();
    MutableLiveData<User> saveUser(User user);
    MutableLiveData<User> saveToLocalStorage(User user);
}

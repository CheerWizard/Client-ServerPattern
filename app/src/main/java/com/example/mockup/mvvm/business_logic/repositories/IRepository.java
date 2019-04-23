package com.example.mockup.mvvm.business_logic.repositories;

import androidx.lifecycle.MutableLiveData;

public interface IRepository<Data , ID> {
    Data getValue();
    void setValue(Data value);
    MutableLiveData<Throwable> error();
    void clear();
}
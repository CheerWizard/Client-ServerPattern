package com.example.mockup.mvvm.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mockup.R;
import com.example.mockup.constants.ExceptionMessages;
import com.example.mockup.mvvm.business_logic.data.User;
import com.example.mockup.mvvm.view_models.UserViewModel;
import com.example.mockup.utils.DialogUtils;
import com.example.mockup.utils.containers.UserDataContainer;
import com.example.mockup.utils.dispatchers.FragmentDispatcher;
import com.example.mockup.utils.factories.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import retrofit2.HttpException;

public class RegistrationFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.urlTextView)
    TextView urlTextView;
    @BindView(R.id.registrBtn)
    Button registrButton;
    @BindView(R.id.emailEditText)
    EditText emailEditText;
    //view model
    private UserViewModel userViewModel;
    //observers
    private Observer<Throwable> errorObserver;
    private Observer<User> userObserver;
    //live data
    private LiveData<User> userLiveData;
    private LiveData<Throwable> errorLiveData;

    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected int layoutResPortrait() {
        return R.layout.registration_fragment_portrait;
    }

    @Override
    protected int layoutResLandscape() {
        return R.layout.registration_fragment_landscape;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViewsModels();
    }

    @Override
    public void onStart() {
        super.onStart();
        initObservers();
    }

    @Override
    public void onResume() {
        super.onResume();
        setListeners();
        handleErrors();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (userLiveData != null) userLiveData.removeObserver(userObserver);
        if (errorLiveData != null) errorLiveData.removeObserver(errorObserver);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (userViewModel != null) userViewModel.restoreFromBundle(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (userViewModel != null) userViewModel.saveToBundle(outState);
    }

    private synchronized void setListeners() {
        registrButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registrBtn:
                Log.d(getClass().getName() , "Registr user method!");
                if (!emailEditText.getText().toString().equals("")) {
                    User user = new User();
                    user.setEmail(emailEditText.getText().toString());
                    UserDataContainer.setUser(user);
                    userLiveData = userViewModel.registUser(user);
                    userLiveData.observe(getBaseActivity() , userObserver);
                    emailEditText.setText("");
                }
                else Toast.makeText(getBaseActivity(), R.string.field_is_empty, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private synchronized void initViewsModels() {
        userViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(UserViewModel.class);
    }

    private synchronized void initObservers() {
        userObserver = user -> {
            if (user != null && user.getEmail() != null) FragmentDispatcher.dispatchAndMoveTo(getBaseActivity(), new AddWeddingFragment());
        };
        errorObserver = error -> {
            if (error != null) throwError(error);
        };
    }

    private synchronized void handleErrors() {
        Log.d(getClass().getName() , "Handle errors method!");
        errorLiveData = userViewModel.error();
        errorLiveData.observe(getBaseActivity() , errorObserver);
    }

    private synchronized void throwError(Throwable e) {
        final HttpException httpException = (HttpException) e;
        switch (httpException.code()) {
            case ExceptionMessages.http_404:
                DialogUtils.showAlertMessage(getBaseActivity() , getString(R.string.invalid_email));
                break;
            case ExceptionMessages.http_400:
                DialogUtils.showSelectorMessage(getBaseActivity() , userViewModel , getString(R.string.already_registered));
                break;
        }
    }
}

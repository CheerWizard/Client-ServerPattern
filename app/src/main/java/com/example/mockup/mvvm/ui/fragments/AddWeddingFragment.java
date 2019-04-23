package com.example.mockup.mvvm.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mockup.R;
import com.example.mockup.constants.ExceptionMessages;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.business_logic.data.Match;
import com.example.mockup.mvvm.view_models.MatchViewModel;
import com.example.mockup.utils.DialogUtils;
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

public class AddWeddingFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.addWedCodeBtn)
    Button addWedCodeButton;
    @BindView(R.id.wedCodeEditText)
    EditText wedCodeEditText;
    //view models
    private MatchViewModel matchViewModel;
    //observers
    private Observer<Event> eventObserver;
    private Observer<Throwable> errorObserver;
    //live data
    private LiveData<Event> eventLiveData;
    private LiveData<Throwable> errorLiveData;

    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected int layoutResPortrait() {
        return R.layout.add_wedding_fragment_portrait;
    }

    @Override
    protected int layoutResLandscape() {
        return R.layout.add_wedding_fragment_landscape;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViewModels();
    }

    @Override
    public void onStart() {
        super.onStart();
        initObservers();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (matchViewModel != null) matchViewModel.restoreFromBundle(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (matchViewModel != null) matchViewModel.saveToBundle(outState);
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
        if (errorLiveData != null) errorLiveData.removeObserver(errorObserver);
        if (eventLiveData != null) eventLiveData.removeObserver(eventObserver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addWedCodeBtn:
                if (!wedCodeEditText.getText().toString().equals("")) {
                    Match match = new Match();
                    final int code_id = Integer.parseInt(wedCodeEditText.getText().toString());
                    //logs
                    Log.d(getClass().getName() , "code_id : " + code_id + " !!!!!!!!!!");
                    match.setCodeId(code_id);
                    eventLiveData = matchViewModel.addMatch(match);
                    eventLiveData.observe(getBaseActivity() , eventObserver);
                    wedCodeEditText.setText("");
                }
                else Toast.makeText(getBaseActivity(), getString(R.string.field_is_empty), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private synchronized void initViewModels() {
        matchViewModel = ViewModelProviders.of(getBaseActivity() , viewModelFactory).get(MatchViewModel.class);
    }

    private synchronized void setListeners() {
        addWedCodeButton.setOnClickListener(this);
    }

    private synchronized void handleErrors() {
        errorLiveData = matchViewModel.error();
        errorLiveData.observe(getBaseActivity() , errorObserver);
    }

    private synchronized void initObservers() {
        eventObserver = event -> {
            if (event != null) FragmentDispatcher.dispatchAndMoveTo(getBaseActivity(), new WeddingListFragment());
        };
        errorObserver = error -> {
            if (error != null) throwError(error);
        };
    }

    private synchronized void throwError(Throwable e) {
        final HttpException httpException = (HttpException) e;
        switch (httpException.code()) {
            case ExceptionMessages.http_404:
                DialogUtils.showAlertMessage(getBaseActivity() , getString(R.string.invalid_wedding_code));
                break;
        }
    }
}

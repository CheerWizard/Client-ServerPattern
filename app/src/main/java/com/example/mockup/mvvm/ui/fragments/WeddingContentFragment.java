package com.example.mockup.mvvm.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.mockup.R;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.ui.web_clients.CustomWebClient;
import com.example.mockup.mvvm.view_models.EventViewModel;
import com.example.mockup.utils.dispatchers.FragmentDispatcher;
import com.example.mockup.utils.factories.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;

public class WeddingContentFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.urlWebView)
    WebView urlWebView;
    @BindView(R.id.backToMyWeddingTextView)
    TextView backToWeddingsTextView;
    //view modelf
    private EventViewModel eventViewModel;
    //observers
    private Observer<Event> eventObserver;
    //live data
    private LiveData<Event> eventLiveData;

    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected int layoutResPortrait() {
        return R.layout.wedding_content_fragment_portrait;
    }

    @Override
    protected int layoutResLandscape() {
        return R.layout.wedding_content_fragment_landscape;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViewModels();
        initWebClients();
        initObservers(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        observeEvent();
    }

    @Override
    public void onResume() {
        super.onResume();
        setListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (eventLiveData != null) eventLiveData.removeObserver(eventObserver);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (eventViewModel != null) eventViewModel.restoreFromBundle(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (eventViewModel != null) eventViewModel.saveToBundle(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToMyWeddingTextView:
                FragmentDispatcher.dispatchAndMoveTo(getBaseActivity() , new WeddingListFragment());
                break;
        }
    }

    private synchronized void initViewModels() {
        eventViewModel = ViewModelProviders.of(getBaseActivity() , viewModelFactory).get(EventViewModel.class);
    }

    private synchronized void initWebClients() {
        urlWebView.getSettings().setJavaScriptEnabled(true);
        urlWebView.setWebViewClient(CustomWebClient.getInstance());
    }

    private synchronized void setListeners() {
        backToWeddingsTextView.setOnClickListener(this);
    }

    private synchronized void observeEvent() {
        eventLiveData = eventViewModel.getEventMutableLiveData();
        eventLiveData.observe(getBaseActivity() , eventObserver);
    }

    private synchronized void initObservers(View view) {
        eventObserver = event -> {
            if (event != null) {
                if (urlWebView != null) urlWebView.loadUrl(event.getWebUrl());
                else urlWebView = view.findViewById(R.id.urlWebView);
            }
        };
    }
}

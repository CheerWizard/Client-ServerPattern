package com.example.mockup.mvvm.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mockup.R;
import com.example.mockup.adapters.EventAdapter;
import com.example.mockup.listeners.EventListener;
import com.example.mockup.mvvm.business_logic.data.Event;
import com.example.mockup.mvvm.view_models.EventViewModel;
import com.example.mockup.utils.DialogUtils;
import com.example.mockup.utils.filters.EventFilter;
import com.example.mockup.utils.dispatchers.FragmentDispatcher;
import com.example.mockup.utils.factories.ViewModelFactory;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class WeddingListFragment extends BaseFragment implements View.OnClickListener, EventListener {
    @BindView(R.id.eventsRecyclerView)
    RecyclerView eventsRecyclerView;
    @BindView(R.id.addOneMoreWedCodeBtn)
    Button addOneMoreWedCodeButton;
    //adapter
    private EventAdapter eventAdapter;
    //view models
    private EventViewModel eventViewModel;
    //observers
    private Observer<List<Event>> eventListObserver;
    //live data
    private LiveData<List<Event>> eventListLiveData;

    @Inject ViewModelFactory viewModelFactory;

    @Override
    protected int layoutResPortrait() {
        return R.layout.wedding_list_fragment_portrait;
    }

    @Override
    protected int layoutResLandscape() {
        return R.layout.wedding_list_fragment_landscape;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViewModels();
        initAdapters();
    }

    @Override
    public void onStart() {
        super.onStart();
        initObservers();
        observeEvents();
    }

    @Override
    public void onResume() {
        super.onResume();
        setListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (eventListLiveData != null) eventListLiveData.removeObserver(eventListObserver);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (eventViewModel != null) eventViewModel.saveToBundle(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (eventViewModel != null) eventViewModel.saveToBundle(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addOneMoreWedCodeBtn:
                FragmentDispatcher.dispatchAndMoveTo(getBaseActivity() , new AddWeddingFragment());
                break;
        }
    }

    private synchronized void initViewModels() {
        eventViewModel = ViewModelProviders.of(getBaseActivity() , viewModelFactory).get(EventViewModel.class);
    }

    private synchronized void initAdapters() {
        eventAdapter = new EventAdapter(this);
        eventsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eventsRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        eventsRecyclerView.setAdapter(eventAdapter);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
    }

    private synchronized void observeEvents() {
        eventListLiveData = eventViewModel.getAllEvents();
        eventListLiveData.observe(getBaseActivity() , eventListObserver);
    }

    private synchronized void initObservers() {
        eventListObserver = eventList -> {
            if (eventList != null && !eventList.isEmpty()) {
                try {
//                call utils to filter our events and after we will update it in adapter
                eventAdapter.updateData(EventFilter.filterExpiredEvents(eventList));
                } catch (ParseException e) {
                    DialogUtils.showAlertMessage(getBaseActivity() , e.getMessage());
                }
            }
        };
    }

    private synchronized void setListeners() {
        addOneMoreWedCodeButton.setOnClickListener(this);
    }

    @Override
    public void onSelect(Event event) {
        //transfer value
        eventViewModel.setEvent(event);
        //begin transaction to another fragment
        FragmentDispatcher.dispatchAndMoveTo(getBaseActivity() , new WeddingContentFragment());
    }

    @Override
    public void onDeleteEvent(Event event) {
        Log.d(getClass().getName() , "On delete event with " + event.getCodeId());
    }
}

package com.example.mockup.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.mockup.R;
import com.example.mockup.listeners.EventListener;
import com.example.mockup.mvvm.business_logic.data.Event;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends RecyclerSwipeAdapter<EventAdapter.WeddingCodesViewHolder> {

    private final List<Event> weddingList = new ArrayList<>();
    private EventListener eventListener;

    public EventAdapter(EventListener eventListener) {
        this.eventListener = eventListener;
        setHasStableIds(true);
    }

    public void updateData(List<Event> updatedList) {
        weddingList.clear();
        if (updatedList != null) {
            weddingList.addAll(updatedList);
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public WeddingCodesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.view_holder_wedding_code, viewGroup, false);
        return new WeddingCodesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WeddingCodesViewHolder viewHolder, int i){
        viewHolder.bind(weddingList.get(i));
    }

    @Override
    public int getItemCount(){
        return weddingList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_wedding_code;
    }

    class WeddingCodesViewHolder extends RecyclerView.ViewHolder{
        //bindView annotation will automatically identify the particular views
        @BindView(R.id.wedNameTextView)
        TextView wedNameTextView;
        @BindView(R.id.deleteBtn)
        Button deleteButton;
        //global var
        private Event event;

        private WeddingCodesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener((v) -> {if (event != null) {
                eventListener.onSelect(event);
            }});
            deleteButton.setOnClickListener((v) -> {
                if (event != null) eventListener.onDeleteEvent(event);
            });
        }

        private void bind(@NonNull Event event) {
            this.event = event;
            this.wedNameTextView.setText(event.getName());
        }
    }
}
package com.example.mockup.mvvm.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.mockup.R;
import com.example.mockup.mvvm.ui.fragments.WeddingListFragment;
import com.example.mockup.mvvm.view_models.UserViewModel;
import com.example.mockup.utils.containers.UserDataContainer;
import com.example.mockup.utils.dispatchers.FragmentDispatcher;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public final class CustomSelectorDialog extends AlertDialog implements View.OnClickListener {
    //bind views
    @BindView(R.id.messageTextView)
    TextView messageTextView;
    @BindView(R.id.restoreTextView)
    TextView restoreTextView;
    @BindView(R.id.newTextView)
    TextView newTextView;

    private AppCompatActivity appCompatActivity;
    private ViewModel viewModel;

    private CustomSelectorDialog(AppCompatActivity appCompatActivity , ViewModel viewModel) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.viewModel = viewModel;
        defaultInit(appCompatActivity);
    }

    private synchronized void defaultInit(Context context) {
        //inflate view
        final View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_selector_layout, null);
        //set to alert dialog
        setView(view);
        //bind view
        ButterKnife.bind(this , view);
        //set listeners
        setListeners();
    }

    @Contract("_, _ -> new")
    @NotNull
    public static synchronized CustomSelectorDialog getInstance(AppCompatActivity appCompatActivity , ViewModel viewModel) {
        return new CustomSelectorDialog(appCompatActivity , viewModel);
    }
    @Contract(pure = true)
    public TextView getMessageTextView() {
        return messageTextView;
    }

    public void setMessageTextView(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Contract(pure = true)
    public TextView getRestoreTextView() {
        return restoreTextView;
    }

    public void setRestoreTextView(TextView restoreTextView) {
        this.restoreTextView = restoreTextView;
    }

    @Contract(pure = true)
    public TextView getNewTextView() {
        return newTextView;
    }

    public void setNewTextView(TextView newTextView) {
        this.newTextView = newTextView;
    }

    @Contract(pure = true)
    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void setMessage(String message) {
        messageTextView.setText(message);
    }

    private synchronized void setListeners() {
        newTextView.setOnClickListener(this);
        restoreTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(@NotNull View v) {
        switch (v.getId()) {
            case R.id.restoreTextView:
                recognizeViewModel();
                hide();
                break;
            case R.id.newTextView:
                hide();
                break;
        }
    }

    private synchronized void recognizeViewModel() {
        if (viewModel instanceof UserViewModel) {
            ((UserViewModel) viewModel).saveToLocalStorage(UserDataContainer.getUser()).observe(appCompatActivity , user -> {
                if (user != null) FragmentDispatcher.dispatchAndMoveTo(appCompatActivity , new WeddingListFragment());
            });
        }
    }
}

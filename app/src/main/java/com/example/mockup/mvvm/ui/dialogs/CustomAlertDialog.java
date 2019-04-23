package com.example.mockup.mvvm.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.mockup.R;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CustomAlertDialog extends AlertDialog implements View.OnClickListener {
    //bind views
    @BindView(R.id.messageTextView)
    TextView messageTextView;
    @BindView(R.id.returnTextView)
    TextView returnTextView;

    private CustomAlertDialog(Context context) {
        super(context);
        defaultInit(context);
    }

    private synchronized void defaultInit(Context context) {
        //inflate view
        final View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_accepter_layout, null);
        //set to alert dialog
        setView(view);
        //bind view
        ButterKnife.bind(this , view);
        //set listeners
        setListeners();
    }

    @NotNull
    @Contract("_ -> new")
    public static synchronized CustomAlertDialog getInstance(Context context) {
        return new CustomAlertDialog(context);
    }

    @Contract(pure = true)
    public TextView getMessageTextView() {
        return messageTextView;
    }

    public void setMessageTextView(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    public void setMessage(String message) {
        messageTextView.setText(message);
    }

    @Contract(pure = true)
    public TextView getReturnTextView() {
        return returnTextView;
    }

    public void setReturnTextView(TextView returnTextView) {
        this.returnTextView = returnTextView;
    }

    private synchronized void setListeners() {
        returnTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(@NotNull View v) {
        switch (v.getId()) {
            case R.id.returnTextView:
                hide();
                break;
        }
    }
}

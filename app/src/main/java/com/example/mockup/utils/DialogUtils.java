package com.example.mockup.utils;

import android.content.Context;

import com.example.mockup.mvvm.ui.dialogs.CustomAlertDialog;
import com.example.mockup.mvvm.ui.dialogs.CustomSelectorDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

public final class DialogUtils {
    public static synchronized void showAlertMessage(Context context , String message) {
        final CustomAlertDialog customAlertDialog = CustomAlertDialog.getInstance(context);
        customAlertDialog.setMessage(message);
        customAlertDialog.show();
    }
    public static synchronized void showSelectorMessage(AppCompatActivity appCompatActivity , ViewModel viewModel , String message) {
        final CustomSelectorDialog customSelectorDialog = CustomSelectorDialog.getInstance(appCompatActivity , viewModel);
        customSelectorDialog.setMessage(message);
        customSelectorDialog.show();
    }
}

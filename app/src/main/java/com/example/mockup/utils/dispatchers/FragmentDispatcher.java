package com.example.mockup.utils.dispatchers;

import com.example.mockup.R;
import com.example.mockup.mvvm.ui.fragments.BaseFragment;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;

public final class FragmentDispatcher {
    public static synchronized void dispatchAndMoveTo(@NotNull AppCompatActivity activity, BaseFragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null).commit();
    }
}

package com.example.mockup.mvvm.ui.activites;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {
    @LayoutRes
    protected abstract int layoutResPortrait();
    @LayoutRes
    protected abstract int layoutResLandscape();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(layoutResLandscape());
        else setContentView(layoutResPortrait());
        ButterKnife.bind(this);
    }
}
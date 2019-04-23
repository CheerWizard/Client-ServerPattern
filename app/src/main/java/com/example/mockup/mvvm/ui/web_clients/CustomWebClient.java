package com.example.mockup.mvvm.ui.web_clients;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebClient extends WebViewClient {
    //make it singleton
    private static CustomWebClient instance;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    public static synchronized CustomWebClient getInstance() {
        if (instance == null) instance = new CustomWebClient();
        return instance;
    }
}

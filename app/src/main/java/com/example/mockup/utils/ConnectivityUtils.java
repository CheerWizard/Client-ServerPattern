package com.example.mockup.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**Class that just will globally transfer connection status and it's info*/
public final class ConnectivityUtils {

    private static ConnectivityManager connectivityManager;

    public static synchronized void init(Context context) {
        if (connectivityManager == null ) connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static synchronized boolean isInternetConnected() {
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null
                && networkInfo.isAvailable()
                && networkInfo.isConnected());
    }
}
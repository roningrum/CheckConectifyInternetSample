package com.example.checkconectifyinternetsample;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class MyApplication extends Application {
    public static final String WIFI_STATE_CHANGE_ACTION = "android.net.wifi.WIFI_STATE_CHANGED";
    @Override
    public void onCreate() {
        super.onCreate();
        registerForNetworkChangeEvents(this);
    }
    public static void registerForNetworkChangeEvents(final Context context){
        ConnectivityReceiver networkStateChangeReceiver = new ConnectivityReceiver();
        context.registerReceiver(networkStateChangeReceiver, new IntentFilter(CONNECTIVITY_ACTION));
        context.registerReceiver(networkStateChangeReceiver, new IntentFilter(WIFI_STATE_CHANGE_ACTION));
    }
}

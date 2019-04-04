package com.example.checkconectifyinternetsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class ConnectivityReceiver extends BroadcastReceiver {
    public static final String NETWORK_AVAILABLE_ACTION = "com.ajit.singh.NetworkAvailable";
    public static final String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context));
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);
    }

    private boolean isConnectedToInternet(Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;

        } catch (Exception e) {
            Log.e(ConnectivityReceiver.class.getName(), e.getMessage());
            return false;
        }
    }
//    public static ConnectivityReceiverListener connectivityReceiverListener;
//
//    public ConnectivityReceiver() {
//        super();
//    }
//
//    @SuppressLint("UnsafeProtectedBroadcastReceiver")
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null
//                && activeNetwork.isConnectedOrConnecting();
//
//        if (connectivityReceiverListener != null) {
//            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
//        }
//    }
//
//    public static boolean isConnected(){
//        ConnectivityManager  cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        return activeNetwork != null
//                && activeNetwork.isConnectedOrConnecting();
//    }
//
//    public interface ConnectivityReceiverListener{
//        void onNetworkConnectionChanged(boolean isConnected);
//    }
}

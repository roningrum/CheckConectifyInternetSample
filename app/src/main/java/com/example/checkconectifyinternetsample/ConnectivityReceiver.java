package com.example.checkconectifyinternetsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * kelas ini digunakan untuk Broadcast info koneksi ke jaringan
 */
public class ConnectivityReceiver extends BroadcastReceiver {
    //konstanta yang akan digunakan
    public static final String NETWORK_AVAILABLE_ACTION = "com.ajit.singh.NetworkAvailable";
    public static final String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

    //method ini digunakan untuk menerima intent broadcast
    @Override
    public void onReceive(Context context, Intent intent) {
        //intent
        Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
        //nilai konstantanta NETWORK_AVAILABLE_ACTION dikirim ke konteks agar activity dapat megakses kelas ini
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context));
        //Fungsi ini digunakan untuk mengirimkan ke objek lokal dalam proses braodcast
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);
    }

    //method isConnectedToInternet
    private boolean isConnectedToInternet(Context context) {
        //menggunakan try catch karena potensi null pointer exception
        try {
            //jika konteks tidak sama dengan null
            if (context != null) {
                //panggil fungsi Conectivity Manager fugsinya membaca koneksi
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                //disini bisa tahu maa koneksi internet yang aktif
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;

        } catch (Exception e) {
            //jika tidak bisa beri pesan error disistem
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

package com.example.buoi6_bai13_ngotrungvinh_b1709321;

import android.app.Application;

public class WifiApp extends Application {
    static WifiApp wifiInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        wifiInstance = this;
    }
    public static synchronized WifiApp getInstance() {
        return wifiInstance;
    }
}

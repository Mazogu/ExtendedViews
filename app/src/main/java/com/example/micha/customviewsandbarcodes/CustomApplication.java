package com.example.micha.customviewsandbarcodes;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by micha on 2/21/2018.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        else{
            LeakCanary.install(this);
        }
    }
}

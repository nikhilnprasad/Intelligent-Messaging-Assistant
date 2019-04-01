package com.vivekbalachandra.android.ima;

import android.app.Application;
import android.content.Context;

/**
 * Created by vivek on 3/3/17.
 */

public class MyApplication extends Application {
    public MyApplication() {
        super();
    }
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=getApplicationContext();
    }

    public static Context getContext() {
        return appContext;
    }
}

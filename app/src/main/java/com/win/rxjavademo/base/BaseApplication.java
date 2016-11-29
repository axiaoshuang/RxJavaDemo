package com.win.rxjavademo.base;

import android.app.Application;

/**
 * Created by 1234356 on 2016/11/29.
 */

public class BaseApplication extends Application {
    private static  BaseApplication   app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }

    public static BaseApplication getApp() {
        return app;
    }

}

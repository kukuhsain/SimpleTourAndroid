package com.kukuhsain.simpletour.guest;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by kukuh on 08/10/16.
 */

public class SimpleTourApp extends Application {
    private static SimpleTourApp INSTANCE;

    public static SimpleTourApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}

package com.lap.bellapp.bellapp_android;

import android.app.Application;
import android.content.Context;

import com.lap.bellapp.bellapp_android.injection.component.ApplicationComponent;
import com.lap.bellapp.bellapp_android.injection.component.DaggerApplicationComponent;
import com.lap.bellapp.bellapp_android.injection.module.ApplicationModule;

import timber.log.Timber;

/**
 * Created by juangarcia on 10/1/15.
 */
public abstract class BellappApplication extends Application{
    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public abstract String getBaseUrl();

    public static BellappApplication get(Context context) {
        return (BellappApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}

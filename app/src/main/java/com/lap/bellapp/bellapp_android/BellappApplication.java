package com.lap.bellapp.bellapp_android;

import android.app.Application;

import com.lap.bellapp.bellapp_android.presentation.di.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.presentation.di.components.DaggerApplicationComponent;
import com.lap.bellapp.bellapp_android.presentation.di.modules.ApplicationModule;

/**
 * Created by juangarcia on 10/1/15.
 */
public abstract class BellappApplication extends Application{

    public abstract String getBaseUrl();

    private ApplicationComponent applicationComponent;


    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}



package com.lap.bellapp.bellapp_android.data;

import android.content.Context;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.data.remote.BellappService;
import com.lap.bellapp.bellapp_android.injection.component.DaggerDataManagerComponent;
import com.lap.bellapp.bellapp_android.injection.module.DataManagerModule;

import javax.inject.Inject;

import rx.Scheduler;


public class DataManager {

    @Inject protected BellappService mBellappService;
    @Inject protected Scheduler mSubscribeScheduler;

    public DataManager(Context context) {
        injectDependencies(context);
    }

    /* This constructor is provided so we can set up a DataManager with mocks from unit test.
     * At the moment this is not possible to do with Dagger because the Gradle APT plugin doesn't
     * work for the unit test variant, plus Dagger 2 doesn't provide a nice way of overriding
     * modules */
    public DataManager(BellappService bellappService,
                       Scheduler subscribeScheduler) {
        mBellappService = bellappService;
        mSubscribeScheduler = subscribeScheduler;
    }

    protected void injectDependencies(Context context) {
        DaggerDataManagerComponent.builder()
                .applicationComponent(BellappApplication.get(context).getComponent())
                .dataManagerModule(new DataManagerModule(context))
                .build()
                .inject(this);
    }

    public Scheduler getSubscribeScheduler() {
        return mSubscribeScheduler;
    }

}

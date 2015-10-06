package com.lap.bellapp.bellapp_android.injection.module;

import android.content.Context;

import com.lap.bellapp.bellapp_android.data.remote.BellappService;
import com.lap.bellapp.bellapp_android.data.remote.RetrofitHelper;
import com.lap.bellapp.bellapp_android.injection.Scope.PerDataManager;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;
/**
 * Provide dependencies to the DataManager, mainly Helper classes and Retrofit services.
 */
@Module
public class DataManagerModule {

    private final Context mContext;

    public DataManagerModule(Context context) {
        mContext = context;
    }


    @Provides
    @PerDataManager
    BellappService provideBellappService() {
        return new RetrofitHelper().newBellappService();
    }

    @Provides
    @PerDataManager
    Scheduler provideSubscribeScheduler() {
        return Schedulers.io();
    }
}

package com.lap.bellapp.bellapp_android;

import android.app.Application;

import com.lap.bellapp.bellapp_android.data.model.ApplicationType;
import com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.injection.components.DaggerApplicationComponent;
import com.lap.bellapp.bellapp_android.injection.modules.ApplicationModule;
import com.lap.bellapp.bellapp_android.ui.model.MenuItems;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by juangarcia on 10/1/15.
 */
public abstract class BellappApplication extends Application{

    public abstract String getBaseUrl();

    public abstract String getEnvDateParsing();

    public abstract ApplicationType getApplicationType();

    public abstract MenuItems getInitialSelecetdMenuItem();

    private ApplicationComponent applicationComponent;


    @Override public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

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

    public abstract void subscribeToParseChannel(String providerChannel);

    }



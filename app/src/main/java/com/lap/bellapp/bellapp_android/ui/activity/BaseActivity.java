package com.lap.bellapp.bellapp_android.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.injection.modules.ActivityModule;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((BellappApplication)getApplication()).getApplicationComponent();
    }


    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link com.lap.bellapp.bellapp_android.injection.modules.ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}

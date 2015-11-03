package com.lap.bellapp.bellapp_android.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.presentation.di.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.presentation.di.modules.ActivityModule;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link com.lap.bellapp.bellapp_android.presentation.di.components.ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((BellappApplication)getApplication()).getApplicationComponent();
    }


    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link com.lap.bellapp.bellapp_android.presentation.di.modules.ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}

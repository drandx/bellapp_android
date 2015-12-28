package com.lap.bellapp.bellapp_android.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.lap.bellapp.bellapp_android.R;

/**
 * Created by juangarcia on 12/28/15.
 */
public class TermsConditionsFragmentActivity extends BaseActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.terms_conditions_fragment_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }
}

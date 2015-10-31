package com.lap.bellapp.bellapp_android.presentation.view.activity;

import android.os.Bundle;
import android.util.Log;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.presentation.presenters.AppointmentDetailPresenter;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/20/15.
 */
public class AppointmentDetailFragmentActivity extends BaseActivity{

    private static final String ARGUMENT_APPOINTMENT = "org.android10.ARGUMENT_APPOINTMENT";


    @Inject
    AppointmentDetailPresenter appointmentPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_detail_fragmnt_activity);
        this.getApplicationComponent().inject(this);
        int appointmentId = getIntent().getIntExtra(ARGUMENT_APPOINTMENT,-1);
        Log.i("AppointmentDetailFS","***_***");
    }
}

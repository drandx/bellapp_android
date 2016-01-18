package com.lap.bellapp.bellapp_android.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.ui.presenters.Appointment.AppointmentReservePresenter;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentReserveView;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by juanpablogarcia on 1/17/16.
 */
public class AppointmentReserveViewFragmentActivity extends BaseActivity implements AppointmentReserveView {
    private Toolbar mToolbar;
    private TextView textCompanyTitle;
    private TextView textCompanyDescription;
    private TextView textAddress;
    private TextView textNeighborhood;
    private TextView textCity;
    private TextView textServiceTitle;
    private TextView textServiceDuration;
    private TextView textServiceDate;
    private TextView textServiceTime;
    private TextView textAssistantName;
    private Button btnReserve;

    @Inject
    AppointmentReservePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        presenter.setUpView(this);
        setContentView(R.layout.appointment_fragment_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.appointment_screen_title));
        textCompanyTitle = (TextView)findViewById(R.id.textCompanyTitle);
        textCompanyDescription = (TextView)findViewById(R.id.textCompanyDescription);
        textAddress = (TextView)findViewById(R.id.textAddress);
        textNeighborhood = (TextView)findViewById(R.id.textNeighborhood);
        textCity = (TextView)findViewById(R.id.textCity);
        textServiceTitle = (TextView)findViewById(R.id.textServiceTitle);
        textServiceDuration = (TextView)findViewById(R.id.textServiceDuration);
        textAssistantName = (TextView)findViewById(R.id.textAssistantName);
        textServiceTime = (TextView)findViewById(R.id.textTime);
        textServiceDate = (TextView)findViewById(R.id.textDate);
        btnReserve = (Button)findViewById(R.id.btnResevre);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textCompanyTitle.setText(presenter.getLoadedCompany().title);
        textCompanyDescription.setText(presenter.getLoadedCompany().content);
        textAddress.setText(presenter.getLoadedCompany().address);
        textCity.setText(presenter.getLoadedCompany().city);
        textNeighborhood.setText(presenter.getLoadedCompany().neighborhood);
        textServiceTitle.setText(presenter.getBusinessService().title);
        textServiceDuration.setText(String.format(this.getResources().getString(R.string.company_detail_service_duration),presenter.getBusinessService().getMinutesDuration()));
        textAssistantName.setText(presenter.getStaff().firstName + presenter.getStaff().lastName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        textServiceTime.setText(dateFormat.format(presenter.getTimeSlot().getInitialTime()));
        dateFormat = new SimpleDateFormat("EEEE DD, MMMM yyyy");
        textServiceDate.setText(dateFormat.format(presenter.getTimeSlot().getInitialTime()));


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("AppointmentReserveView", "Reservear clicked ...");
            }
        });
    }
}

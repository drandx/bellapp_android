package com.lap.bellapp.bellapp_android.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.MeetingState;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.ui.presenters.Appointment.AppointmentDetailPresenter;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentView;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/20/15.
 */
public class AppointmentDetailFragmentActivity extends BaseActivity implements AppointmentView {

    private static final String ARGUMENT_APPOINTMENT = "org.android10.ARGUMENT_APPOINTMENT";
    private TextView appointmentTitle;
    private TextView appointmentSubTitle;
    private TextView address;
    private TextView neighborhood;
    private TextView city;
    private TextView serviceTitle;
    private TextView date;
    private TextView time;
    private TextView associateName;
    private TextView minutes;
    private TextView status;

    @Inject
    AppointmentDetailPresenter appointmentPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_detail_fragment_activity);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Mis Citas");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.appointmentTitle = (TextView) findViewById(R.id.textBusinessTitle);
        this.appointmentSubTitle = (TextView) findViewById(R.id.textBusinessSubTitle);
        this.address = (TextView) findViewById(R.id.textAddress);
        this.neighborhood = (TextView) findViewById(R.id.textNeighborhood);
        this.city = (TextView) findViewById(R.id.textCity);
        this.date = (TextView) findViewById(R.id.textDate);
        this.time = (TextView) findViewById(R.id.textTime);
        this.associateName = (TextView) findViewById(R.id.textAssoiciateName);
        this.serviceTitle = (TextView)findViewById(R.id.textServiceTitle);
        this.minutes = (TextView)findViewById(R.id.textMinutes);
        this.status = (TextView)findViewById(R.id.textStatusTitle);

        this.getApplicationComponent().inject(this);
        int appointmentId = getIntent().getIntExtra(ARGUMENT_APPOINTMENT, -1);

        Button accept = (Button) findViewById(R.id.buttonAccept);
        MeetingState state = new MeetingState();
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointmentPresenter.confirmAppointment(getIntent().getIntExtra(ARGUMENT_APPOINTMENT, -1),1);
            }
        });

        Button cancel = (Button)findViewById(R.id.buttonDecline);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                appointmentPresenter.confirmAppointment(getIntent().getIntExtra(ARGUMENT_APPOINTMENT, -1), 2);
            }
        });

        this.appointmentPresenter.setAppointmentView(this);
        this.appointmentPresenter.initialize(appointmentId);

        if (getApplicationContext().getPackageName().contains("customer")){
            accept.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadAppointmentInformation(MeetingTime appointment) {
        Log.i("AppointmentDetailFrgA", "--> Meeting id: " + appointment.getMeetingTimeId());
        this.appointmentTitle.setText(appointment.company.title);
        this.appointmentSubTitle.setText(appointment.company.content);
        this.address.setText(appointment.company.address);
        this.neighborhood.setText(appointment.company.neighborhood);
        this.city.setText(appointment.company.city);
        this.serviceTitle.setText(appointment.service.title);

        Log.i("AppointmentDetailFrgA", "--> Service Duration: " + appointment.service.minutesDuration);
        this.minutes.setText("Duracion: "+appointment.service.minutesDuration+" minutos");

        String dayString = new SimpleDateFormat("EEEE dd, MMMM yyyy").format(appointment.startTime);
        String timeString = new SimpleDateFormat("HH:mm aaa").format(appointment.startTime);

        this.date.setText(dayString);
        this.time.setText(timeString);

        String associateName = appointment.customer.firstName + " " + appointment.customer.lastName;
        Log.i("AppointmentDetailFrgA", "--> Associate Name: "+associateName);

        this.associateName.setText(associateName);
    }

    @Override
    public void showConfirmationMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        LinearLayout loader = (LinearLayout) findViewById(R.id.main_progress_container);
        loader.setVisibility(View.GONE);
    }

    @Override
    public void updateMeetingStatus(String stateMessage) {
        this.status.setText(stateMessage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

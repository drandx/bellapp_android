package com.lap.bellapp.bellapp_android.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.ui.adapter.CompanyStaffAdapter;
import com.lap.bellapp.bellapp_android.ui.adapter.TimeSlotsAdapter;
import com.lap.bellapp.bellapp_android.ui.model.TimeSlot;
import com.lap.bellapp.bellapp_android.ui.presenters.Calendar.CalendarPresenter;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentsCalendarView;
import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.calendarview.widget.DayView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by juanpablogarcia on 1/12/16.
 */
public class CalendarFragmentActivity extends BaseActivity implements AppointmentsCalendarView, View.OnClickListener {
    private Toolbar mToolbar;
    private CalendarView calendarView;
    private TextView textSelectedDate;
    private RecyclerView timesListRecycler;
    private TimeSlotsAdapter timeSlotsAdapter;
    private ProgressBar loader;

    @Inject
    CalendarPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_fragment_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.calendar_screen_title));
        textSelectedDate = (TextView) findViewById(R.id.textSelectedDate);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setIsOverflowDateVisible(true);
        calendarView.setCurrentDay(new Date(System.currentTimeMillis()));
        calendarView.setBackButtonColor(R.color.colorAccent);
        calendarView.setNextButtonColor(R.color.colorAccent);
        calendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));
        calendarView.setSelected(true);
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull Date selectedDate) {
                SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
                Toast.makeText(getApplicationContext(), "Selected date is: " + df.format(selectedDate), Toast.LENGTH_SHORT).show();
                textSelectedDate.setText(df.format(selectedDate));
                presenter.setUpSelectedDate(selectedDate);
                presenter.loadServiceAvailableTimes();
            }
        });
        final DayView dayView = calendarView.findViewByDate(new Date(System.currentTimeMillis()));
        if(null != dayView) {
            Toast.makeText(getApplicationContext(), "Today is: " + dayView.getText().toString() + "/" + calendarView.getCurrentMonth() + "/" + calendarView.getCurrentYear(), Toast.LENGTH_SHORT).show();
        }
        timesListRecycler = (RecyclerView)findViewById(R.id.timesList);
        loader = (ProgressBar)findViewById(R.id.time_slots_loader);
        getApplicationComponent().inject(this);
        presenter.setUpView(this);
    }

    @Override
    public void loadStaffTimeSlots(List<TimeSlot> timeSlots) {
        timesListRecycler.setLayoutManager(new LinearLayoutManager(this));
        timeSlotsAdapter = new TimeSlotsAdapter(timeSlots,this,this);
        timesListRecycler.setAdapter(timeSlotsAdapter);

    }

    @Override
    public void showLoadingView() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        Log.i("CalendarView","TimeSlot Clicked...");
    }
}

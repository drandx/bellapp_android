package com.lap.bellapp.bellapp_android.ui.view;

import com.lap.bellapp.bellapp_android.ui.model.TimeSlot;

import java.util.List;

/**
 * Created by juanpablogarcia on 1/16/16.
 */
public interface AppointmentsCalendarView {
    public void loadStaffTimeSlots(List<TimeSlot> timeSlots);
    public void showLoadingView();
    public void hideLoadingView();
}

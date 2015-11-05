package com.lap.bellapp.bellapp_android.presentation.view;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;

/**
 * Created by juangarcia on 10/31/15.
 */
public interface AppointmentView {
    void loadAppointmentInformation(MeetingTime appointment);
    void showConfirmationMessage(String message);
}

package com.lap.bellapp.bellapp_android.ui.view;

import com.lap.bellapp.bellapp_android.data.model.MeetingTime;

/**
 * Created by juangarcia on 10/31/15.
 */
public interface AppointmentView {
    void loadAppointmentInformation(MeetingTime appointment);
    void showConfirmationMessage(String message);
    void hideLoading();
}

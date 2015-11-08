package com.lap.bellapp.bellapp_android.ui.view;

import com.lap.bellapp.bellapp_android.data.model.MeetingTime;

import java.util.List;

/**
 * Created by juangarcia on 10/21/15.
 */
public interface StaffListView {
    public void hideViewLoading();
    public void showErrorMessage(String errorMessage);
    public void showAppointmentsList(List<MeetingTime> appointments);
}

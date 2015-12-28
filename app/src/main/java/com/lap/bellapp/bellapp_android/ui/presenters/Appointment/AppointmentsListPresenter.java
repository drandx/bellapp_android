package com.lap.bellapp.bellapp_android.ui.presenters.Appointment;

import com.lap.bellapp.bellapp_android.ui.model.AppointmentsFilter;
import com.lap.bellapp.bellapp_android.ui.presenters.Presenter;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentsListView;

/**
 * Created by juangarcia on 12/28/15.
 */
public interface AppointmentsListPresenter extends Presenter{
    public void configureListView(AppointmentsListView listView, AppointmentsFilter appointmentsFilter);
    public void loadAppointmentsList(int userId);
}

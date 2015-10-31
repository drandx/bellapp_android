package com.lap.bellapp.bellapp_android.data.repositories;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;
import com.lap.bellapp.bellapp_android.data.repositories.datasource.AppointmentDataStore;
import com.lap.bellapp.bellapp_android.data.repositories.datasource.AppointmentsDataStoreFactory;
import com.lap.bellapp.bellapp_android.domain.repository.AppointmentRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by juangarcia on 10/27/15.
 */
public class AppointmentDataRepository implements AppointmentRepository {

    private final AppointmentsDataStoreFactory appointmentFactory;

    @Inject
    public AppointmentDataRepository(AppointmentsDataStoreFactory appointmentFactory) {
        this.appointmentFactory = appointmentFactory;
    }


    @Override
    public Observable<MeetingTime> getAppointment(int meetingId) {
        AppointmentDataStore appointmentDataStore = this.appointmentFactory.create();
        return appointmentDataStore.getMeetingTime(meetingId);
    }
}

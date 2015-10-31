package com.lap.bellapp.bellapp_android.domain.repository;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;

import rx.Observable;

/**
 * Created by juangarcia on 10/27/15.
 */
public interface AppointmentRepository {
    public Observable<MeetingTime> getAppointment(int meetingId);
}

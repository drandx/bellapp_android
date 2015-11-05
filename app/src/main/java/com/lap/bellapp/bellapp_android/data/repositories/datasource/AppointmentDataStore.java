package com.lap.bellapp.bellapp_android.data.repositories.datasource;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;

import rx.Observable;

/**
 * Created by juangarcia on 10/28/15.
 */
public interface AppointmentDataStore {
    Observable<MeetingTime> getMeetingTime(final int meetingId);
    Observable<MeetingTime> confirmMeetingTIme(final int meetingId);

}

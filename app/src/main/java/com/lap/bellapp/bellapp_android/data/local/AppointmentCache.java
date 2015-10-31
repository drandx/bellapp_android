package com.lap.bellapp.bellapp_android.data.local;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;

import rx.Observable;

/**
 * Created by juangarcia on 10/27/15.
 */
public interface AppointmentCache {

    Observable<MeetingTime> get(final int meetingId);

    void put(MeetingTime meetingTime);

    boolean isCached(final int meetingId);

}

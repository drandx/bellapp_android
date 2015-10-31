package com.lap.bellapp.bellapp_android.data.local;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;
import com.lap.bellapp.bellapp_android.data.exception.AppointmentNotFoundException;

import java.util.Hashtable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by juangarcia on 10/27/15.
 */
public class AppointmentMemoryCache implements AppointmentCache {

    private final Hashtable cachedAppointments;

    private static AppointmentCache instance;

    public static AppointmentCache getInstance(){
        if (instance == null){
            return new AppointmentMemoryCache();
        }
        return instance;
    }

    private AppointmentMemoryCache() {
        this.cachedAppointments = new Hashtable();
    }

    @Override
    public Observable<MeetingTime> get(final int meetingId) {
        return Observable.create(new Observable.OnSubscribe<MeetingTime>() {

            @Override
            public void call(Subscriber<? super MeetingTime> subscriber) {
                MeetingTime loadedMeeting = (MeetingTime) cachedAppointments.get(meetingId);

                if (loadedMeeting != null) {
                    subscriber.onNext(loadedMeeting);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new AppointmentNotFoundException());
                }
            }
        });
    }

    @Override
    public void put(MeetingTime meetingTime) {
        this.cachedAppointments.put(meetingTime.meetingTimeId, meetingTime);
    }

    @Override
    public boolean isCached(int meetingId) {
        if(this.cachedAppointments.get(meetingId) != null){
            return true;
        }
        return false;
    }
}

package com.lap.bellapp.bellapp_android.data.repositories.datasource;

import android.content.Context;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;
import com.lap.bellapp.bellapp_android.data.remote.BellappService;
import com.lap.bellapp.bellapp_android.data.remote.RetrofitHelper;

import rx.Observable;

/**
 * Created by juangarcia on 10/28/15.
 */
public class AppointmentRemoteStore implements AppointmentDataStore {

    private BellappService mServiceAPI;
    private Context context;

    public AppointmentRemoteStore(Context context) {
        mServiceAPI = new RetrofitHelper().newBellappService(context);
        this.context = context;
    }

    @Override
    public Observable<MeetingTime> getMeetingTime(int meetingId) {
        return mServiceAPI.getAppointment(meetingId);
    }

    @Override
    public Observable<MeetingTime> confirmMeetingTIme(int meetingId) {
        return mServiceAPI.confirmAppointment(meetingId);
    }
}

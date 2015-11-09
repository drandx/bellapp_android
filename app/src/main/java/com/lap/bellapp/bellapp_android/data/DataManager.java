package com.lap.bellapp.bellapp_android.data;

import com.google.gson.JsonObject;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.data.remote.BellappService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by juangarcia on 11/4/15.
 */
public class DataManager {

    public final BellappService mServiceAPI;

    @Inject
    public DataManager(BellappService mServiceAPI) {
        this.mServiceAPI = mServiceAPI;
    }

    public Observable<StaffEntity> getStaffEntity(final int staffId) {
        return mServiceAPI.getStaff(staffId).doOnNext(new Action1<StaffEntity>() {
            @Override
            public void call(StaffEntity staffEntity) {
                //staffCache.put(staffEntity);
            }
        });
    }

    public Observable<StaffEntity> getLoginStaff(String email, String password) {
        return mServiceAPI.getLoginStaff(email, password).flatMap(new Func1<StaffEntity, Observable<StaffEntity>>() {
            @Override
            public Observable<StaffEntity> call(StaffEntity staffEntity) {
                return getStaffEntity(staffEntity.staffId);
            }
        });
    }

    public Observable<StaffEntity> updateStaff(final StaffEntity staffEntity) {
        return mServiceAPI.putStaff(staffEntity.staffId, staffEntity).doOnNext(new Action1<StaffEntity>() {
            @Override
            public void call(StaffEntity staffEntity) {
                //staffCache.put(staffEntity);
            }
        });
    }

    public Observable<MeetingTime> getMeetingTime(int meetingId) {
        return mServiceAPI.getAppointment(meetingId);
    }

    public Observable<MeetingTime> confirmMeetingTime(int meetingId, boolean state) {
        JsonObject confirmObj = new JsonObject();
        confirmObj.addProperty("confirmed",state?1:0);
        return mServiceAPI.confirmAppointment(meetingId, confirmObj);
    }

    public Observable<List<MeetingTime>> getUserMeetingTimes(int userId) {
        return mServiceAPI.getStaff(userId).flatMap(new Func1<StaffEntity, Observable<List<MeetingTime>>>() {
            @Override
            public Observable<List<MeetingTime>> call(final StaffEntity staffEntity) {

                Observable<List<MeetingTime>> meetingObservable =
                        Observable.create(new Observable.OnSubscribe<List<MeetingTime>>() {
                                              @Override
                                              public void call(Subscriber<? super List<MeetingTime>> subscriber) {
                                                  subscriber.onNext(staffEntity.getMeetingTimesl());
                                                  subscriber.onCompleted();
                                                  subscriber.onError(new Exception("There was an error getting the user meetings"));
                                              }
                                          }

                        );

                return meetingObservable;
            }
        });
    }
}
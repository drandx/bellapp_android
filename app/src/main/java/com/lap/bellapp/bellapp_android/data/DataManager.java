package com.lap.bellapp.bellapp_android.data;

import com.google.gson.JsonObject;
import com.lap.bellapp.bellapp_android.data.local.PreferencesHelper;
import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;
import com.lap.bellapp.bellapp_android.data.model.BusinessHours;
import com.lap.bellapp.bellapp_android.data.model.CustomerEntity;
import com.lap.bellapp.bellapp_android.data.model.GeneralResult;
import com.lap.bellapp.bellapp_android.data.model.MeetingState;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.MeetingTimeStateEnum;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.data.remote.BellappService;
import com.lap.bellapp.bellapp_android.ui.presenters.Appointment.StaffAppointmentListPresenter;

import java.util.List;
import java.util.Objects;

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
    public final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(BellappService mServiceAPI, PreferencesHelper mPreferencesHelper) {
        this.mServiceAPI = mServiceAPI;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    public PreferencesHelper getmPreferencesHelper() {
        return mPreferencesHelper;
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

    public Observable<MeetingState> getMeetingState(int meetingId){
        return mServiceAPI.getMeetingState(meetingId);
    }

    public Observable<GeneralResult> confirmMeetingTime(int meetingId, MeetingTimeStateEnum state) {
        JsonObject confirmObj = new JsonObject();
        confirmObj.addProperty("confirmed",state.getStateCode());
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
                                                  subscriber.onNext(staffEntity.getMeetingTimes());
                                                  subscriber.onCompleted();
                                                  subscriber.onError(new Exception("There was an error getting the user meetings"));
                                              }
                                          }

                        );

                return meetingObservable;
            }
        });
    }

    public Observable<CustomerEntity> getLoginCustomer(String email, String password) {
        return mServiceAPI.getLoginCustomer(email, password).flatMap(new Func1<CustomerEntity, Observable<CustomerEntity>>() {
            @Override
            public Observable<CustomerEntity> call(CustomerEntity customerEntity) {
                return getCustomerEntity(customerEntity.customerId);
            }
        });
    }

    public Observable<CustomerEntity> getCustomerEntity(final int customerId) {
        return mServiceAPI.getCustomer(customerId).doOnNext(new Action1<CustomerEntity>() {
            @Override
            public void call(CustomerEntity customerEntity) {
                //staffCache.put(staffEntity);
            }
        });
    }

    public Observable<CustomerEntity> updateCustomer(final CustomerEntity customerEntity) {
        return mServiceAPI.putCustomer(customerEntity.customerId, customerEntity).doOnNext(new Action1<CustomerEntity>() {
            @Override
            public void call(CustomerEntity customerEntity) {
                //staffCache.put(staffEntity);
            }
        });
    }

    public Observable<CustomerEntity> postCustomerEntity(CustomerEntity customerEntity){
        return  mServiceAPI.postCustomer(customerEntity);
    }

    public Observable<String> getTermsConditions(){
        return mServiceAPI.getTermsConditions();
    }

    public Observable<List<BusinessCategory>> getBusinessCategories(){
        return mServiceAPI.getBusinessCategories();
    }

    public Observable<List<StaffEntity>> getStaffsByService(int serviceId){
        return mServiceAPI.getStaffsByService(serviceId);
    }

    public Observable<BusinessHours> getBusinessHours(int businessId){
        return mServiceAPI.getBusinessHours(businessId);
    }

    public Observable<Object> postMeetingTime(MeetingTime meetingTime){
        return mServiceAPI.postMeetingTime(meetingTime);
    }


}

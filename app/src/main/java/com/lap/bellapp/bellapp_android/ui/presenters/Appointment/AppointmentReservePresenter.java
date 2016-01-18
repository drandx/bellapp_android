package com.lap.bellapp.bellapp_android.ui.presenters.Appointment;

import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.model.TimeSlot;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentReserveView;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by juanpablogarcia on 1/18/16.
 */
public class AppointmentReservePresenter implements IAppointmentReservePresenter {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final Context context;
    private final DataManager dataManager;
    private Subscription subscription = Subscriptions.empty();
    private AppointmentReserveView view;
    private Company company;
    private BusinessService service;
    private StaffEntity staff;
    private TimeSlot timeSlot;
    String customerId = "com.bellapp.customer.id";

    public AppointmentReservePresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context, DataManager dataManager) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.context = context;
        this.dataManager = dataManager;
    }


    @Override
    public void setUpLoadedCompany(Company company) {
        this.company = company;
    }

    @Override
    public void setUpLoadedService(BusinessService service) {
        this.service = service;
    }

    @Override
    public void setUpLoadedTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public void setUpStaff(StaffEntity staff) {
        this.staff = staff;
    }

    @Override
    public Company getLoadedCompany() {
        return company;
    }

    @Override
    public BusinessService getBusinessService() {
        return service;
    }

    @Override
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    @Override
    public StaffEntity getStaff() {
        return staff;
    }

    @Override
    public void setUpView(AppointmentReserveView view) {
        this.view = view;
    }

    @Override
    public void makeAppointment(MeetingTime meetingTime) {
        subscription = dataManager.postMeetingTime(meetingTime)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ReservePresenter",e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    public void setUpAppointment(){
        String customerIdString = dataManager.getmPreferencesHelper().getString(customerId);
        MeetingTime meetingTime = new MeetingTime(staff.staffId, Integer.parseInt(customerIdString),
                service.id, company.companyId, timeSlot.getInitialTime(), timeSlot.getFinalTime());
        makeAppointment(meetingTime);
    }

    public int getCustomerId(){
        String customerIdString = dataManager.getmPreferencesHelper().getString(customerId);
        return Integer.parseInt(customerIdString);
    }
}

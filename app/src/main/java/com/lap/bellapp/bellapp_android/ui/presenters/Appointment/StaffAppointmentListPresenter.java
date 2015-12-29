package com.lap.bellapp.bellapp_android.ui.presenters.Appointment;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.reactive.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.model.AppointmentsFilter;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentsListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/21/15.
 */
public class StaffAppointmentListPresenter extends DefaultSubscriber<StaffEntity> implements IAppointmentsListPresenter {

    private AppointmentsListView staffListView;
    private AppointmentsFilter filter;
    private DataManager dataManager;

    @Inject
    public StaffAppointmentListPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, DataManager dataManager) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
    }

    @Override
    public void configureListView(AppointmentsListView listView, AppointmentsFilter appointmentsFilter) {
        this.staffListView = listView;
        this.filter = appointmentsFilter;
    }

    @Override
    public void loadAppointmentsList(int userId) {
        this.subscribeToObservable(this.dataManager.getStaffEntity(userId), this);
    }

    @Override
    public void onCompleted() {
        staffListView.hideViewLoading();
    }

    @Override
    public void onError(Throwable e) {
        staffListView.showErrorMessage(e.getMessage());
    }

    @Override
    public void onNext(StaffEntity staffEntity) {
        if(this.filter == AppointmentsFilter.TODAY){
            List<MeetingTime> todayAppointments = new ArrayList<>();
            for (MeetingTime meeting:staffEntity.getMeetingTimes()) {
                Date today = new Date();
                Calendar meetCal = Calendar.getInstance();
                Calendar todayCal = Calendar.getInstance();
                meetCal.setTime(meeting.startTime);
                todayCal.setTime(today);
                if(meetCal.get(Calendar.DAY_OF_YEAR) == todayCal.get(Calendar.DAY_OF_YEAR)){
                    todayAppointments.add(meeting);
                }
            }
            staffListView.showAppointmentsList(todayAppointments);
        }
        else{
            staffListView.showAppointmentsList(staffEntity.getMeetingTimes());
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
    }
}

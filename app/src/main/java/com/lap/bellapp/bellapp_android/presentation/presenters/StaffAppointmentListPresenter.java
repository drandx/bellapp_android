package com.lap.bellapp.bellapp_android.presentation.presenters;

import android.support.annotation.NonNull;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;
import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.domain.interactor.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.domain.interactor.UseCase;
import com.lap.bellapp.bellapp_android.presentation.view.AppointmentsFilter;
import com.lap.bellapp.bellapp_android.presentation.view.StaffListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by juangarcia on 10/21/15.
 */
public class StaffAppointmentListPresenter extends DefaultSubscriber<StaffEntity> implements Presenter {

    private StaffListView staffListView;
    private final UseCase appointmentsUseCase;
    private AppointmentsFilter filter;

    @Inject
    public StaffAppointmentListPresenter(@Named("staffAppointments") UseCase appointmentsUseCase, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.appointmentsUseCase = appointmentsUseCase;
    }

    public void setView(@NonNull StaffListView view) {
        this.staffListView = view;
    }

    public void initiaLize(AppointmentsFilter filter) {
        this.filter = filter;
        this.appointmentsUseCase.execute(this);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        appointmentsUseCase.unsubscribe();
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
            for (MeetingTime meeting:staffEntity.getMeetingTimesl()) {
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
            staffListView.showAppointmentsList(staffEntity.getMeetingTimesl());
        }
    }


}

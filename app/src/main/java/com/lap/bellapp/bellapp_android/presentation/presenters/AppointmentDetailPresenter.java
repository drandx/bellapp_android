package com.lap.bellapp.bellapp_android.presentation.presenters;

import android.util.Log;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;
import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.domain.interactor.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.domain.repository.AppointmentRepository;
import com.lap.bellapp.bellapp_android.presentation.view.AppointmentView;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/27/15.
 */
public class AppointmentDetailPresenter extends DefaultSubscriber<MeetingTime> implements Presenter {

    private AppointmentView appointmentView;
    private final AppointmentRepository appointmentRepository;

    @Inject
    public AppointmentDetailPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, AppointmentRepository appointmentRepository) {
        super(threadExecutor, postExecutionThread);
        this.appointmentRepository = appointmentRepository;
    }

    public void setAppointmentView(AppointmentView appointmentView) {
        this.appointmentView = appointmentView;
    }

    public void initialize(int appointmentId){
        this.subscribeToObservable(this.appointmentRepository.getAppointment(appointmentId), this);
    }

    public void confirmAppointment(int appointmentId, boolean state){
        Log.i("AppointmentPresenter","--> Confirming Appointment" + appointmentId + state);

    }

    @Override public void onCompleted() {
        Log.i("AppointmentPresenter","--> Completed Operation");
    }

    @Override public void onError(Throwable e) {
        Log.e("AppointmentPresenter", "Error getting Appointment Detail");
    }

    @Override public void onNext(MeetingTime appointment) {
        this.appointmentView.loadAppointmentInformation(appointment);
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

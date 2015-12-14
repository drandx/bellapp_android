package com.lap.bellapp.bellapp_android.ui.presenters;

import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.MeetingState;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.MeetingTimeStateEnum;
import com.lap.bellapp.bellapp_android.reactive.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentView;
import com.parse.ParsePush;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by juangarcia on 10/27/15.
 */
public class AppointmentDetailPresenter extends DefaultSubscriber<MeetingTime> implements Presenter {

    private AppointmentView appointmentView;
    private final DataManager dataManager;
    private Context context;
    private MeetingTime loadedMeetingTime;
    private MeetingState meetingStatus;

    @Inject
    public AppointmentDetailPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, DataManager dataManager, Context context) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
        this.context = context;
    }

    public void setAppointmentView(AppointmentView appointmentView) {
        this.appointmentView = appointmentView;
    }

    public void initialize(int appointmentId){
        this.subscribeToObservable(this.dataManager.getMeetingTime(appointmentId), this);


        Subscriber<MeetingState> stateSubscriber = new Subscriber<MeetingState>() {
            @Override
            public void onNext(MeetingState s) {
                meetingStatus = s;
                appointmentView.updateMeetingStatus(s);
            }

            @Override
            public void onCompleted() {
                Log.e("AppointmentDPresenter","Completed!!!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("AppointmentDPresenter", e.getMessage());
                MeetingState fakeState = new MeetingState();
                fakeState.setConfirmed(0);
                appointmentView.updateMeetingStatus(fakeState);
            }
        };

        this.dataManager.getMeetingState(appointmentId)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(stateSubscriber);

    }

    public void confirmAppointment(int appointmentId, boolean state) {
        Log.i("AppointmentPresenter", "--> Confirming Appointment" + appointmentId + state);

        this.subscribeToObservable(this.dataManager.confirmMeetingTime(appointmentId, state), new Observer<MeetingTime>() {

            @Override
            public void onCompleted() {
                Log.i("AppointmentDetailPr", "onCompleted Confirm Meeting");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("AppointmentDetailPr", "onError");
                appointmentView.showConfirmationMessage("Error confirmando la cita");
            }

            @Override
            public void onNext(MeetingTime meetingTime) {
                appointmentView.showConfirmationMessage("La cita fue actualizada con exito");

                ParsePush push = new ParsePush();
                push.setChannel("customer_" + loadedMeetingTime.customerId);
                String dayString = new SimpleDateFormat("EEEE dd, MMMM yyyy - HH:MM aaa").format(loadedMeetingTime.startTime);
                push.setMessage(String.format(context.getResources().getString(R.string.appointments_status_push_message), dayString,
                        meetingStatus == null ? MeetingTimeStateEnum.PENDING_CONFIRMATION.getDescription(context) : meetingStatus.getState().getDescription(context)));
                push.sendInBackground();
            }
        });

    }

    @Override public void onCompleted() {
        Log.i("AppointmentPresenter","--> Completed Operation");
        appointmentView.hideLoading();
    }

    @Override public void onError(Throwable e) {
        Log.e("AppointmentPresenter", "Error getting Appointment Detail");
    }

    @Override public void onNext(MeetingTime appointment) {
        this.appointmentView.loadAppointmentInformation(appointment);
        this.loadedMeetingTime = appointment;
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

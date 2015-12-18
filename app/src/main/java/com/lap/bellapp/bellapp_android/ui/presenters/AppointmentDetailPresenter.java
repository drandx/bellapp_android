package com.lap.bellapp.bellapp_android.ui.presenters;

import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.GeneralResult;
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
        this.getMeetingStatus(appointmentId);
    }

    private void getMeetingStatus(int appointmentId){
        Subscriber<MeetingState> stateSubscriber = new Subscriber<MeetingState>() {
            @Override
            public void onNext(MeetingState s) {
                String statusMessage = MeetingTimeStateEnum.findByCode(s.getConfirmed()).getDescription(context);
                appointmentView.updateMeetingStatus(statusMessage);
                Log.i("AppointmentDetailPresenter", "**Next!" + 1 + " - " + statusMessage);

            }

            @Override
            public void onCompleted() {
                Log.i("AppointmentDetailPresenter","Completed!!!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("AppointmentDetailPresenter", "Error getting status");
                //appointmentView.updateMeetingStatus(MeetingTimeStateEnum.PENDING_CONFIRMATION.getDescription(context));
            }
        };

        this.dataManager.getMeetingState(appointmentId)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(stateSubscriber);
    }

    public void confirmAppointment(final int appointmentId, final int state) {
        Log.i("AppointmentDetailPresenter", "--> Confirming Appointment" + appointmentId + state);

        Subscriber<GeneralResult> confirmSubscriber = new Subscriber<GeneralResult>() {
            @Override
            public void onNext(GeneralResult s) {
                appointmentView.showConfirmationMessage(s.getMessage());
                //appointmentView.updateMeetingStatus(MeetingTimeStateEnum.findByCode(state).getDescription(context));
                //loadedMeetingTime.state = MeetingTimeStateEnum.findByCode(state);

                getMeetingStatus(appointmentId);

                ParsePush push = new ParsePush();
                push.setChannel("customer_" + loadedMeetingTime.customerId);
                String dayString = new SimpleDateFormat("EEEE dd, MMMM yyyy - HH:MM aaa").format(loadedMeetingTime.startTime);
                push.setMessage(String.format(context.getResources().getString(R.string.appointments_status_push_message), dayString,
                        loadedMeetingTime.state == null ? MeetingTimeStateEnum.PENDING_CONFIRMATION.getDescription(context) : loadedMeetingTime.state.getDescription(context)));
                push.sendInBackground();
            }

            @Override
            public void onCompleted() {
                Log.i("AppointmentDetailPresenter", "onCompleted Confirm Meeting");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("AppointmentDetailPresenter", "onError");
                appointmentView.showConfirmationMessage("Error confirmando la cita");
            }
        };

        this.dataManager.confirmMeetingTime(appointmentId, MeetingTimeStateEnum.findByCode(state))
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(confirmSubscriber);
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

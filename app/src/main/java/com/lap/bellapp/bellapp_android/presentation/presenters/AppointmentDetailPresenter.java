package com.lap.bellapp.bellapp_android.presentation.presenters;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.domain.interactor.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.domain.repository.AppointmentRepository;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/27/15.
 */
public class AppointmentDetailPresenter extends DefaultSubscriber<StaffEntity> implements Presenter {

    private final AppointmentRepository appointmentRepository;

    @Inject
    public AppointmentDetailPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, AppointmentRepository appointmentRepository) {
        super(threadExecutor, postExecutionThread);
        this.appointmentRepository = appointmentRepository;
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

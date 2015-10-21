package com.lap.bellapp.bellapp_android.presentation.presenters;

import android.support.annotation.NonNull;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.domain.interactor.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.domain.interactor.UseCase;
import com.lap.bellapp.bellapp_android.presentation.view.StaffListView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by juangarcia on 10/21/15.
 */
public class StaffAppointmentListPresenter extends DefaultSubscriber<StaffEntity> implements Presenter {

    private StaffListView staffListView;
    private final UseCase appointmentsUseCase;

    @Inject
    public StaffAppointmentListPresenter(@Named("staffAppointments") UseCase appointmentsUseCase) {
        this.appointmentsUseCase = appointmentsUseCase;
    }

    public void setView(@NonNull StaffListView view) {
        this.staffListView = view;
    }

    public void initiaLize(){
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

    }

    @Override public void onCompleted() {
        staffListView.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
        staffListView.showErrorMessage(e.getMessage());
    }

    @Override
    public void onNext(StaffEntity staffEntity) {
        staffListView.showAppointmentsList(staffEntity.getMeetingTimesl());
    }


}

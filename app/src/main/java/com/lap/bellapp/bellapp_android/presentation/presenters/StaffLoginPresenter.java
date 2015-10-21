package com.lap.bellapp.bellapp_android.presentation.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.domain.interactor.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.domain.interactor.UseCase;
import com.lap.bellapp.bellapp_android.presentation.di.PerActivity;
import com.lap.bellapp.bellapp_android.presentation.view.StaffLoginView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by juangarcia on 10/17/15.
 */
@PerActivity
public class StaffLoginPresenter extends DefaultSubscriber<StaffEntity> implements Presenter{
    private final UseCase loginStaffUseCase;
    private StaffLoginView loginView;

    public void initialize() {
        this.getUserDetails();
    }

    @Inject
    public StaffLoginPresenter(@Named("loginStaff") UseCase loginuseCase) {
        this.loginStaffUseCase = loginuseCase;
    }

    public void setView(@NonNull StaffLoginView view) {
        this.loginView = view;
    }

    @Override public void onCompleted() {
        loginView.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
        loginView.hideViewLoading();
        loginView.showErrorMessage(e.getMessage());
        Log.e("StaffLoginPresenter",e.getMessage());
    }

    @Override public void onNext(StaffEntity users) {
        Log.i("StaffLoginPresenter", "onNext");
        Log.i("StaffLoginPresenter", users.toString());
        loginView.navigateToNextStep();
    }

    private void getUserDetails() {
        this.loginStaffUseCase.execute(this);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.loginStaffUseCase.unsubscribe();
    }
}

package com.lap.bellapp.bellapp_android.presentation.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
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

    @Inject
    public StaffLoginPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, @Named("loginStaff") UseCase loginuseCase) {
        super(threadExecutor, postExecutionThread);
        this.loginStaffUseCase = loginuseCase;
    }

    public void initialize() {
        this.getUserDetails();
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
        Log.e("StaffLoginPresenter","Request Error " + e.getMessage());
    }

    @Override public void onNext(StaffEntity staffEntity) {
        Log.i("StaffLoginPresenter", "onNext");
        Log.i("StaffLoginPresenter", staffEntity.toString());
        loginView.navigateToNextStep(staffEntity);
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

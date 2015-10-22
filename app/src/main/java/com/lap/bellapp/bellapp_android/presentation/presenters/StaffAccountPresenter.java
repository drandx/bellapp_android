package com.lap.bellapp.bellapp_android.presentation.presenters;

import android.util.Log;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.domain.interactor.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.domain.repository.StaffRepository;
import com.lap.bellapp.bellapp_android.presentation.view.StaffAccountView;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/22/15.
 */
public class StaffAccountPresenter extends DefaultSubscriber<StaffEntity> implements Presenter{

    public StaffRepository staffRepository;
    private StaffAccountView accountView;

    @Inject
    public StaffAccountPresenter(ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread,
                                 StaffRepository staffRepository) {
        super(threadExecutor, postExecutionThread);
        this.staffRepository = staffRepository;
    }

    public void setAccountView(StaffAccountView accountView) {
        this.accountView = accountView;
    }

    public void initialize(int userId){
        Log.i("StaffAccountPresenter", "Init Staff Account Preseneter UserId"+userId);
        this.subscribeToObservable(this.staffRepository.getUser(userId), this);
    }

    @Override public void onCompleted() {
        Log.i("StaffAccountPresenter","onCompleted");
    }

    @Override public void onError(Throwable e) {
        Log.i("StaffAccountPresenter","onCompleted");
    }

    @Override public void onNext(StaffEntity t) {
        Log.i("StaffAccountPresenter","onNext");
        this.accountView.loadStaffAccount(t);
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

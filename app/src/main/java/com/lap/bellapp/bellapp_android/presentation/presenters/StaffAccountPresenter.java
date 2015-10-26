package com.lap.bellapp.bellapp_android.presentation.presenters;

import android.util.Log;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.domain.interactor.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.domain.repository.StaffRepository;
import com.lap.bellapp.bellapp_android.presentation.view.StaffAccountView;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by juangarcia on 10/22/15.
 */
public class StaffAccountPresenter extends DefaultSubscriber<StaffEntity> implements Presenter{

    public StaffRepository staffRepository;

    private StaffAccountView accountView;
    private StaffEntity loadedStaff;

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
        this.subscribeToObservable(this.staffRepository.getUser(userId), this);
    }

    public void updateModel(StaffEntity staffEntity){
        this.loadedStaff = staffEntity;
        this.subscribeToObservable(this.staffRepository.updateStaff(this.loadedStaff), new Observer<StaffEntity>() {
            @Override
            public void onCompleted() {
                Log.i("StaffAccountPresenter","Completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("StaffAccountPresenter",e.getMessage());
            }

            @Override
            public void onNext(StaffEntity staffEntity) {
                Log.i("StaffAccountPresenter","onNextt");

            }
        });
    }

    @Override public void onCompleted() {
        Log.i("StaffAccountPresenter","onCompleted");
        this.unsubscribeFromObservable();
    }

    @Override public void onError(Throwable e) {
        Log.i("StaffAccountPresenter","onCompleted");
    }

    @Override public void onNext(StaffEntity t) {
        Log.i("StaffAccountPresenter","onNext");
        this.loadedStaff = t;
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

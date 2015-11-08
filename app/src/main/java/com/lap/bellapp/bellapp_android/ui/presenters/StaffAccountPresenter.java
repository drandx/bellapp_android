package com.lap.bellapp.bellapp_android.ui.presenters;

import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.reactive.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.view.StaffAccountView;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by juangarcia on 10/22/15.
 */
public class StaffAccountPresenter extends DefaultSubscriber<StaffEntity> implements Presenter{

    public DataManager dataManager;

    private StaffAccountView accountView;
    private StaffEntity loadedStaff;
    private Context context;

    @Inject
    public StaffAccountPresenter(ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread,
                                 DataManager dataManager, Context context) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
        this.context = context;
    }

    public void setAccountView(StaffAccountView accountView) {
        this.accountView = accountView;
    }

    public void initialize(int userId){
        // TODO - Critical
        this.subscribeToObservable(this.dataManager.getStaffEntity(userId), this);
    }

    public void updateModel(StaffEntity staffEntity){
        this.loadedStaff = staffEntity;
        this.subscribeToObservable(this.dataManager.updateStaff(this.loadedStaff), new Observer<StaffEntity>() {
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
                accountView.showUpdateMessage(context.getString(R.string.account_update_sucess_message));

            }
        });
    }

    @Override public void onCompleted() {
        Log.i("StaffAccountPresenter","onCompleted");
        //TODO - Critical
        //this.unsubscribeFromObservable();
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
        //TODO - Critical
        //this.unsubscribeFromObservable();
    }
}

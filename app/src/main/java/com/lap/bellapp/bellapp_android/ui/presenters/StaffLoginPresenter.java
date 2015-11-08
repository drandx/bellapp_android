package com.lap.bellapp.bellapp_android.ui.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.reactive.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.view.StaffLoginView;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by juangarcia on 10/17/15.
 */
public class StaffLoginPresenter extends DefaultSubscriber<StaffEntity> implements Presenter{
    private final DataManager dataManager;
    private StaffLoginView loginView;
    private Context context;

    @Inject
    public StaffLoginPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, DataManager dataManager, Context context) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
        this.context = context;
    }

    public void initialize() {
    }

    public void staffLogin(String email, String password){
        this.subscribeToObservable(this.dataManager.getLoginStaff(email, password), new Observer<StaffEntity>() {
            @Override
            public void onCompleted() {
                loginView.hideViewLoading();
            }

            @Override
            public void onError(Throwable e) {
                loginView.hideViewLoading();
                loginView.showErrorMessage(context.getString(R.string.login_error_message));
                Log.e("StaffLoginPresenter", "Request Error " + e.getMessage());
            }

            @Override
            public void onNext(StaffEntity staffEntity) {
                Log.i("StaffLoginPresenter", "onNext");
                Log.i("StaffLoginPresenter", staffEntity.toString());
                ((BellappApplication) context.getApplicationContext()).subscribeToParseChannel("provider_" + staffEntity.getStaffId());
                loginView.navigateToNextStep(staffEntity);
            }
        });
    }

    public void setView(@NonNull StaffLoginView view) {
        this.loginView = view;
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

package com.lap.bellapp.bellapp_android.ui.presenters.Login;

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
import com.lap.bellapp.bellapp_android.ui.view.LoginView;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by juangarcia on 10/17/15.
 */
public class StaffLoginPresenter extends DefaultSubscriber<StaffEntity> implements ILoginPresenter {
    private final DataManager dataManager;
    private LoginView loginView;
    private Context context;
    String asistantEmail = "com.bellapp.assistant.email";
    String asistantPassword = "com.bellapp.assistant.password";



    @Inject
    public StaffLoginPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, DataManager dataManager, Context context) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
        this.context = context;
    }

    public void authenticateUser(final String email, final String password){
        this.subscribeToObservable(this.dataManager.getLoginStaff(email, password), new Observer<StaffEntity>() {
            @Override
            public void onCompleted() {
                loginView.hideViewLoading();
                dataManager.getmPreferencesHelper().putString(asistantEmail, email);
                dataManager.getmPreferencesHelper().putString(asistantPassword,password);
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
                loginView.navigateToNextStep(staffEntity.staffId);
            }
        });
    }

    public void setView(@NonNull LoginView view) {
        this.loginView = view;
        String email = dataManager.getmPreferencesHelper().getString(asistantEmail);
        String password = dataManager.getmPreferencesHelper().getString(asistantPassword);
        loginView.setUpDefaultValues(email,password);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.unsibscribeOperations();
    }
}

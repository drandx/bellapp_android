package com.lap.bellapp.bellapp_android.ui.presenters.Login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.CustomerEntity;
import com.lap.bellapp.bellapp_android.reactive.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.view.LoginView;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by juangarcia on 10/17/15.
 */
public class CustomerLoginPresenter extends DefaultSubscriber<CustomerEntity> implements LoginPresenter {
    private final DataManager dataManager;
    private LoginView loginView;
    private Context context;
    String customerEmail = "com.bellapp.customer.email";
    String customerPassword = "com.bellapp.customer.password";

    @Inject
    public CustomerLoginPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, DataManager dataManager, Context context) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
        this.context = context;
    }

    public void authenticateUser(final String email, final String password){
        this.subscribeToObservable(this.dataManager.getLoginCustomer(email, password), new Observer<CustomerEntity>() {
            @Override
            public void onCompleted() {
                loginView.hideViewLoading();
                dataManager.getmPreferencesHelper().putString(customerEmail, email);
                dataManager.getmPreferencesHelper().putString(customerPassword,password);
            }

            @Override
            public void onError(Throwable e) {
                loginView.hideViewLoading();
                loginView.showErrorMessage(context.getString(R.string.login_error_message));
                Log.e("CustomerLoginPresenter", "Request Error " + e.getMessage());
            }

            @Override
            public void onNext(CustomerEntity customerEntity) {
                Log.i("CustomerLoginPresenter", "onNext");
                Log.i("CustomerLoginPresenter", customerEntity.toString());
                ((BellappApplication) context.getApplicationContext()).subscribeToParseChannel("provider_" + customerEntity.getCustomerId());
                loginView.navigateToNextStep(customerEntity.customerId);
            }
        });
    }

    public void setView(@NonNull LoginView view) {
        this.loginView = view;
        String email = dataManager.getmPreferencesHelper().getString(customerEmail);
        String password = dataManager.getmPreferencesHelper().getString(customerPassword);
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

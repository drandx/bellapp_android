package com.lap.bellapp.bellapp_android.ui.presenters.Account;

import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.CustomerEntity;
import com.lap.bellapp.bellapp_android.reactive.DefaultSubscriber;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.model.UserDetail;
import com.lap.bellapp.bellapp_android.ui.view.AccountView;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by juangarcia on 10/22/15.
 */
public class CustomerAccountPresenter extends DefaultSubscriber<CustomerEntity> implements IAccountPresenter {

    public DataManager dataManager;

    private AccountView accountView;
    private CustomerEntity loadedUser;
    private Context context;

    String customerPassword = "com.bellapp.customer.password";

    @Inject
    public CustomerAccountPresenter(ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread,
                                 DataManager dataManager, Context context) {
        super(threadExecutor, postExecutionThread);
        this.dataManager = dataManager;
        this.context = context;
    }

    public void configureAccountView(AccountView accountView) {
        this.accountView = accountView;
    }

    public void loadAccountDetails(int userId){
        this.subscribeToObservable(this.dataManager.getCustomerEntity(userId), this);
    }

    public void updateAccountDetails(UserDetail userDetails){
        this.loadedUser = new CustomerEntity(userDetails.userId, userDetails.email,
                userDetails.password, userDetails.firstName, userDetails.lastName,
                userDetails.phoneNumber);
        this.subscribeToObservable(this.dataManager.updateCustomer(this.loadedUser), new Observer<CustomerEntity>() {
            @Override
            public void onCompleted() {
                Log.i("StaffAccountPresenter","Completed");
                accountView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("StaffAccountPresenter",e.getMessage());
            }

            @Override
            public void onNext(CustomerEntity customerEntity) {
                Log.i("StaffAccountPresenter","onNextt");
                dataManager.getmPreferencesHelper().putString(customerPassword, userDetails.password);
                accountView.loadAccountDetails(new UserDetail(customerEntity.customerId, customerEntity.email, userDetails.password, customerEntity.phoneNumber, customerEntity.lastName, customerEntity.firstName));
                accountView.showUpdateMessage(context.getString(R.string.account_update_sucess_message));

            }
        });
    }

    @Override public void onCompleted() {
        Log.i("StaffAccountPresenter","onCompleted");
        //TODO - Critical
        //this.unsubscribeFromObservable();
        this.accountView.hideLoading();
    }

    @Override public void onError(Throwable e) {
        Log.i("StaffAccountPresenter","onCompleted");
    }

    @Override public void onNext(CustomerEntity t) {
        Log.i("StaffAccountPresenter","onNext");
        String password = dataManager.getmPreferencesHelper().getString(customerPassword);
        this.loadedUser = t;
        this.accountView.loadAccountDetails(new UserDetail(t.customerId, t.email, password, t.phoneNumber, t.lastName, t.firstName));
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

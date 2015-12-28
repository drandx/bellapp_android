package com.lap.bellapp.bellapp_android.ui.presenters.Account;

import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
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
public class StaffAccountPresenter extends DefaultSubscriber<StaffEntity> implements AccountPresenter {

    public DataManager dataManager;

    private AccountView accountView;
    private StaffEntity loadedUser;
    private Context context;

    @Inject
    public StaffAccountPresenter(ThreadExecutor threadExecutor,
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
        this.subscribeToObservable(this.dataManager.getStaffEntity(userId), this);
    }

    public void updateAccountDetails(UserDetail userDetails){
        this.loadedUser = new StaffEntity(userDetails.userId, userDetails.email,
                userDetails.password, userDetails.firstName, userDetails.lastName,
                userDetails.phoneNumber);
        this.subscribeToObservable(this.dataManager.updateStaff(this.loadedUser), new Observer<StaffEntity>() {
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
            public void onNext(StaffEntity staffEntity) {
                Log.i("StaffAccountPresenter","onNextt");
                accountView.loadAccountDetails(new UserDetail(staffEntity.staffId, staffEntity.email, staffEntity.phoneNumber, staffEntity.password, staffEntity.lastName, staffEntity.firstName));
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

    @Override public void onNext(StaffEntity t) {
        Log.i("StaffAccountPresenter","onNext");
        this.loadedUser = t;
        this.accountView.loadAccountDetails(new UserDetail(t.staffId, t.email, t.phoneNumber, t.password, t.lastName, t.firstName));
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

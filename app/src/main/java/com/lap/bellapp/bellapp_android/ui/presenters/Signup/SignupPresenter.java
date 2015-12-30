package com.lap.bellapp.bellapp_android.ui.presenters.Signup;

import android.content.Context;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.CustomerEntity;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.view.SignUpView;
import com.lap.bellapp.bellapp_android.util.Utils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by juangarcia on 12/29/15.
 */
public class SignupPresenter implements ISignupPresenter {


    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private Subscription subscription = Subscriptions.empty();

    private SignUpView view;
    private Context context;
    private DataManager dataManager;


    @Inject
    public SignupPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
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

    @Override
    public void configureView(SignUpView view) {
        this.view = view;
    }

    @Override
    public void submitInformation(String email, String password, String firstName, String lastName, String phoneNumber, String gender, boolean termsConditions) {
        if (email.length() > 0 && password.length() > 0 &&
                password.length() > 0 && lastName.length() > 0 && firstName.length() > 0
                && gender.length() > 0) {

            if (Utils.isValidEmail(email) && Utils.isValidPhoneNumber(phoneNumber)) {

                if (termsConditions) {
                    Subscriber<CustomerEntity> mySubscriber = new Subscriber<CustomerEntity>() {
                        @Override
                        public void onNext(CustomerEntity s) {
                            System.out.println(s);
                        }

                        @Override
                        public void onCompleted() {
                            view.showSuccessMessage(context.getResources().getString(R.string.form_message_success_validation));
                            view.navigateToNextStep();
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showErrorMessage(context.getResources().getString(R.string.form_message_existing_email_validation));
                            return;
                        }
                    };
                    CustomerEntity customerEntity = new CustomerEntity(email, password, firstName, lastName, phoneNumber, gender);

                    this.subscription = this.dataManager.postCustomerEntity(customerEntity)
                            .subscribeOn(Schedulers.from(threadExecutor))
                            .observeOn(postExecutionThread.getScheduler())
                            .subscribe(mySubscriber);

                    return;
                } else {
                    view.showErrorMessage(context.getResources().getString(R.string.form_message_terms_conditions_validation));
                    return;
                }

            }
        }
        view.showErrorMessage(context.getResources().getString(R.string.form_message_error_validation));
    }

}

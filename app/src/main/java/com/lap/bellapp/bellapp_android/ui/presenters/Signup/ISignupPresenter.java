package com.lap.bellapp.bellapp_android.ui.presenters.Signup;

import com.lap.bellapp.bellapp_android.ui.presenters.Presenter;
import com.lap.bellapp.bellapp_android.ui.view.SignUpView;

/**
 * Created by juangarcia on 12/29/15.
 */
public interface ISignupPresenter extends Presenter{
    public void configureView(SignUpView view);
    public void submitInformation(String email, String password, String firstName, String lastName, String phoneNumber, String gender, boolean termsConditions);
}

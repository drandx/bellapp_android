package com.lap.bellapp.bellapp_android.ui.view;

/**
 * Created by juangarcia on 10/17/15.
 */
public interface LoginView {
    public void hideViewLoading();
    public void showErrorMessage(String errorMessage);
    public void navigateToNextStep(int userId);
    public void setUpDefaultValues(String email, String password);
}

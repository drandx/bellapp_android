package com.lap.bellapp.bellapp_android.ui.view;

/**
 * Created by juangarcia on 12/29/15.
 */
public interface SignUpView {
    public void hideViewLoading();
    public void showErrorMessage(String errorMessage);
    public void showSuccessMessage(String successMessage);
    public void navigateToNextStep();
}

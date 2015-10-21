package com.lap.bellapp.bellapp_android.presentation.view;

/**
 * Created by juangarcia on 10/17/15.
 */
public interface StaffLoginView {
    public void hideViewLoading();
    public void showErrorMessage(String errorMessage);
    public void navigateToNextStep();
}

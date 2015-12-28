package com.lap.bellapp.bellapp_android.ui.presenters.Login;

import android.support.annotation.NonNull;

import com.lap.bellapp.bellapp_android.ui.presenters.Presenter;
import com.lap.bellapp.bellapp_android.ui.view.LoginView;

/**
 * Created by juangarcia on 12/27/15.
 */
public interface LoginPresenter extends Presenter {
    public void authenticateUser(final String email, final String password);
    public void setView(@NonNull LoginView view);
}
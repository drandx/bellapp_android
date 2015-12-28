package com.lap.bellapp.bellapp_android.util;

import com.lap.bellapp.bellapp_android.ui.presenters.Login.LoginPresenter;

/**
 * Created by juangarcia on 12/27/15.
 */
public class PresentersFactory {

    LoginPresenter loginPresenter;

    public PresentersFactory(LoginPresenter loginPresenter){
        this.loginPresenter = loginPresenter;
    }

    public LoginPresenter getLoginPresenter(){
        return loginPresenter;
    }
}

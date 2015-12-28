package com.lap.bellapp.bellapp_android.util;

import com.lap.bellapp.bellapp_android.ui.presenters.Account.AccountPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Appointment.AppointmentsListPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Login.LoginPresenter;

/**
 * Created by juangarcia on 12/27/15.
 */
public class PresentersFactory {

    LoginPresenter loginPresenter;
    AccountPresenter accountPresenter;
    AppointmentsListPresenter appointmentsListPresenter;

    public PresentersFactory(LoginPresenter loginPresenter, AccountPresenter accountPresenter, AppointmentsListPresenter appointmentsListPresenter){
        this.loginPresenter = loginPresenter;
        this.accountPresenter = accountPresenter;
        this.appointmentsListPresenter = appointmentsListPresenter;
    }

    public LoginPresenter getLoginPresenter(){
        return loginPresenter;
    }

    public AccountPresenter getAccountPresenter() {
        return accountPresenter;
    }

    public AppointmentsListPresenter getAppointmentsListPresenter() {
        return appointmentsListPresenter;
    }
}

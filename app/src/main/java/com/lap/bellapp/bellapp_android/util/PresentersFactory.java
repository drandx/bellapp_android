package com.lap.bellapp.bellapp_android.util;

import com.lap.bellapp.bellapp_android.ui.presenters.Account.IAccountPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Appointment.IAppointmentsListPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Login.ILoginPresenter;

/**
 * Created by juangarcia on 12/27/15.
 */
public class PresentersFactory {

    ILoginPresenter loginPresenter;
    IAccountPresenter accountPresenter;
    IAppointmentsListPresenter appointmentsListPresenter;

    public PresentersFactory(ILoginPresenter loginPresenter, IAccountPresenter accountPresenter, IAppointmentsListPresenter appointmentsListPresenter){
        this.loginPresenter = loginPresenter;
        this.accountPresenter = accountPresenter;
        this.appointmentsListPresenter = appointmentsListPresenter;
    }

    public ILoginPresenter getLoginPresenter(){
        return loginPresenter;
    }

    public IAccountPresenter getAccountPresenter() {
        return accountPresenter;
    }

    public IAppointmentsListPresenter getAppointmentsListPresenter() {
        return appointmentsListPresenter;
    }
}

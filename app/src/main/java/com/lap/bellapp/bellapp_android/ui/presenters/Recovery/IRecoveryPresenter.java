package com.lap.bellapp.bellapp_android.ui.presenters.Recovery;

import android.support.annotation.NonNull;

import com.lap.bellapp.bellapp_android.ui.presenters.Presenter;
import com.lap.bellapp.bellapp_android.ui.view.RecoveryView;

/**
 * Created by HECTOR DAMIAN on 20/02/2016.
 */
public interface IRecoveryPresenter extends Presenter {
    public void recoveryUserPassword(final String email);
    public void setView(@NonNull RecoveryView view);
}

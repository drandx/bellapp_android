package com.lap.bellapp.bellapp_android.ui.view;

import com.lap.bellapp.bellapp_android.ui.model.UserDetail;

/**
 * Created by juangarcia on 10/22/15.
 */
public interface AccountView {
    public void loadAccountDetails(UserDetail staffEntity);
    public void showUpdateMessage(String message);
    public void hideLoading();

}

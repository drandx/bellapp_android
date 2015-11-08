package com.lap.bellapp.bellapp_android.ui.view;

import com.lap.bellapp.bellapp_android.data.model.StaffEntity;

/**
 * Created by juangarcia on 10/22/15.
 */
public interface StaffAccountView {
    public void loadStaffAccount(StaffEntity staffEntity);
    public void showUpdateMessage(String message);

}

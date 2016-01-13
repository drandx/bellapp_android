package com.lap.bellapp.bellapp_android.ui.view;

import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;

import java.util.List;

/**
 * Created by juanpablogarcia on 1/12/16.
 */
public interface CompanyStaffView {
    public void loadStaff(List<StaffEntity> staff);
    public void showLoadingView();
    public void hideLoadingView();
}

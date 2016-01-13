package com.lap.bellapp.bellapp_android.ui.presenters.Company;

import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.ui.view.CompanyStaffView;

/**
 * Created by juanpablogarcia on 1/12/16.
 */
public interface ICompanyStaffPresenter {
    public void setUpLoadedCompany(Company company);
    public void setUpLoadedService(BusinessService service);
    public Company getLoadedCompany();
    public BusinessService getloadedService();
    public void loadStaff();
    public void configureView(CompanyStaffView view);
}

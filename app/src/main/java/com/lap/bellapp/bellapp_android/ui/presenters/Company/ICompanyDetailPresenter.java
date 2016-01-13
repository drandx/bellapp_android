package com.lap.bellapp.bellapp_android.ui.presenters.Company;

import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;

/**
 * Created by juanpablogarcia on 1/9/16.
 */
public interface ICompanyDetailPresenter {
    public void setUpLoadedCompany(Company company);
    public BusinessService getServiceByPosition(int position);
    public Company getLoadedCompany();
}

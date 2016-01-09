package com.lap.bellapp.bellapp_android.ui.presenters.Company;

import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.ui.view.CompaniesListView;

import java.util.List;

/**
 * Created by juangarcia on 1/4/16.
 */
public interface ICompanyListPresenter {
    public void configureView(CompaniesListView view);
    public void getCompanies();
    public void setUpCompanies(List<Company> companies);
    public Company getCompanyByPosition(int position);
}

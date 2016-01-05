package com.lap.bellapp.bellapp_android.ui.presenters.Company;

import com.lap.bellapp.bellapp_android.ui.view.CompaniesListView;

/**
 * Created by juangarcia on 1/4/16.
 */
public interface ICompanyListPresenter {
    public void configureView(CompaniesListView view);
    public void loadCompanies(int categoryId);
}

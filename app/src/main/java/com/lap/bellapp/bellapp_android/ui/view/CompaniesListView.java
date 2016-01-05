package com.lap.bellapp.bellapp_android.ui.view;

import com.lap.bellapp.bellapp_android.data.model.Company;

import java.util.List;

/**
 * Created by juangarcia on 1/4/16.
 */
public interface CompaniesListView {
    public void loadCompanies(List<Company> companies);
    public void showLoadingView();
    public void hideLoadingView();
}

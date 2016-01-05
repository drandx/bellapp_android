package com.lap.bellapp.bellapp_android.ui.view;

import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;

import java.util.List;

/**
 * Created by juangarcia on 1/3/16.
 */
public interface CategoriesListView {
    public void loadCategories(List<BusinessCategory> categories);
    public void showLoadingView();
    public void hideLoadingView();
}

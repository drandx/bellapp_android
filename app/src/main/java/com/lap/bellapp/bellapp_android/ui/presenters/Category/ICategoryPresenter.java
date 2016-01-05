package com.lap.bellapp.bellapp_android.ui.presenters.Category;

import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;
import com.lap.bellapp.bellapp_android.ui.view.CategoriesListView;

import java.util.List;

/**
 * Created by juangarcia on 1/3/16.
 */
public interface ICategoryPresenter {
    public BusinessCategory getCategorybyPosition(int position);
    public List<BusinessCategory> getCategories();
    public void configureView(CategoriesListView view);
    public void loadCategories();
}

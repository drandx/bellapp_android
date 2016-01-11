package com.lap.bellapp.bellapp_android.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;
import com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.ui.activity.CompaniesListFragmentActivity;
import com.lap.bellapp.bellapp_android.ui.adapter.CategoriesListAdapter;
import com.lap.bellapp.bellapp_android.ui.presenters.Category.CategoryListPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Company.CompanyListPresenter;
import com.lap.bellapp.bellapp_android.ui.view.CategoriesListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juangarcia on 1/3/16.
 */
public class CategoriesListFragment extends BaseFragment implements View.OnClickListener, CategoriesListView{
    RecyclerView categorieslist;
    ProgressBar loader;
    public View view;

    @Inject
    Context context;
    @Inject
    CategoryListPresenter categoriesPresenter;
    @Inject
    CompanyListPresenter companyListPresenter;

    CategoriesListAdapter categoriesListAdapter;

    public static CategoriesListFragment newInstance() {
        CategoriesListFragment fragment = new CategoriesListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        this.getComponent(ApplicationComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.categories_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.categorieslist = (RecyclerView) view.findViewById(R.id.recycledCategoriesList);
        loader = (ProgressBar)view.findViewById(R.id.fragment_categories_loader);
        categoriesPresenter.configureView(this);
        categoriesPresenter.loadCategories();
    }

    @Override
    public void onClick(View v) {
        Log.i("CategoriesListFragment", "..Category was clicked");
        int itemPosition = categorieslist.getChildAdapterPosition(v);
        BusinessCategory clickedItem = categoriesListAdapter.getItems().get(itemPosition);
        companyListPresenter.setUpCompanies(clickedItem.associatedCompanies);
        Intent companiesIntent = new Intent(context, CompaniesListFragmentActivity.class);
        this.startActivity(companiesIntent);
    }

    @Override
    public void loadCategories(List<BusinessCategory> categories) {
        categoriesListAdapter = new CategoriesListAdapter(categories,context,this);
        this.categorieslist.setLayoutManager(new LinearLayoutManager(context));
        this.categorieslist.setAdapter(this.categoriesListAdapter);
    }

    @Override
    public void showLoadingView() {
        this.loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        this.loader.setVisibility(View.GONE);
    }
}

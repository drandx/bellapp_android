package com.lap.bellapp.bellapp_android.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.ui.adapter.CompaniesListAdapter;
import com.lap.bellapp.bellapp_android.ui.presenters.Company.CompanyListPresenter;
import com.lap.bellapp.bellapp_android.ui.view.CompaniesListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juangarcia on 1/4/16.
 */
public class CompaniesListFragmentActivity extends BaseActivity implements CompaniesListView,View.OnClickListener {

    private RecyclerView companiesList;
    private ProgressBar loader;
    private Toolbar mToolbar;

    @Inject
    CompanyListPresenter companyListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        setContentView(R.layout.activity_companies_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.companies_screen_title));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        companiesList = (RecyclerView)findViewById(R.id.recycledCompaniesList);
        loader = (ProgressBar)findViewById(R.id.fragment_companies_loader);
        companiesList.setLayoutManager(new LinearLayoutManager(this));
        companyListPresenter.configureView(this);
        companyListPresenter.getCompanies();
    }

    @Override
    public void onClick(View v) {
        //TODO - Start a new activity for Company details

    }

    @Override
    public void loadCompanies(List<Company> companies) {
        companiesList.setAdapter(new CompaniesListAdapter(companies,this,this));
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


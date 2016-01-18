package com.lap.bellapp.bellapp_android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.ui.adapter.CompanyStaffAdapter;
import com.lap.bellapp.bellapp_android.ui.presenters.Calendar.CalendarPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Company.CompanyStaffPresenter;
import com.lap.bellapp.bellapp_android.ui.view.CompanyStaffView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanpablogarcia on 1/11/16.
 */
public class CompanyStaffFragmentActivity extends BaseActivity implements CompanyStaffView, View.OnClickListener {
    private Toolbar mToolbar;
    private RecyclerView companyStaffRecycler;
    private ProgressBar loader;
    private CompanyStaffAdapter staffAdapter;

    @Inject
    CompanyStaffPresenter companyStaffPresenter;
    @Inject
    CalendarPresenter calendarPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_staff_fragment_activity);
        loader = (ProgressBar)findViewById(R.id.fragment_staff_loader);
        companyStaffRecycler = (RecyclerView) findViewById(R.id.recycledStaffList);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.company_detail_staff_screen_title));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getApplicationComponent().inject(this);
        companyStaffPresenter.configureView(this);
        companyStaffPresenter.loadStaff();
    }

    @Override
    public void loadStaff(List<StaffEntity> staff) {
        companyStaffRecycler.setLayoutManager(new LinearLayoutManager(this));
        staffAdapter = new CompanyStaffAdapter(staff,companyStaffPresenter.getLoadedCompany(),companyStaffPresenter.getloadedService(),this,this);
        companyStaffRecycler.setAdapter(staffAdapter);
    }

    @Override
    public void showLoadingView() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int itemPosition = companyStaffRecycler.getChildAdapterPosition(v);
        StaffEntity selectedStaff = staffAdapter.getItem(itemPosition);
        Log.i("CategoriesListFragment", "..Category was clicked");
        Intent calendarIntent = new Intent(this, CalendarFragmentActivity.class);
        calendarPresenter.setUpLoadedCompany(companyStaffPresenter.getLoadedCompany());
        calendarPresenter.setUpLoadedService(companyStaffPresenter.getloadedService());
        calendarPresenter.setUpLoadedStaff(selectedStaff);
        this.startActivity(calendarIntent);
    }
}

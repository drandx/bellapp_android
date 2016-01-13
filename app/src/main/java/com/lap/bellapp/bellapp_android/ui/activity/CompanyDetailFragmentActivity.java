package com.lap.bellapp.bellapp_android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.ui.adapter.ServicesListAdapter;
import com.lap.bellapp.bellapp_android.ui.presenters.Company.CompanyDetailPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Company.CompanyStaffPresenter;
import com.lap.bellapp.bellapp_android.ui.view.CompanyDetailView;

import javax.inject.Inject;

/**
 * Created by juanpablogarcia on 1/9/16.
 */
public class CompanyDetailFragmentActivity extends BaseActivity implements CompanyDetailView, View.OnClickListener {
    private Toolbar mToolbar;
    private RecyclerView servicesList;
    private ImageButton buttonFavorite;
    private TextView textCompanyTitle;
    private TextView textCompanyDescription;
    private ImageView imageCompanyIcon;
    private TextView textAddress;
    private TextView textNeighborhood;
    private TextView textCity;

    private String ARGUMENT_SERVICE_ID = "service.id";

    @Inject
    CompanyDetailPresenter companyDetailPresenter;

    @Inject
    CompanyStaffPresenter companyStaffPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_detail_fragment_activity);
        getApplicationComponent().inject(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.companies_screen_title));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textCompanyTitle = (TextView)findViewById(R.id.textCompanyTitle);
        textCompanyDescription = (TextView)findViewById(R.id.textCompanyDescription);
        imageCompanyIcon = (ImageView)findViewById(R.id.imageCompanyIcon);
        textAddress = (TextView)findViewById(R.id.textAddress);
        textNeighborhood = (TextView)findViewById(R.id.textNeighborhood);
        textCity = (TextView)findViewById(R.id.textCity);

        Company companyItem = companyDetailPresenter.getLoadedCompany();
        textCity.setText(companyItem.city);
        textNeighborhood.setText(companyItem.neighborhood);
        textAddress.setText(companyItem.address);
        textCompanyDescription.setText(companyItem.content);
        textCompanyTitle.setText(companyItem.title);

        if(companyItem.fullImagePath != ""){
            Glide.with(this)
                    .load(companyItem.fullImagePath)
                            //.fitCenter()
                            //.centerCrop()
                    .into(imageCompanyIcon);
        }

        servicesList = (RecyclerView)findViewById(R.id.recycledServicesList);
        servicesList.setLayoutManager(new LinearLayoutManager(this));
        servicesList.setAdapter(new ServicesListAdapter(companyDetailPresenter.getLoadedCompany().getServices(), this, this));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int itemPosition = servicesList.getChildAdapterPosition(v);
        BusinessService selectedService = companyDetailPresenter.getServiceByPosition(itemPosition);
        companyStaffPresenter.setUpLoadedService(selectedService);
        companyStaffPresenter.setUpLoadedCompany(companyDetailPresenter.getLoadedCompany());
        Intent intent = new Intent(this, CompanyStaffFragmentActivity.class);
        this.startActivity(intent);
    }
}

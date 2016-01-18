package com.lap.bellapp.bellapp_android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;

import java.util.List;

/**
 * Created by juanpablogarcia on 1/12/16.
 */
public class CompanyStaffAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final List<StaffEntity> staffItems;
    private final Context mContext;
    private final View.OnClickListener onClickListener;
    private final Company company;
    private final BusinessService service;

    public CompanyStaffAdapter(List<StaffEntity> staffItems, Company company, BusinessService service, Context context, View.OnClickListener onClickListener) {
        this.staffItems = staffItems;
        this.mContext = context;
        this.onClickListener = onClickListener;
        this.company = company;
        this.service = service;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            ViewGroup staffView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.staff_item, parent, false);
            staffView.setOnClickListener(onClickListener);
            return new StaffViewHolder(staffView);
        } else if (viewType == TYPE_HEADER) {
            ViewGroup companyView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.company_item_staff, parent, false);
            return new CompanyViewHolder(companyView);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StaffViewHolder) {
            StaffViewHolder staffViewHolder = (StaffViewHolder)holder;
            StaffEntity staffItem = staffItems.get(position - 1);
            staffViewHolder.textName.setText(staffItem.firstName + " " + staffItem.lastName);
        } else if (holder instanceof CompanyViewHolder) {
            CompanyViewHolder companyHolder = (CompanyViewHolder)holder;
            companyHolder.textCompanyTitle.setText(company.title);
            companyHolder.textCompanyDescription.setText(company.content);
            companyHolder.textCity.setText(company.city);
            companyHolder.textNeighborhood.setText(company.neighborhood);
            companyHolder.textAddress.setText(company.address);
            companyHolder.textServiceDescription.setText(service.content);
            companyHolder.textServiceTitle.setText(service.title);
            companyHolder.textServiceDuration.setText(String.format(mContext.getResources().getString(R.string.company_detail_service_duration),service.getMinutesDuration()));
        }
    }

    @Override
    public int getItemCount() {
        return staffItems.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public StaffEntity getItem(int position) {
        return staffItems.get(position - 1);
    }

    class StaffViewHolder extends RecyclerView.ViewHolder {
        TextView textName;

        public StaffViewHolder(View itemView) {
            super(itemView);
            textName = (TextView)itemView.findViewById(R.id.textStaffName);
        }
    }

    class CompanyViewHolder extends RecyclerView.ViewHolder {
        private TextView textCompanyTitle;
        private TextView textCompanyDescription;
        private TextView textAddress;
        private TextView textNeighborhood;
        private TextView textCity;
        private TextView textServiceTitle;
        private TextView textServiceDuration;
        private TextView textServiceDescription;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            textCompanyTitle = (TextView)itemView.findViewById(R.id.textCompanyTitle);
            textCompanyDescription = (TextView)itemView.findViewById(R.id.textCompanyDescription);
            textAddress = (TextView)itemView.findViewById(R.id.textAddress);
            textNeighborhood = (TextView)itemView.findViewById(R.id.textNeighborhood);
            textCity = (TextView)itemView.findViewById(R.id.textCity);
            textServiceTitle = (TextView)itemView.findViewById(R.id.textServiceTitle);
            textServiceDescription = (TextView)itemView.findViewById(R.id.textServiceDescription);
            textServiceDuration = (TextView)itemView.findViewById(R.id.textServiceDuration);
        }
    }
}

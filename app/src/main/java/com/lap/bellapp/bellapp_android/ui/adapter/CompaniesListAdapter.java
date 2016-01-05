package com.lap.bellapp.bellapp_android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.Company;

import java.util.List;

/**
 * Created by juangarcia on 1/4/16.
 */
public class CompaniesListAdapter extends RecyclerView.Adapter<CompaniesListAdapter.CompanyViewHolder> {
    private final List<Company> companyItems;
    private final Context mContext;
    private final View.OnClickListener onClickListener;

    public CompaniesListAdapter(List<Company> companyItems, Context context, View.OnClickListener onClickListener) {
        this.companyItems = companyItems;
        this.mContext = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup companyView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.company_item, parent, false);
        companyView.setOnClickListener(onClickListener);
        CompanyViewHolder categoryView = new CompanyViewHolder(companyView);
        return categoryView;
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        Company companyItem = this.companyItems.get(position);
        holder.textCity.setText(companyItem.city);
        holder.textNeighborhood.setText(companyItem.neighborhood);
        holder.textAddress.setText(companyItem.address);
        holder.textCompanyDescription.setText(companyItem.content);
        holder.textCompanyTitle.setText(companyItem.title);

        if(companyItem.fullImagePath != ""){
            Glide.with(mContext)
                    .load(companyItem.fullImagePath)
                            //.fitCenter()
                            //.centerCrop()
                    .into(holder.imageCompanyIcon);
        }
    }

    @Override
    public int getItemCount() {
        return companyItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    class CompanyViewHolder extends RecyclerView.ViewHolder{
        ImageButton buttonFavorite;
        TextView textCompanyTitle;
        TextView textCompanyDescription;
        ImageView imageCompanyIcon;
        TextView textAddress;
        TextView textNeighborhood;
        TextView textCity;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            buttonFavorite = (ImageButton)itemView.findViewById(R.id.buttonFavorite);
            textCompanyTitle = (TextView)itemView.findViewById(R.id.textCompanyTitle);
            textCompanyDescription = (TextView)itemView.findViewById(R.id.textCompanyDescription);
            imageCompanyIcon = (ImageView)itemView.findViewById(R.id.imageCompanyIcon);
            textAddress = (TextView)itemView.findViewById(R.id.textAddress);
            textNeighborhood = (TextView)itemView.findViewById(R.id.textNeighborhood);
            textCity = (TextView)itemView.findViewById(R.id.textCity);
        }
    }
}

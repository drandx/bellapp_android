package com.lap.bellapp.bellapp_android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by juanpablogarcia on 1/9/16.
 */
public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.ServiceViewHolder>  {


    private final List<BusinessService> serviceItems;
    private final Context mContext;
    private final View.OnClickListener onClickListener;

    public ServicesListAdapter(List<BusinessService> serviceItems, Context context, View.OnClickListener onClickListener) {
        this.serviceItems = serviceItems;
        this.mContext = context;
        this.onClickListener = onClickListener;
    }


    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup serviceView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.service_item, parent, false);
        serviceView.setOnClickListener(onClickListener);
        ServiceViewHolder categoryView = new ServiceViewHolder(serviceView);
        return categoryView;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {
        BusinessService serviceItem = serviceItems.get(position);
        holder.textServiceTitle.setText(serviceItem.title);
        holder.textServiceDescription.setText(String.format(mContext.getResources().getString(R.string.company_detail_service_duration),serviceItem.getMinutesDuration()));
    }

    @Override
    public int getItemCount() {
        return this.serviceItems.size();
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder{
        TextView textServiceTitle;
        TextView textServiceDescription;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            textServiceDescription = (TextView)itemView.findViewById(R.id.textServiceDescription);
            textServiceTitle = (TextView)itemView.findViewById(R.id.textServiceTitle);
        }
    }
}

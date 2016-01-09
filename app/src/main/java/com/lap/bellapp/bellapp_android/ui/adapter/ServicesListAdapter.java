package com.lap.bellapp.bellapp_android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;

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
        return null;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder{

        public ServiceViewHolder(View itemView) {
            super(itemView);
        }
    }
}

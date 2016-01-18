package com.lap.bellapp.bellapp_android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.ui.model.TimeSlot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by juanpablogarcia on 1/9/16.
 */
public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsAdapter.TimeSlotViewHolder>  {


    private final List<TimeSlot> timeSlotsItems;
    private final Context mContext;
    private final View.OnClickListener onClickListener;

    public TimeSlotsAdapter(List<TimeSlot> timeSlotItems, Context context, View.OnClickListener onClickListener) {
        this.timeSlotsItems = timeSlotItems;
        this.mContext = context;
        this.onClickListener = onClickListener;
    }


    @Override
    public TimeSlotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup serviceView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.timeslot_item, parent, false);
        serviceView.setOnClickListener(onClickListener);
        TimeSlotViewHolder timeSlotView = new TimeSlotViewHolder(serviceView);
        return timeSlotView;
    }

    @Override
    public void onBindViewHolder(TimeSlotViewHolder holder, int position) {
        TimeSlot timeSlotItem = timeSlotsItems.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        holder.textTimeSlot.setText(String.format(mContext.getResources().getString(R.string.calendar_timeslot_item_title), dateFormat.format(timeSlotItem.getInitialTime()), dateFormat.format(timeSlotItem.getFinalTime())));
    }

    @Override
    public int getItemCount() {
        return this.timeSlotsItems.size();
    }

    class TimeSlotViewHolder extends RecyclerView.ViewHolder{
        TextView textTimeSlot;

        public TimeSlotViewHolder(View itemView) {
            super(itemView);
            textTimeSlot = (TextView)itemView.findViewById(R.id.textTimeSlot);
        }
    }
}

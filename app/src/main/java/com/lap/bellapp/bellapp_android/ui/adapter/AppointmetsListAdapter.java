package com.lap.bellapp.bellapp_android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by juangarcia on 10/21/15.
 */
public class AppointmetsListAdapter extends ArrayAdapter {

    private Context context;
    private List<MeetingTime> appointmentsList;

    public AppointmetsListAdapter(Context context, List<MeetingTime> meetingTimes) {
        super(context, R.layout.appointment_item);
        this.context = context;
        this.appointmentsList = meetingTimes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MeetingTime appointmentEntity = this.appointmentsList.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.appointment_item, null);

            viewHolder.dateTitle = (TextView) convertView.findViewById(R.id.textViewAppointmentDate);
            viewHolder.dateSubTitle = (TextView) convertView.findViewById(R.id.textViewAppointmentTime);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String dayString = new SimpleDateFormat("EEEE dd, MMMM").format(appointmentEntity.getStartTime());
        String timeInitString = new SimpleDateFormat("hh:mm a").format(appointmentEntity.getStartTime());
        String timeEndString = new SimpleDateFormat("hh:mm a").format(appointmentEntity.getFinishTime());


        viewHolder.dateTitle.setText(dayString);
        viewHolder.dateSubTitle.setText(timeInitString + " - " + timeEndString);

        return convertView;
    }

    @Override
    public int getCount() {
        return this.appointmentsList.size();
    }

    @Override
    public MeetingTime getItem(int position) {
        return this.appointmentsList.get(position);
    }

    private static class ViewHolder {
        TextView dateTitle;
        TextView dateSubTitle;
    }
}


package com.lap.bellapp.bellapp_android.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;

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
        MeetingTime appointmentEntity = (MeetingTime)this.appointmentsList.get(position);
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.appointment_item, null);

            TextView dateTitle = (TextView) view.findViewById(R.id.textViewAppointmentDate);
            dateTitle.setText(appointmentEntity.getStartTime().toString());
            TextView dateSubTitle = (TextView) view.findViewById(R.id.textViewAppointmentTime);
            dateSubTitle.setText(appointmentEntity.getStartTime().toString());

        }
        else {
            view = convertView;
        }
        return view;
    }

    @Override
    public int getCount() {
        return this.appointmentsList.size();
    }

    @Override
    public MeetingTime getItem(int position) {
        return this.appointmentsList.get(position);
    }


}

package com.lap.bellapp.bellapp_android.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;
import com.lap.bellapp.bellapp_android.presentation.di.components.StaffComponent;
import com.lap.bellapp.bellapp_android.presentation.presenters.StaffAppointmentListPresenter;
import com.lap.bellapp.bellapp_android.presentation.view.AppointmentsFilter;
import com.lap.bellapp.bellapp_android.presentation.view.StaffListView;
import com.lap.bellapp.bellapp_android.presentation.view.adapter.AppointmetsListAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/20/15.
 */
public class AppointmentsListFragment extends BaseFragment implements StaffListView, AdapterView.OnItemClickListener {

    @Inject
    public StaffAppointmentListPresenter appoinmentsListPresenter;
    @Inject
    public Context context;

    private View rootView;
    private AppointmetsListAdapter appointmentsAdapter;
    private static final String POSITION = "POSITION";

    public static AppointmentsListFragment newInstance(int position){
        AppointmentsListFragment fragment = new AppointmentsListFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.appointments_list_fragment, container, false);
        return rootView;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    private void initialize(){
        int tabSelected = getArguments().getInt(POSITION);
        Log.i("AppointmentsListFrg","InitiaLize List Adapter..Tab selected: "+tabSelected);
        this.getComponent(StaffComponent.class).inject(this);
        this.appoinmentsListPresenter.setView(this);
        this.appoinmentsListPresenter.initiaLize(AppointmentsFilter.getEnumByInt(tabSelected));
    }

    @Override
    public void hideViewLoading() {
        LinearLayout progressBarLayout = (LinearLayout) rootView.findViewById(R.id.main_progress_container);
        progressBarLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Log.e("AppointmentsListFrg",errorMessage);
    }

    @Override
    public void showAppointmentsList(List<MeetingTime> appointments) {
        final ListView listview = (ListView) rootView.findViewById(R.id.appoinments_listview);
        this.appointmentsAdapter = new AppointmetsListAdapter(context, appointments);
        listview.setAdapter(this.appointmentsAdapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("AppointmentsListFrg", "Selected Item position: "+position);
    }
}

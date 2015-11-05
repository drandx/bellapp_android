package com.lap.bellapp.bellapp_android.presentation.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;
import com.lap.bellapp.bellapp_android.presentation.di.components.StaffComponent;
import com.lap.bellapp.bellapp_android.presentation.presenters.StaffAppointmentListPresenter;
import com.lap.bellapp.bellapp_android.presentation.view.AppointmentsFilter;
import com.lap.bellapp.bellapp_android.presentation.view.StaffListView;
import com.lap.bellapp.bellapp_android.presentation.view.activity.AppointmentDetailFragmentActivity;
import com.lap.bellapp.bellapp_android.presentation.view.adapter.AppointmetsListAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/20/15.
 */
public class AppointmentsListFragment extends BaseFragment implements StaffListView, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String POSITION = "POSITION";
    private static final String ARGUMENT_APPOINTMENT = "org.android10.ARGUMENT_APPOINTMENT";


    @Inject
    public StaffAppointmentListPresenter appoinmentsListPresenter;
    @Inject
    public Context context;

    private View rootView;
    private AppointmetsListAdapter appointmentsAdapter;
    private SwipeRefreshLayout swipeLayout;

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

        swipeLayout = (SwipeRefreshLayout) this.rootView.findViewById(R.id.swipeContainer);
        swipeLayout.setOnRefreshListener(this);
//        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);

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
        //LinearLayout progressBarLayout = (LinearLayout) rootView.findViewById(R.id.main_progress_container);
        //progressBarLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Log.e("AppointmentsListFrg", errorMessage);
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
        Log.i("AppointmentsListFrg", "Selected Item position: " + position);
        Log.i("AppointmentsListFrg", "Selected Item id:" + this.appointmentsAdapter.getItem(position).getMeetingTimeId());
        Intent intent = new Intent(getActivity(), AppointmentDetailFragmentActivity.class);
        intent.putExtra(ARGUMENT_APPOINTMENT, this.appointmentsAdapter.getItem(position).meetingTimeId);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        int tabSelected = getArguments().getInt(POSITION);
        this.appoinmentsListPresenter.initiaLize(AppointmentsFilter.getEnumByInt(tabSelected));
        this.swipeLayout.setRefreshing(false);
    }
}

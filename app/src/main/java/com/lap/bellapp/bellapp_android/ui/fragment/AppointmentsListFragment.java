package com.lap.bellapp.bellapp_android.ui.fragment;

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
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.ui.activity.AppointmentDetailFragmentActivity;
import com.lap.bellapp.bellapp_android.ui.adapter.AppointmetsListAdapter;
import com.lap.bellapp.bellapp_android.ui.model.AppointmentsFilter;
import com.lap.bellapp.bellapp_android.ui.presenters.StaffAppointmentListPresenter;
import com.lap.bellapp.bellapp_android.ui.view.StaffListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/20/15.
 */
public class AppointmentsListFragment extends BaseFragment implements StaffListView, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String POSITION = "POSITION";
    private static final String ARGUMENT_APPOINTMENT = "org.android10.ARGUMENT_APPOINTMENT";
    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";

    @Inject
    public StaffAppointmentListPresenter appoinmentsListPresenter;
    @Inject
    public Context context;

    private View rootView;
    private AppointmetsListAdapter appointmentsAdapter;
    private SwipeRefreshLayout swipeLayout;
    private int userId;
    private ListView listView;

    public static AppointmentsListFragment newInstance(int position, int userId){
        AppointmentsListFragment fragment = new AppointmentsListFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        args.putInt(ARGUMENT_KEY_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.appointments_list_fragment, container, false);
        this.listView = (ListView) this.rootView.findViewById(R.id.appoinments_listview);
        this.listView.setOnItemClickListener(this);
        swipeLayout = (SwipeRefreshLayout) this.rootView.findViewById(R.id.swipeContainer);
        swipeLayout.setOnRefreshListener(this);
        return rootView;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    private void initialize(){
        int tabSelected = getArguments().getInt(POSITION);
        userId = getArguments().getInt(ARGUMENT_KEY_USER_ID);
        Log.i("AppointmentsListFrg","InitiaLize List Adapter..Tab selected: "+tabSelected);
        this.getComponent(ApplicationComponent.class).inject(this);
        this.appoinmentsListPresenter.getAppointments(userId);
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
        this.rootView.findViewById(R.id.empty).setVisibility((appointments.size() == 0) ? View.VISIBLE : View.GONE);
        this.appointmentsAdapter = new AppointmetsListAdapter(context, appointments);
        this.listView.setAdapter(this.appointmentsAdapter);
        this.swipeLayout.setRefreshing(false);
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
        this.appoinmentsListPresenter.getAppointments(userId);
    }
}

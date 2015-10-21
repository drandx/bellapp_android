package com.lap.bellapp.bellapp_android.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lap.bellapp.bellapp_android.R;

/**
 * Created by juangarcia on 10/20/15.
 */
public class AppointmentsListFragment extends BaseFragment {

    private int selectedPosition;

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
        this.selectedPosition = getArguments().getInt(POSITION);
        return inflater.inflate(R.layout.appointments_list_fragment, container, false);
    }
}

package com.lap.bellapp.bellapp_android.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lap.bellapp.bellapp_android.R;

/**
 * Created by juangarcia on 10/20/15.
 */
public class AccountFragment extends BaseFragment {

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.account_fragments, container, false);
    }

}

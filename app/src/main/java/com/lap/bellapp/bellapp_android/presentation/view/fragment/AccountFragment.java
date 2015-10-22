package com.lap.bellapp.bellapp_android.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lap.bellapp.bellapp_android.R;


/**
 * Created by juangarcia on 10/20/15.
 */
public class AccountFragment extends BaseFragment {
    public EditText textEmail;
    public EditText textPassword;
    public EditText textFirstName;
    public EditText textLastName;
    public EditText textPhoneNumber;


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Use fields...
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragments, container, false);
        this.textEmail = (EditText) view.findViewById(R.id.editTextEmail);
        this.textPassword = (EditText) view.findViewById(R.id.editTextPassword);
        this.textFirstName = (EditText) view.findViewById(R.id.editTextFirstName);
        this.textLastName = (EditText) view.findViewById(R.id.editTextLastName);
        this.textPhoneNumber = (EditText) view.findViewById(R.id.editTextPhoneNumber);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}

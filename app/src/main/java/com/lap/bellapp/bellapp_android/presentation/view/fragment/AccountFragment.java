package com.lap.bellapp.bellapp_android.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.presentation.di.components.StaffComponent;
import com.lap.bellapp.bellapp_android.presentation.presenters.StaffAccountPresenter;
import com.lap.bellapp.bellapp_android.presentation.view.StaffAccountView;

import javax.inject.Inject;


/**
 * Created by juangarcia on 10/20/15.
 */
public class AccountFragment extends BaseFragment implements StaffAccountView{

    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";

    public EditText textEmail;
    public EditText textPassword;
    public EditText textFirstName;
    public EditText textLastName;
    public EditText textPhoneNumber;

    @Inject
    StaffAccountPresenter staffAccountPresenter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Use fields...
    }

    public static AccountFragment newInstance(int userId) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_KEY_USER_ID, userId);
        fragment.setArguments(args);
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

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    private void initialize(){
        this.getComponent(StaffComponent.class).inject(this);
        Bundle args = getArguments();
        this.staffAccountPresenter.initialize(args.getInt(ARGUMENT_KEY_USER_ID, 0));
        this.staffAccountPresenter.setAccountView(this);
    }

    @Override
    public void loadStaffAccount(StaffEntity staffEntity) {
        this.textEmail.setText(staffEntity.getEmail());
        this.textPassword.setText(staffEntity.getPassword());
        this.textFirstName.setText(staffEntity.getFirstName());
        this.textLastName.setText(staffEntity.getLastName());
        this.textPhoneNumber.setText(staffEntity.getPhoneNumber());
    }
}

package com.lap.bellapp.bellapp_android.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.ui.presenters.StaffAccountPresenter;
import com.lap.bellapp.bellapp_android.ui.view.StaffAccountView;

import javax.inject.Inject;


/**
 * Created by juangarcia on 10/20/15.
 */
public class AccountFragment extends BaseFragment implements StaffAccountView{

    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";
    private StaffEntity staffEntity;
    private int userId;


    public EditText textEmail;
    public EditText textPassword;
    public EditText textFirstName;
    public EditText textLastName;
    public EditText textPhoneNumber;
    public Button buttonUpdate;

    @Inject
    StaffAccountPresenter staffAccountPresenter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.userId = args.getInt(ARGUMENT_KEY_USER_ID, 0);
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
        this.buttonUpdate = (Button) view.findViewById(R.id.buttonUpdateStaffAccount);
        this.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffEntity = new StaffEntity();
                staffEntity.setStaffId(userId);
                staffEntity.setEmail(textEmail.getText().toString());
                staffEntity.setFirstName(textFirstName.getText().toString());
                staffEntity.setLastName(textLastName.getText().toString());
                staffEntity.setPhoneNumber(textPhoneNumber.getText().toString());
                staffEntity.setPassword(textPassword.getText().toString());
                staffAccountPresenter.updateModel(staffEntity);
            }
        });
        return view;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    private void initialize(){
        this.getComponent(ApplicationComponent.class).inject(this);
        this.staffAccountPresenter.initialize(this.userId);
        this.staffAccountPresenter.setAccountView(this);
    }

    @Override
    public void loadStaffAccount(StaffEntity staffEntity) {
        this.staffEntity = staffEntity;
        this.textEmail.setText(staffEntity.getEmail());
        this.textPassword.setText(staffEntity.getPassword());
        this.textFirstName.setText(staffEntity.getFirstName());
        this.textLastName.setText(staffEntity.getLastName());
        this.textPhoneNumber.setText(staffEntity.getPhoneNumber());
    }

    @Override
    public void showUpdateMessage(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
    }
}

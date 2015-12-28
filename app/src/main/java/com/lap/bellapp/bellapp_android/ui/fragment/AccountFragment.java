package com.lap.bellapp.bellapp_android.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.ui.model.UserDetail;
import com.lap.bellapp.bellapp_android.ui.presenters.Account.AccountPresenter;
import com.lap.bellapp.bellapp_android.ui.view.AccountView;
import com.lap.bellapp.bellapp_android.util.PresentersFactory;

import javax.inject.Inject;


/**
 * Created by juangarcia on 10/20/15.
 */
public class AccountFragment extends BaseFragment implements AccountView {

    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";
    private UserDetail userDetails;
    private int userId;

    public EditText textEmail;
    public EditText textPassword;
    public EditText textFirstName;
    public EditText textLastName;
    public EditText textPhoneNumber;
    public Button buttonUpdate;
    public View view;

    private AccountPresenter accountPresenter;

    @Inject
    PresentersFactory presentersFactory;

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
        this.view = inflater.inflate(R.layout.account_fragments, container, false);
        this.textEmail = (EditText) view.findViewById(R.id.editTextEmail);
        this.textPassword = (EditText) view.findViewById(R.id.editTextPassword);
        this.textFirstName = (EditText) view.findViewById(R.id.editTextFirstName);
        this.textLastName = (EditText) view.findViewById(R.id.editTextLastName);
        this.textPhoneNumber = (EditText) view.findViewById(R.id.editTextPhoneNumber);
        this.buttonUpdate = (Button) view.findViewById(R.id.buttonUpdateStaffAccount);
        this.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetails = new UserDetail(userId, textEmail.getText().toString(),
                        textPassword.getText().toString(), textPhoneNumber.getText().toString(),
                        textLastName.getText().toString(), textFirstName.getText().toString());
                accountPresenter.updateAccountDetails(userDetails);
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
        this.accountPresenter = presentersFactory.getAccountPresenter();
        this.accountPresenter.configureAccountView(this);
        this.accountPresenter.loadAccountDetails(this.userId);
    }

    @Override
    public void loadAccountDetails(UserDetail userDetails) {
        this.userDetails = userDetails;
        this.textEmail.setText(userDetails.email);
        this.textPassword.setText(userDetails.password);
        this.textFirstName.setText(userDetails.firstName);
        this.textLastName.setText(userDetails.lastName);
        this.textPhoneNumber.setText(userDetails.phoneNumber);
    }

    @Override
    public void showUpdateMessage(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        LinearLayout loading = (LinearLayout) this.view.findViewById(R.id.account_progress_container);
        loading.setVisibility(View.GONE);
    }
}

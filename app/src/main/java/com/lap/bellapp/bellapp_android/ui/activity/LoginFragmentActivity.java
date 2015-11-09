package com.lap.bellapp.bellapp_android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.injection.HasComponent;
import com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.ui.presenters.StaffLoginPresenter;
import com.lap.bellapp.bellapp_android.ui.view.StaffLoginView;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by juangarcia on 10/17/15.
 */
public class LoginFragmentActivity extends BaseActivity implements StaffLoginView, HasComponent<ApplicationComponent> {
    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";

    private EditText email;
    private EditText password;
    private Button loginButton;
    private Toolbar mToolbar;


    @Inject
    StaffLoginPresenter loginPresenter;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_login_fragment_activity);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        email = (EditText) findViewById(R.id.text_email);
        password = (EditText) findViewById(R.id.text_password);

        loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.staffLogin(email.getText().toString(), password.getText().toString());
            }
        });

        initializeInjector();
        loginPresenter.setView(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        loginPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.destroy();
    }


    @Override
    public void hideViewLoading() {

    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToNextStep(StaffEntity staffEntity) {
        Intent intent = new Intent(this, HomeActivity.class);
        this.userId = staffEntity.staffId;
        intent.putExtra(ARGUMENT_KEY_USER_ID, this.userId);
        startActivity(intent);
    }

    private void initializeInjector() {
        getApplicationComponent().inject(this);
    }

    @Override
    public ApplicationComponent getComponent() {
        return this.getApplicationComponent();
    }

}

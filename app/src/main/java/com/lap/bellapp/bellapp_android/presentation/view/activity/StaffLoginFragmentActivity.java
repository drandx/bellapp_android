package com.lap.bellapp.bellapp_android.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.presentation.di.HasComponent;
import com.lap.bellapp.bellapp_android.presentation.di.components.DaggerStaffComponent;
import com.lap.bellapp.bellapp_android.presentation.di.components.StaffComponent;
import com.lap.bellapp.bellapp_android.presentation.di.modules.StaffModule;
import com.lap.bellapp.bellapp_android.presentation.presenters.StaffLoginPresenter;
import com.lap.bellapp.bellapp_android.presentation.view.StaffLoginView;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by juangarcia on 10/17/15.
 */
public class StaffLoginFragmentActivity extends BaseActivity implements StaffLoginView, HasComponent<StaffComponent> {
    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";

    private EditText email;
    private EditText password;
    private Button loginButton;

    @Inject
    StaffLoginPresenter loginPresenter;

    private StaffComponent staffComponent;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_login_fragment_activity);

        email = (EditText) findViewById(R.id.text_email);
        password = (EditText) findViewById(R.id.text_password);

        loginButton = (Button) findViewById(R.id.button_login);

        final StaffLoginFragmentActivity subscriber = this;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("StaffLogin", " Click LogIn");
                initializeInjector();
                staffComponent.inject(subscriber);
                loginPresenter.setView(subscriber);
                loginPresenter.initialize();
            }
        });

        initializeInjector();
        staffComponent.inject(this);
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
        Toast.makeText(getApplicationContext(), errorMessage,
                Toast.LENGTH_LONG).show();    }

    @Override
    public void navigateToNextStep(StaffEntity staffEntity) {
        Intent intent = new Intent(this, HomeActivity.class);
        this.userId = staffEntity.staffId;
        intent.putExtra(ARGUMENT_KEY_USER_ID, this.userId);
        startActivity(intent);
    }

    private void initializeInjector() {
        this.staffComponent = DaggerStaffComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .staffModule(new StaffModule(-1, this.email.getText().toString(), this.password.getText().toString()))
                .build();
    }

    @Override
    public StaffComponent getComponent() {
        return staffComponent;
    }

}

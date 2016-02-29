package com.lap.bellapp.bellapp_android.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.ui.view.RecoveryView;

/**
 * Created by HECTOR DAMIAN on 20/02/2016.
 */
public class RecoveryPasswordFragmentActivity extends BaseActivity implements RecoveryView {

    private EditText email;
    private Button send;
    private Toolbar toolbarRecovery;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recovery_password_fragment_activity);

        toolbarRecovery = (Toolbar) findViewById(R.id.toolbarRecovery);
        //setSupportActionBar(toolbarRecovery);
        getSupportActionBar().setTitle(R.string.customer_recovery_screen_title);

        email = (EditText) findViewById(R.id.editTextEmail);
        send = (Button) findViewById(R.id.buttonSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

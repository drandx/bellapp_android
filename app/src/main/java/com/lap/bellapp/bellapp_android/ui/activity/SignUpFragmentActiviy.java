package com.lap.bellapp.bellapp_android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;

/**
 * Created by juangarcia on 12/28/15.
 */
public class SignUpFragmentActiviy extends BaseActivity {
    private Toolbar mToolbar;

    public EditText textEmail;
    public EditText textPassword;
    public EditText textFirstName;
    public EditText textLastName;
    public EditText textPhoneNumber;
    public Button buttonSubmit;
    public TextView textViewTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_fragment_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        textEmail = (EditText) findViewById(R.id.editTextEmail);
        textPassword = (EditText) findViewById(R.id.editTextPassword);
        textFirstName = (EditText) findViewById(R.id.editTextFirstName);
        textLastName = (EditText) findViewById(R.id.editTextLastName);
        textPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        buttonSubmit = (Button) findViewById(R.id.buttonSignUp);
        textViewTerms = (TextView) findViewById(R.id.textViewTerms);

        textViewTerms.setText(Html.fromHtml(String.format("%s <b>%s</b>",
                getString(R.string.customer_signup_terms_agreement), getString(R.string.customer_signup_terms_agreement_bold))));

        final SignUpFragmentActiviy sender = this;

        textViewTerms.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(sender, TermsConditionsFragmentActivity.class);
                        startActivity(intent);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

    buttonSubmit.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
                /*userDetails = new UserDetail(userId, textEmail.getText().toString(),
                        textPassword.getText().toString(), textPhoneNumber.getText().toString(),
                        textLastName.getText().toString(), textFirstName.getText().toString());
                accountPresenter.updateAccountDetails(userDetails);*/
        }
    });
    }
}

package com.lap.bellapp.bellapp_android.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lap.bellapp.bellapp_android.R;

/**
 * Created by juangarcia on 12/21/15.
 */
public class CustomerHomeActivity extends BaseActivity {

    private Button btnSignup;
    private Button btnSignin;

   @Override
   protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       Log.i("CustomerHomeActivity", "OnCreate");
       setContentView(R.layout.customer_welcome_fragment_activity);

       btnSignup = (Button)findViewById(R.id.buttonSignUp);
       btnSignup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i("CustomerHomeActivity", "Signing up..");
               goToSignUpSteo();

           }
       });

       btnSignin = (Button)findViewById(R.id.buttonLogin);
       btnSignin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i("CustomerHomeActivity","Signing in..");
               goToSinginStep();
           }
       });

   }

    private void goToSinginStep(){
        Intent intent = new Intent(this, LoginFragmentActivity.class);
        startActivity(intent);
    }

    private void goToSignUpSteo(){
        Log.i("CustomerHomeActivity","Comming soon...");
    }

}

package com.lap.bellapp.bellapp_android.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.lap.bellapp.bellapp_android.presentation.di.HasComponent;
import com.lap.bellapp.bellapp_android.presentation.di.components.DaggerStaffComponent;
import com.lap.bellapp.bellapp_android.presentation.di.components.StaffComponent;
import com.lap.bellapp.bellapp_android.presentation.di.modules.StaffModule;

public class MainActivity extends BaseActivity
        implements HasComponent<StaffComponent>{

    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";

    private StaffComponent staffComponent;
    private int userId;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    //private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.initializeInjector();
        //setContentView(R.layout.activity_main);
//        mNavigationDrawerFragment = (NavigationDrawerFragment)
//                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
//        mTitle = getTitle();
//
//        mNavigationDrawerFragment.setUp(
//                R.id.navigation_drawer,
//                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

//    @Override
//    public void onNavigationDrawerItemSelected(int position) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        BaseFragment selectedFragment;
//
//        if(position == 0){
//            selectedFragment = AppointmentsMasterFragment.newInstance();
//        }
//        else if(position == 1){
//            selectedFragment = AccountFragment.newInstance(userId);
//        }
//        else{
//            selectedFragment = AppointmentsMasterFragment.newInstance();
//        }
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, selectedFragment)
//                .commit();
//    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            getMenuInflater().inflate(R.menu.main, menu);
//            restoreActionBar();
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initializeInjector() {
        this.userId = getIntent().getIntExtra(ARGUMENT_KEY_USER_ID, -1);

        this.staffComponent = DaggerStaffComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .staffModule(new StaffModule(this.userId,"",""))
                .build();
    }
    @Override
    public StaffComponent getComponent() {
        return staffComponent;
    }
}

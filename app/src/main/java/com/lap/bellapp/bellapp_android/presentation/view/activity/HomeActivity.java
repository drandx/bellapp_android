package com.lap.bellapp.bellapp_android.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.presentation.di.HasComponent;
import com.lap.bellapp.bellapp_android.presentation.di.components.DaggerStaffComponent;
import com.lap.bellapp.bellapp_android.presentation.di.components.StaffComponent;
import com.lap.bellapp.bellapp_android.presentation.di.modules.StaffModule;
import com.lap.bellapp.bellapp_android.presentation.view.fragment.AccountFragment;
import com.lap.bellapp.bellapp_android.presentation.view.fragment.AppointmentsMasterFragment;
import com.lap.bellapp.bellapp_android.presentation.view.fragment.BaseFragment;
import com.lap.bellapp.bellapp_android.presentation.view.fragment.FragmentDrawer;

/**
 * Created by juangarcia on 11/3/15.
 */
public class HomeActivity extends BaseActivity implements FragmentDrawer.FragmentDrawerListener, HasComponent<StaffComponent> {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";
    private StaffComponent staffComponent;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializeInjector();
        setContentView(R.layout.home_activity);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.home_drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

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

    public void displayView(int position){
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment selectedFragment;
        String title;

        if(position == 0){
            selectedFragment = AppointmentsMasterFragment.newInstance();
            title = getString(R.string.nav_item_appointments);
        }
        else if(position == 1){
            selectedFragment = AccountFragment.newInstance(userId);
            title = getString(R.string.nav_item_account);
        }
        else{
            selectedFragment = AppointmentsMasterFragment.newInstance();
            title = getString(R.string.app_name);
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container_body, selectedFragment)
                .commit();
        getSupportActionBar().setTitle(title);
    }
}

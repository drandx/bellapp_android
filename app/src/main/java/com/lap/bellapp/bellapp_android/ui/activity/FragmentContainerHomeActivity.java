package com.lap.bellapp.bellapp_android.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.injection.HasComponent;
import com.lap.bellapp.bellapp_android.injection.components.ApplicationComponent;
import com.lap.bellapp.bellapp_android.ui.fragment.AccountFragment;
import com.lap.bellapp.bellapp_android.ui.fragment.AppointmentsMasterFragment;
import com.lap.bellapp.bellapp_android.ui.fragment.BaseFragment;
import com.lap.bellapp.bellapp_android.ui.fragment.CategoriesListFragment;
import com.lap.bellapp.bellapp_android.ui.fragment.FragmentDrawer;
import com.lap.bellapp.bellapp_android.ui.model.MenuItems;
import com.lap.bellapp.bellapp_android.util.Utils;

/**
 * Created by juangarcia on 11/3/15.
 */
public class FragmentContainerHomeActivity extends BaseActivity implements FragmentDrawer.FragmentDrawerListener, HasComponent<ApplicationComponent> {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";
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
        menuItemSelected(((BellappApplication)getApplicationContext()).getInitialSelecetdMenuItem());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initializeInjector() {
        this.userId = getIntent().getIntExtra(ARGUMENT_KEY_USER_ID, -1);
    }
    @Override
    public ApplicationComponent getComponent() {
        return this.getApplicationComponent();
    }

    @Override
    public void menuItemSelected(MenuItems menuItem) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment selectedFragment;
        String title = Utils.getStringResourceByName(menuItem.getMenuTitleKey(), this);
        switch (menuItem){
            case APPOINTMENTS:
                selectedFragment = AppointmentsMasterFragment.newInstance(userId);
                break;
            case ACCOUNT:
                selectedFragment = AccountFragment.newInstance(userId);
                break;
            case CATEGORIES:
                selectedFragment = CategoriesListFragment.newInstance();
                break;
            default:
                selectedFragment = AppointmentsMasterFragment.newInstance(userId);
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container_body, selectedFragment)
                .commit();
        getSupportActionBar().setTitle(title);
    }
}

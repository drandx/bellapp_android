package com.lap.bellapp.bellapp_android.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lap.bellapp.bellapp_android.R;
import com.lap.bellapp.bellapp_android.presentation.view.external.SlidingTabLayout;

/**
 * Created by juangarcia on 10/20/15.
 */
public class AppointmentsMasterFragment extends BaseFragment {
    public final static String NEWS_TEXT_TAB = "HOY";
    public final static String EVENTS_TEXT_TAB = "TODAS";
    public final static int NUMBER_OF_TABS = 2;
    private static int EVENTS_POSITION = 1;

    public AppointmentsListFragment ntf;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private LayoutInflater inflater;
    private MainPagerAdapter mpa;

    public static AppointmentsMasterFragment newInstance() {
        AppointmentsMasterFragment fragment = new AppointmentsMasterFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.appointments_master_fragment, container, false);

        if(getFragmentManager() != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (ft != null) {
                if(getFragmentManager().getFragments() != null) {
                    for (Fragment f : getFragmentManager().getFragments()) {
                        if(f.getClass().equals(AppointmentsListFragment.class)){
                            ft.remove(f);
                        }
                    }
                    ft.commit();
                }
            }
        }

        if (mpa==null)
        {
            mpa = new MainPagerAdapter(getFragmentManager());
            viewPager = (ViewPager) rootView.findViewById(R.id.appointments_viewpager);
            viewPager.setAdapter(mpa);

            slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.appointments_sliding_tabs);
            slidingTabLayout.setCustomTabView(R.layout.news_tab_item,R.id.news_tab_title);
            slidingTabLayout.setViewPager(viewPager);
            slidingTabLayout.setDividerColors(getResources().getColor(R.color.gray_scale_palette_3));
            slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.gray_scale_palette_black));
            slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    setSelectedTab(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });

            initTabTexts();
        }

        return rootView;
    }


    private void initTabTexts() {
        for(int i=0;i<NUMBER_OF_TABS;i++){
            LinearLayout tab = (LinearLayout)((LinearLayout) slidingTabLayout.getChildAt(0)).getChildAt(i);
            TextView textView = (TextView)tab.findViewById(R.id.news_tab_title);
            switch (i){
                case 0:
                    textView.setText(NEWS_TEXT_TAB);
                    textView.setTextColor(getResources().getColor(R.color.gray_scale_palette_3));
                    break;
                case 1:
                    textView.setText(EVENTS_TEXT_TAB);
                    textView.setTextColor(getResources().getColor(R.color.gray_scale_palette_black));
                    break;

            }
        }
    }


    private void setSelectedTab(int position){

        for (int i=0; i<=EVENTS_POSITION; i++){
            LinearLayout tab = (LinearLayout)((LinearLayout) slidingTabLayout.getChildAt(0)).getChildAt(i);
            TextView textView = (TextView)tab.findViewById(R.id.news_tab_title);

            if(i == position){
                textView.setTextColor(getResources().getColor(R.color.gray_scale_palette_black));
            }else{
                textView.setTextColor(getResources().getColor(R.color.gray_scale_palette_3));
            }
        }

    }


    /**
     * Internal class that extends the {@link android.support.v4.app.FragmentPagerAdapter}
     *
     * Created by dcoellar on 9/3/15 and reused by fmolina on 9/10/15.
     *
     */
    class MainPagerAdapter extends FragmentPagerAdapter {

        /**
         * Constructor required by the parent class
         *
         * Created by dcoellar on 9/3/15 and reused by fmolina on 9/10/15.
         *
         * params: FragmentManager fm : Fragment Manager
         */
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Defines/Returns the fragment for a given position
         *
         * Created by dcoellar on 9/3/15 and reused by fmolina on 9/10/15.
         *
         * params: int position : The position for witch a fragment will be returned
         *
         * returns: Fragment : The fragment for a position
         */
        @Override
        public Fragment getItem(int position) {
            AppointmentsListFragment fragment = AppointmentsListFragment.newInstance(position);
            return fragment;
        }

        /**
         * Returns the number of fragments to be supported
         *
         * Created by dcoellar on 9/3/15 and reused by fmolina on 9/10/15.
         *
         * returns: int : The number of fragments
         */
        @Override
        public int getCount() {
            return 2;
        }


    }
}

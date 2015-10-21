package com.lap.bellapp.bellapp_android.presentation.view.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.lap.bellapp.bellapp_android.presentation.di.HasComponent;

/**
 * Created by juangarcia on 10/20/15.
 */
public class BaseFragment extends Fragment{

    private String screenName;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>)getActivity()).getComponent());
    }
}

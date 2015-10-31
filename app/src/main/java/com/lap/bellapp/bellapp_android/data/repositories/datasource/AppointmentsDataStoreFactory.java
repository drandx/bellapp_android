package com.lap.bellapp.bellapp_android.data.repositories.datasource;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by juangarcia on 10/28/15.
 */
public class AppointmentsDataStoreFactory {

    private Context mContext;

    @Inject
    public AppointmentsDataStoreFactory(Context context) {
        mContext = context;
    }

    public AppointmentDataStore create() {
        AppointmentDataStore userDataStore = new AppointmentRemoteStore(mContext);
        return userDataStore;
    }
}

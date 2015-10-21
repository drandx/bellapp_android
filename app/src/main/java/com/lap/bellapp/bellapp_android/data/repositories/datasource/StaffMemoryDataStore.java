package com.lap.bellapp.bellapp_android.data.repositories.datasource;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.data.local.StaffCache;
import com.lap.bellapp.bellapp_android.data.local.StaffMemoryCache;

import rx.Observable;

/**
 * Created by juangarcia on 10/19/15.
 */
public class StaffMemoryDataStore implements StaffDataStore {

    //TODO - Use local cache injected
    private StaffCache localCache = StaffMemoryCache.getInstance();


    @Override
    public Observable<StaffEntity> getStaffEntity(int staffId) {
        return localCache.get(staffId);
    }

    @Override
    public Observable<StaffEntity> getLoginStaff(String email, String password) {
        return null;
    }
}

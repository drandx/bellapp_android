package com.lap.bellapp.bellapp_android.data.repositories.datasource;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.data.local.StaffCache;
import com.lap.bellapp.bellapp_android.data.local.StaffMemoryCache;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by juangarcia on 10/19/15.
 */
public class StaffMemoryDataStore implements StaffDataStore {

    //TODO - Use local cache injected
    private StaffCache memoryCache = StaffMemoryCache.getInstance();


    @Override
    public Observable<StaffEntity> getStaffEntity(int staffId) {
        return memoryCache.get(staffId);
    }

    @Override
    public Observable<StaffEntity> getLoginStaff(String email, String password) {
        return null;
    }

    @Override
    public Observable<StaffEntity> updateStaff(final StaffEntity staffEntity) {
        memoryCache.put(staffEntity);
        Observable<StaffEntity> staffObservable =
                Observable.create(new Observable.OnSubscribe<StaffEntity>() {
                                      @Override
                                      public void call(Subscriber<? super StaffEntity> subscriber) {
                                          subscriber.onNext(staffEntity);
                                          subscriber.onCompleted();
                                          subscriber.onError(new Exception("There was an error updating the staff entity"));
                                      }
                                  }

                );
        return staffObservable;
    }
}

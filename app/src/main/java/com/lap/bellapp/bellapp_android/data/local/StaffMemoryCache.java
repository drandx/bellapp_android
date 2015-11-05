package com.lap.bellapp.bellapp_android.data.local;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.data.exception.UserNotFoundException;

import java.util.Hashtable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by juangarcia on 10/17/15.
 */
public class StaffMemoryCache implements StaffCache {

    private final Hashtable cachedUsers;

    private static  StaffMemoryCache instance;

    public static StaffMemoryCache getInstance(){
        if(instance == null){
            instance = new StaffMemoryCache();
        }
        return instance;
    }

    public StaffMemoryCache() {
        this.cachedUsers = new Hashtable();
    }

    @Override
    public synchronized Observable<StaffEntity> get(final int userId) {
        return Observable.create(new Observable.OnSubscribe<StaffEntity>() {

            @Override
            public void call(Subscriber<? super StaffEntity> subscriber) {
                StaffEntity loadedStaff = (StaffEntity) cachedUsers.get(userId);

                if (loadedStaff != null){
                    subscriber.onNext(loadedStaff);
                    subscriber.onCompleted();
                }
                else{
                    subscriber.onError(new UserNotFoundException());
                }
            }
        });
    }

    @Override
    public void put(StaffEntity userEntity) {
        if(isCached(userEntity.staffId)){
            StaffEntity loadedStaff = (StaffEntity) cachedUsers.get(userEntity.staffId);
            loadedStaff.setPassword(userEntity.getPassword());
            loadedStaff.setEmail(userEntity.getEmail());
            loadedStaff.setPhoneNumber(userEntity.getPhoneNumber());
            loadedStaff.setFirstName(userEntity.getFirstName());
            loadedStaff.setLastName(userEntity.getLastName());
            cachedUsers.put(userEntity.staffId, loadedStaff);
        }
        else{
            cachedUsers.put(userEntity.staffId, userEntity);
        }
    }

    @Override
    public boolean isCached(int userId) {
        StaffEntity loadedStaff = (StaffEntity) cachedUsers.get(userId);
        if (loadedStaff != null) {
            return true;
        }
        return false;
    }
}

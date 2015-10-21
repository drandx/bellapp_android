package com.lap.bellapp.bellapp_android.data.local;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;

import rx.Observable;

/**
 * Created by juangarcia on 10/19/15.
 */
public interface StaffCache {
    /**
     * Gets an {@link rx.Observable} which will emit a {@link StaffEntity}.
     *
     * @param userId The user id to retrieve data.
     */
    Observable<StaffEntity> get(final int userId);

    /**
     * Puts and element into the cache.
     *
     * @param userEntity Element to insert in the cache.
     */
    void put(StaffEntity userEntity);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @param userId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final int userId);
}

/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lap.bellapp.bellapp_android.data.repositories.datasource;

import android.content.Context;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.data.local.StaffCache;
import com.lap.bellapp.bellapp_android.data.local.StaffMemoryCache;
import com.lap.bellapp.bellapp_android.data.remote.BellappService;
import com.lap.bellapp.bellapp_android.data.remote.RetrofitHelper;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * {@link StaffDataStore} implementation based on connections to the api (Cloud).
 */
public class RemoteStaffDataStore implements StaffDataStore {

  private BellappService mServiceAPI;
  private StaffCache staffCache = null;

  public RemoteStaffDataStore(Context context) {
    mServiceAPI = new RetrofitHelper().newBellappService(context);
    staffCache = StaffMemoryCache.getInstance();
  }

  @Override
  public Observable<StaffEntity> getStaffEntity(final int staffId) {
    return mServiceAPI.getStaff(staffId).doOnNext(new Action1<StaffEntity>() {
      @Override
      public void call(StaffEntity staffEntity) {
        staffCache.put(staffEntity);
      }
    });
  }

    @Override
  public Observable<StaffEntity> getLoginStaff(String email, String password) {
    return mServiceAPI.getLoginStaff(email, password).flatMap(new Func1<StaffEntity, Observable<StaffEntity>>() {
        @Override
        public Observable<StaffEntity> call(StaffEntity staffEntity) {
          return getStaffEntity(staffEntity.staffId);
        }
      });
  }

//  private final RestApi restApi;
//  private final UserCache userCache;
//
//  private final Action1<UserEntity> saveToCacheAction =
//      userEntity -> {
//        if (userEntity != null) {
//          CloudStaffDataStore.this.userCache.put(userEntity);
//        }
//      };
//
//  /**
//   * Construct a {@link StaffDataStore} based on connections to the api (Cloud).
//   *
//   * @param restApi The {@link RestApi} implementation to use.
//   * @param userCache A {@link UserCache} to cache data retrieved from the api.
//   */
//  public CloudStaffDataStore(RestApi restApi, UserCache userCache) {
//    this.restApi = restApi;
//    this.userCache = userCache;
//  }
//
//  @Override
//  public Observable<List<UserEntity>> getUserEntityList() {
//    return this.restApi.getUserEntityList();
//  }
//
//  @Override
//  public Observable<UserEntity> getUserEntityDetails(final int userId) {
//    return this.restApi.getUserEntityById(userId)
//        .doOnNext(saveToCacheAction);
//  }

}

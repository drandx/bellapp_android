/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lap.bellapp.bellapp_android.data.repositories.datasource;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface StaffDataStore {

  /**
   * Get an {@link rx.Observable} which will emit a {@link StaffEntity} by its id.
   *
   * @param staffId The id to retrieve user data.
   */
  Observable<StaffEntity> getStaffEntity(final int staffId);

  Observable<StaffEntity> getLoginStaff(final String email, final String password);

  Observable<StaffEntity> updateStaff(StaffEntity staffEntity);
}

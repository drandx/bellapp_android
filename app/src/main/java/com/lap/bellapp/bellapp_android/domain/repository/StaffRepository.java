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
package com.lap.bellapp.bellapp_android.domain.repository;

import com.lap.bellapp.bellapp_android.data.entity.MeetingTime;
import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link StaffEntity} related data.
 */
public interface StaffRepository {
  /**
   * Get an {@link rx.Observable} which will emit a {@link StaffEntity}.
   *
   * @param userId The user id used to retrieve user data.
   */
  Observable<StaffEntity> getUser(final int userId);

  /**
   * Get an {@link rx.Observable} which will emit a {@link StaffEntity}.
   *
   * @param email The email used for login
   * @param password The password used for login
   */
  Observable<StaffEntity> getUserLogin(final String email, final String password);

  Observable<List<MeetingTime>> getUserMeetingTimes(int userId);
}

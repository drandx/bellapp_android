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
package com.lap.bellapp.bellapp_android.data.repositories;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;
import com.lap.bellapp.bellapp_android.data.repositories.datasource.StaffDataStore;
import com.lap.bellapp.bellapp_android.data.repositories.datasource.StaffDataStoreFactory;
import com.lap.bellapp.bellapp_android.domain.repository.StaffRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link StaffRepository} for retrieving user data.
 */
@Singleton
public class StaffDataRepository implements StaffRepository {

    private final StaffDataStoreFactory staffDataStoreFactory;

    @Inject
    public StaffDataRepository(StaffDataStoreFactory staffDataStoreFactory) {
        this.staffDataStoreFactory = staffDataStoreFactory;
    }

    @Override
    public Observable<StaffEntity> getUser(int userId) {
        StaffDataStore staffStore = staffDataStoreFactory.create(userId);
        return staffStore.getStaffEntity(userId);
    }

    @Override
    public Observable<StaffEntity> getUserLogin(String email, String password) {
        StaffDataStore staffStore = staffDataStoreFactory.create();
        return staffStore.getLoginStaff(email,password);
    }
}

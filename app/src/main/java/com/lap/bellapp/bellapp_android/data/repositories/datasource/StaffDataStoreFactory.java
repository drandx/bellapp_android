/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lap.bellapp.bellapp_android.data.repositories.datasource;

import android.content.Context;

import com.lap.bellapp.bellapp_android.data.local.StaffMemoryCache;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link StaffDataStore}.
 */
@Singleton
public class StaffDataStoreFactory {

    private Context mContext;

    @Inject
    public StaffDataStoreFactory(Context context) {
        mContext = context;
    }

    public StaffDataStore create() {
        StaffDataStore userDataStore = new RemoteStaffDataStore(mContext);
        return userDataStore;
    }

    public StaffDataStore create(int userId) {
        if (StaffMemoryCache.getInstance().isCached(userId)){
          return new StaffMemoryDataStore();
        }
        else{
            return new RemoteStaffDataStore(mContext);
        }
    }

}

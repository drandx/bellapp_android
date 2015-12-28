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
package com.lap.bellapp.bellapp_android.injection.modules;

import android.content.Context;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.local.PreferencesHelper;
import com.lap.bellapp.bellapp_android.data.model.ApplicationType;
import com.lap.bellapp.bellapp_android.data.remote.BellappService;
import com.lap.bellapp.bellapp_android.data.remote.RetrofitHelper;
import com.lap.bellapp.bellapp_android.reactive.executor.JobExecutor;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.reactive.executor.UIThread;
import com.lap.bellapp.bellapp_android.ui.presenters.Login.CustomerLoginPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Login.StaffLoginPresenter;
import com.lap.bellapp.bellapp_android.util.PresentersFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final BellappApplication application;

  public ApplicationModule(BellappApplication application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Context provideApplicationContext() {
    return this.application;
  }

  @Provides
  @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides
  @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides
  @Singleton
  DataManager provideDataManager(){
    BellappService bellappService = new RetrofitHelper().newBellappService(application);
    PreferencesHelper preferencesHelper = new PreferencesHelper(application);
    return new DataManager(bellappService, preferencesHelper);
  }

  @Provides
  @Singleton
  PresentersFactory providePresentersFactory(CustomerLoginPresenter customerLogin,
                                             StaffLoginPresenter staffsLogin){
    if(this.application.getApplicationType() == ApplicationType.ASSISTANT){
      return new PresentersFactory(staffsLogin);
    }
    else{
      return new PresentersFactory(customerLogin);
    }
  }

}

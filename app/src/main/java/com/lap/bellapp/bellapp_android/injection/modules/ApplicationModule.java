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
import com.lap.bellapp.bellapp_android.ui.presenters.Account.CustomerAccountPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Account.StaffAccountPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Appointment.CustomerAppointmentsListPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Appointment.StaffAppointmentListPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Calendar.CalendarPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Company.CompanyDetailPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Company.CompanyListPresenter;
import com.lap.bellapp.bellapp_android.ui.presenters.Company.CompanyStaffPresenter;
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
                                             StaffLoginPresenter staffsLogin,
                                             StaffAccountPresenter staffAccountPresenter,
                                             CustomerAccountPresenter customerAccountPresenter,
                                             StaffAppointmentListPresenter staffAppointmentListPresenter,
                                             CustomerAppointmentsListPresenter customerAppointmentsListPresenter){
    if(this.application.getApplicationType() == ApplicationType.ASSISTANT){
      return new PresentersFactory(staffsLogin, staffAccountPresenter, staffAppointmentListPresenter);
    }
    else{
      return new PresentersFactory(customerLogin, customerAccountPresenter, customerAppointmentsListPresenter);
    }
  }

  @Provides
  @Singleton
  CompanyListPresenter provideCompanyListPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context, DataManager dataManager){
    return new CompanyListPresenter(threadExecutor,postExecutionThread,context,dataManager);
  }

  @Provides
  @Singleton
  CompanyDetailPresenter provideCompanyDetailPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context, DataManager dataManager){
    return new CompanyDetailPresenter(threadExecutor,postExecutionThread,context,dataManager);
  }

  @Provides
  @Singleton
  CompanyStaffPresenter provideCompanyStaffPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context, DataManager dataManager){
    return new CompanyStaffPresenter(threadExecutor,postExecutionThread,context,dataManager);
  }

  @Provides
  @Singleton
  CalendarPresenter provideCalendarPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context context, DataManager dataManager){
    return new CalendarPresenter(threadExecutor,postExecutionThread,context,dataManager);
  }

}

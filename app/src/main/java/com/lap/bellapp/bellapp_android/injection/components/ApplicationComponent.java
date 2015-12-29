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
package com.lap.bellapp.bellapp_android.injection.components;

import android.content.Context;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.injection.modules.ApplicationModule;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.activity.AppointmentDetailFragmentActivity;
import com.lap.bellapp.bellapp_android.ui.activity.BaseActivity;
import com.lap.bellapp.bellapp_android.ui.activity.LoginFragmentActivity;
import com.lap.bellapp.bellapp_android.ui.activity.SignUpFragmentActiviy;
import com.lap.bellapp.bellapp_android.ui.fragment.AccountFragment;
import com.lap.bellapp.bellapp_android.ui.fragment.AppointmentsListFragment;
import com.lap.bellapp.bellapp_android.util.PresentersFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);
  void inject(LoginFragmentActivity staffLoginFragmentActivity);
  void inject(AccountFragment accountFragment);
  void inject(AppointmentsListFragment appointmentsListFragment);
  void inject(AppointmentDetailFragmentActivity appointmentDetailFragmentActivity);
  void inject(SignUpFragmentActiviy signUpFragmentActiviy);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  DataManager dataManager();
  PresentersFactory presentersFactory();

}

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
package com.lap.bellapp.bellapp_android.presentation.di.modules;

import com.lap.bellapp.bellapp_android.domain.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.domain.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.domain.interactor.StaffLoginUseCase;
import com.lap.bellapp.bellapp_android.domain.interactor.UseCase;
import com.lap.bellapp.bellapp_android.domain.repository.StaffRepository;
import com.lap.bellapp.bellapp_android.presentation.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class StaffModule {

  private int userId = -1;
  private String email = "";
  private String password = "";

  public StaffModule() {}

  public StaffModule(int userId, String email, String password) {
    this.userId = userId;
    this.email = email;
    this.password = password;
  }

//  @Provides
//  @PerActivity @Named("userList") UseCase provideGetUserListUseCase(GetUserListUseCase getUserListUseCase) {
//    return getUserListUseCase;
//  }

  @Provides
  @PerActivity
  @Named("loginStaff")
  UseCase provideStaffLoginUseCase(
      StaffRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    return new StaffLoginUseCase(userId, email, password, userRepository, threadExecutor, postExecutionThread);
  }
}
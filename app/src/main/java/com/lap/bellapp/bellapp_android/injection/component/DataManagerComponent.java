package com.lap.bellapp.bellapp_android.injection.component;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.injection.Scope.PerDataManager;
import com.lap.bellapp.bellapp_android.injection.module.DataManagerModule;

import dagger.Component;

@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = DataManagerModule.class)
public interface DataManagerComponent {

    void inject(DataManager dataManager);
}

package com.lap.bellapp.assistant;

import com.lap.bellapp.bellapp_android.BellappApplication;

/**
 * Created by juangarcia on 10/1/15.
 */
public class AssistantApplication extends BellappApplication {
    @Override
    public String getBase_Url() {
        return Constants.SERVICES_HOST;
    }
}

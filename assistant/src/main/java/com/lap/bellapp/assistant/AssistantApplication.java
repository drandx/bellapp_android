package com.lap.bellapp.assistant;

import com.lap.bellapp.bellapp_android.BellappApplication;

/**
 * Created by juangarcia on 10/1/15.
 */
public class AssistantApplication extends BellappApplication {
    @Override
    public String getBaseUrl() {
        return Constants.SERVICES_HOST;
    }
}

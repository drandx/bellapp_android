package com.lap.bellapp.assistant;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.lap.bellapp.bellapp_android.data.model.ApplicationType;
import com.lap.bellapp.bellapp_android.ui.model.MenuItems;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

/**
 * Created by juangarcia on 10/1/15.
 */
public class AssistantApplication extends BellappApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, Constants.PARSE_APPLICATION_ID, Constants.PARSE_CLIENT_ID);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        for (String channel : Constants.PARSE_CHANNELS){
            ParsePush.subscribeInBackground(channel);
        }
    }

    @Override
    public String getBaseUrl() {
        return Constants.SERVICES_HOST;
    }

    @Override
    public String getEnvDateParsing() {
        return Constants.ENV_DATE_PARSING;
    }

    @Override
    public void subscribeToParseChannel(String parseChannel) {
        ParsePush.subscribeInBackground(parseChannel);
    }

    @Override
    public ApplicationType getApplicationType(){
        return ApplicationType.ASSISTANT;
    }

    @Override
    public MenuItems getInitialSelecetdMenuItem() {
        return MenuItems.APPOINTMENTS;
    }


}

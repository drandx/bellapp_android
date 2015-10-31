package com.lap.bellapp.customer;

import com.lap.bellapp.bellapp_android.BellappApplication;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

/**
 * Created by juangarcia on 10/31/15.
 */
public class CustomerApplication extends BellappApplication {    @Override
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
    public void subscribeToParseChannel(String providerChannel) {
        ParsePush.subscribeInBackground(providerChannel);
    }
}

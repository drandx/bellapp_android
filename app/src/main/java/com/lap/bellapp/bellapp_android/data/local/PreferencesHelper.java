package com.lap.bellapp.bellapp_android.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private SharedPreferences mPref;

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";

    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getString(String key){
        return mPref.getString(key, "");
    }

    public void putString(String key, String value){
        mPref.edit().putString(key,value).apply();
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

}

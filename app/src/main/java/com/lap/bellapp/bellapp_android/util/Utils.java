package com.lap.bellapp.bellapp_android.util;

import android.content.Context;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by juangarcia on 12/29/15.
 */
public class Utils {
    public static boolean isValidEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static  boolean isValidPhoneNumber(String phoneNumber){
        Pattern pattern = Patterns.PHONE;
        return pattern.matcher(phoneNumber).matches();
    }

    public static String getStringResourceByName(String aString, Context context) {
        int resId = context.getResources().getIdentifier(aString, "string", context.getPackageName());
        if (resId == 0) {
            return aString;
        } else {
            return context.getString(resId);
        }
    }
}

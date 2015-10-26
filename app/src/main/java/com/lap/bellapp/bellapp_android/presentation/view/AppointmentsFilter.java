package com.lap.bellapp.bellapp_android.presentation.view;

/**
 * Created by juangarcia on 10/25/15.
 */
public enum AppointmentsFilter {
    TODAY(0),
    ALL(1);

    private int value;

    AppointmentsFilter(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue() + "";
    }

    public static AppointmentsFilter getEnumByInt(int code) {
        for (AppointmentsFilter enumObj : AppointmentsFilter.values()) {
            if (code == enumObj.value) {
                return enumObj;
            }
        }
        return null;
    }
}
package com.lap.bellapp.bellapp_android.data.model;

/**
 * Created by juangarcia on 12/29/15.
 */
public enum GenderType {
    MALE ("Hombre"),
    FEMALE ("Mujer");

    private final String value;

    private GenderType(String s) {
        value = s;
    }

    public GenderType genderFromString(String value){
        for(GenderType item : values()){
            if (item.value == value) {
                return item;
            }
        }
        return null;
    }

    public String toString() {
        return this.value;
    }
}

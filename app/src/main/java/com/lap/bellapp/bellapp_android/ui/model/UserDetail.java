package com.lap.bellapp.bellapp_android.ui.model;

/**
 * Created by juangarcia on 12/28/15.
 */
public class UserDetail{
    public int userId;
    public String email;
    public String password;
    public String phoneNumber;
    public String lastName;
    public String firstName;

    public UserDetail(int userId, String email, String password, String phoneNumber, String lastName, String firstName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
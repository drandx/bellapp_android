package com.lap.bellapp.bellapp_android.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by juangarcia on 10/31/15.
 */
public class CustomerEntity {

    @SerializedName("CustomerID")
    public int customerId;

    @SerializedName("Email")
    public String email;

    @SerializedName("Password")
    public String password;

    @SerializedName("FirstName")
    public String firstName;

    @SerializedName("LastName")
    public String lastName;

    @SerializedName("PhoneNumber")
    public String phoneNumber;

    @SerializedName("Gender")
    public String gender;

    @SerializedName("MeetingTimes")
    public List<MeetingTime> MeetingTimes;

    public CustomerEntity(int customerId, String email, String password, String firstName, String lastName, String phoneNumber, String gender) {
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public CustomerEntity(String email, String password, String firstName, String lastName, String phoneNumber, String gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public CustomerEntity(int customerId, String email, String password, String firstName, String lastName, String phoneNumber) {
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<MeetingTime> getMeetingTimes() {
        return MeetingTimes;
    }

    public void setMeetingTimes(List<MeetingTime> meetingTimes) {
        MeetingTimes = meetingTimes;
    }
}

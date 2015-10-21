package com.lap.bellapp.bellapp_android.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by juangarcia on 10/5/15.
 */
public class StaffEntity {

    @SerializedName("StaffID")
    public int staffId;

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

    @SerializedName("MeetingTimes")
    public List<MeetingTime> meetingTimes;

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

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public List<MeetingTime> getMeetingTimesl() {
        return meetingTimes;
    }

    public void setMeetingTimesl(List<MeetingTime> meetingTimesl) {
        this.meetingTimes = meetingTimesl;
    }

    @Override
    public String toString(){
        return staffId + " " + email + " " + password + " " + firstName + " " + lastName + " " + phoneNumber;
    }

    public enum UserTypes{
        STAFF("Staff"),
        CUSTOMER("Customer");

        private String value;

        UserTypes(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }

        public static UserTypes getEnumByString(String code){
            if (code != null) {
                for(UserTypes enumObj : UserTypes.values()){
                    if(code.equalsIgnoreCase(enumObj.value)){
                        return enumObj;
                    }
                }
            }

            return null;
        }
    }
}

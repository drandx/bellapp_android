package com.lap.bellapp.bellapp_android.data.model;

/**
 * Created by juangarcia on 10/5/15.
 */
public class User {
    public UserTypes userType;

    public String email;

    public String password;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public String gender;

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
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

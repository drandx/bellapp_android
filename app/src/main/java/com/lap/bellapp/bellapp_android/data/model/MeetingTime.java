package com.lap.bellapp.bellapp_android.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by juangarcia on 10/5/15.
 */
public class MeetingTime{

    @SerializedName("MeetingTimeID")
    public int meetingTimeId;

    @SerializedName("StaffID")
    public int staffId;

    @SerializedName("CustomerID")
    public int customerId;

    @SerializedName("BusinessServiceID")
    public int businessServiceId;

    @SerializedName("CompanyID")
    public int companyId;

    @SerializedName("StartTime")
    public Date startTime;

    @SerializedName("FinishTime")
    public Date finishTime;

    @SerializedName("Staff")
    public StaffEntity staff;

    @SerializedName("BusinessService")
    public BusinessService service;

    @SerializedName("Company")
    public Company company;

    @SerializedName("Customer")
    public CustomerEntity customer;

    public MeetingTimeStateEnum state;

    public int getMeetingTimeId() {
        return meetingTimeId;
    }

    public void setMeetingTimeId(int meetingTimeId) {
        this.meetingTimeId = meetingTimeId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBusinessServiceId() {
        return businessServiceId;
    }

    public void setBusinessServiceId(int businessServiceId) {
        this.businessServiceId = businessServiceId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }

    public BusinessService getService() {
        return service;
    }

    public void setService(BusinessService service) {
        this.service = service;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public MeetingTimeStateEnum getState() {
        return state;
    }

    public void setState(MeetingTimeStateEnum state) {
        this.state = state;
    }
}

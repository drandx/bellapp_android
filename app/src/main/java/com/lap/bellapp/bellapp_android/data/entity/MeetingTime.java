package com.lap.bellapp.bellapp_android.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by juangarcia on 10/5/15.
 */
public class MeetingTime {

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
}

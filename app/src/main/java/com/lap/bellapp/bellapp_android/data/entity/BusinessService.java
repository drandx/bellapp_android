package com.lap.bellapp.bellapp_android.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by juangarcia on 10/31/15.
 */
public class BusinessService {

    @SerializedName("ID")
    public int id;

    @SerializedName("BusinessCategoryID")
    public int businessCategoryId;

    @SerializedName("Title")
    public String title;

    @SerializedName("Content")
    public String content;

    @SerializedName("MinutesDuration")
    public int minutesDuration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusinessCategoryId() {
        return businessCategoryId;
    }

    public void setBusinessCategoryId(int businessCategoryId) {
        this.businessCategoryId = businessCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMinutesDuration() {
        return minutesDuration;
    }

    public void setMinutesDuration(int minutesDuration) {
        this.minutesDuration = minutesDuration;
    }
}

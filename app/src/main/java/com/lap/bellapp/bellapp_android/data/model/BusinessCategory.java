package com.lap.bellapp.bellapp_android.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by juangarcia on 12/30/15.
 */
public class BusinessCategory {
    @SerializedName("BusinessCategoryID")
    public int businessCategoryId;

    @SerializedName("Title")
    public String title;

    @SerializedName("Content")
    public String content;

    @SerializedName("IconName")
    public String iconName;

    @SerializedName("FullIconPath")
    public String fullIconPath;

    @SerializedName("BussinessServices")
    public List<BusinessService> bussinessServices;

    @SerializedName("AssociatedCompanies")
    public List<Company> associatedCompanies;

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

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getFullIconPath() {
        return fullIconPath;
    }

    public void setFullIconPath(String fullIconPath) {
        this.fullIconPath = fullIconPath;
    }

    public List<BusinessService> getBussinessServices() {
        return bussinessServices;
    }

    public void setBussinessServices(List<BusinessService> bussinessServices) {
        this.bussinessServices = bussinessServices;
    }

    public List<Company> getAssociatedCompanies() {
        return associatedCompanies;
    }

    public void setAssociatedCompanies(List<Company> associatedCompanies) {
        this.associatedCompanies = associatedCompanies;
    }
}

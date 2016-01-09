package com.lap.bellapp.bellapp_android.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by juangarcia on 10/31/15.
 */
public class Company {

    @SerializedName("CompanyID")
    public int companyId;

    @SerializedName("Title")
    public String title;

    @SerializedName("Latitude")
    public String latitude;

    @SerializedName("Longitude")
    public String longitude;

    @SerializedName("Content")
    public String content;

    @SerializedName("ImageName")
    public String imageName;

    @SerializedName("FullImagePath")
    public String fullImagePath;

    @SerializedName("Address")
    public String address;

    @SerializedName("Email")
    public String email;

    @SerializedName("City")
    public String city;

    @SerializedName("Neighborhood")
    public String neighborhood;

    @SerializedName("Country")
    public String country;

    @SerializedName("Services")
    public List<BusinessService> services;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFullImagePath() {
        return fullImagePath;
    }

    public void setFullImagePath(String fullImagePath) {
        this.fullImagePath = fullImagePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<BusinessService> getServices() {
        return services;
    }

    public void setServices(List<BusinessService> services) {
        this.services = services;
    }
}

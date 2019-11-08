package com.ab.hicarecommercialapp.model.branch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Arjun Bhatt on 10/16/2019.
 */
public class Branch extends RealmObject {
    @SerializedName("BranchCode")
    @Expose
    private String branchCode;
    @SerializedName("CompanyId")
    @Expose
    private Integer companyId;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("AccountId")
    @Expose
    private String accountId;
    @SerializedName("AccountKey")
    @Expose
    private String accountKey;
    @SerializedName("ParentAccountKey")
    @Expose
    private String parentAccountKey;
    @SerializedName("CompanyCode")
    @Expose
    private String companyCode;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("Distance")
    @Expose
    private Double distance;
    @SerializedName("FlatNumber")
    @Expose
    private String flatNumber;
    @SerializedName("BuildingName")
    @Expose
    private String buildingName;
    @SerializedName("Landmark")
    @Expose
    private String landmark;
    @SerializedName("Locality")
    @Expose
    private String locality;
    @SerializedName("BillingStreet")
    @Expose
    private String billingStreet;
    @SerializedName("BillingCity")
    @Expose
    private String billingCity;
    @SerializedName("BillingState")
    @Expose
    private String billingState;
    @SerializedName("PostalCode")
    @Expose
    private String postalCode;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getParentAccountKey() {
        return parentAccountKey;
    }

    public void setParentAccountKey(String parentAccountKey) {
        this.parentAccountKey = parentAccountKey;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

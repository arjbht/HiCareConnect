package com.ab.hicarecommercialapp.model.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceTechnicianDetails implements Parcelable {
    @SerializedName("Id")
    @Expose
    private String Technicianid;
    @SerializedName("Name")
    @Expose
    private String TechnicianName;
    @SerializedName("Technician_Mobile__c")
    @Expose
    private String TechnicianMobile;

    protected ServiceTechnicianDetails(Parcel in) {
        Technicianid = in.readString();
        TechnicianName = in.readString();
        TechnicianMobile = in.readString();
    }

    public static final Creator<ServiceTechnicianDetails> CREATOR = new Creator<ServiceTechnicianDetails>() {
        @Override
        public ServiceTechnicianDetails createFromParcel(Parcel in) {
            return new ServiceTechnicianDetails(in);
        }

        @Override
        public ServiceTechnicianDetails[] newArray(int size) {
            return new ServiceTechnicianDetails[size];
        }
    };

    public String getTechnicianid() {
        return Technicianid;
    }

    public void setTechnicianid(String technicianid) {
        Technicianid = technicianid;
    }

    public String getTechnicianName() {
        return TechnicianName;
    }

    public void setTechnicianName(String technicianName) {
        TechnicianName = technicianName;
    }

    public String getTechnicianMobile() {
        return TechnicianMobile;
    }

    public void setTechnicianMobile(String technicianMobile) {
        TechnicianMobile = technicianMobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Technicianid);
        dest.writeString(TechnicianName);
        dest.writeString(TechnicianMobile);
    }
}

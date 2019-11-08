package com.ab.hicarecommercialapp.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseDetail implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Unit_Value__c")
    @Expose
    private String UnitValue;

    protected HouseDetail(Parcel in) {
        id = in.readString();
        name = in.readString();
        UnitValue = in.readString();
    }

    public static final Creator<HouseDetail> CREATOR = new Creator<HouseDetail>() {
        @Override
        public HouseDetail createFromParcel(Parcel in) {
            return new HouseDetail(in);
        }

        @Override
        public HouseDetail[] newArray(int size) {
            return new HouseDetail[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitValue() {
        return UnitValue;
    }

    public void setUnitValue(String unitValue) {
        UnitValue = unitValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(UnitValue);
    }
}

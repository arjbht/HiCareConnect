package com.ab.hicarecommercialapp.model.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ServiceResponse implements Parcelable {
    @SerializedName("IsSuccess") @Expose
    private Boolean IsSuccess;
    @SerializedName("Message") @Expose
    private String message;
    @SerializedName("Data") @Expose
    private List<Service_Details> data = null;

    protected ServiceResponse(Parcel in) {
        byte tmpIsSuccess = in.readByte();
        IsSuccess = tmpIsSuccess == 0 ? null : tmpIsSuccess == 1;
        message = in.readString();
    }

    public static final Creator<ServiceResponse> CREATOR = new Creator<ServiceResponse>() {
        @Override
        public ServiceResponse createFromParcel(Parcel in) {
            return new ServiceResponse(in);
        }

        @Override
        public ServiceResponse[] newArray(int size) {
            return new ServiceResponse[size];
        }
    };

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public void setSuccess(Boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Service_Details> getData() {
        return data;
    }

    public void setData(List<Service_Details> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (IsSuccess == null ? 0 : IsSuccess ? 1 : 2));
        dest.writeString(message);
    }
}

package com.ab.hicarecommercialapp.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/1/2019.
 */
public class UpcomingServiceResponse {
    @SerializedName("IsSuccess") @Expose
    private Boolean IsSuccess;
    @SerializedName("Message") @Expose
    private String message;
    @SerializedName("Data") @Expose
    private List<ServiceTasks> data = null;

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

    public List<ServiceTasks> getData() {
        return data;
    }

    public void setData(List<ServiceTasks> data) {
        this.data = data;
    }
}

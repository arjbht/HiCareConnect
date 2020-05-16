package com.ab.hicarecommercialapp.model.notification;

import com.ab.hicarecommercialapp.model.order.Orders;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/20/2019.
 */
public class NotificationResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<Notifications> data = null;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Notifications> getData() {
        return data;
    }

    public void setData(List<Notifications> data) {
        this.data = data;
    }
}

package com.ab.hicarecommercialapp.model.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 9/26/2019.
 */
public class OrderResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<Orders> data = null;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Orders> getData() {
        return data;
    }

    public void setData(List<Orders> data) {
        this.data = data;
    }
}

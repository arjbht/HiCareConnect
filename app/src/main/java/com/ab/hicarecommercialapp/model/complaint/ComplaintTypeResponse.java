package com.ab.hicarecommercialapp.model.complaint;

import com.ab.hicarecommercialapp.view.dashboard.fragment.complaints.ComplaintType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/20/2019.
 */
public class ComplaintTypeResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean IsSuccess;
    @SerializedName("Message")
    @Expose
    private String message = null;
    @SerializedName("Data")
    @Expose
    private List<ComplaintType> data = null;

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

    public List<ComplaintType> getData() {
        return data;
    }

    public void setData(List<ComplaintType> data) {
        this.data = data;
    }
}

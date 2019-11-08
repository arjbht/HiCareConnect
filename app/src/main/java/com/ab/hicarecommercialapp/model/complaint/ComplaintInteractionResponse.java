package com.ab.hicarecommercialapp.model.complaint;

import com.ab.hicarecommercialapp.view.dashboard.fragment.complaints.ComplaintType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/3/2019.
 */
public class ComplaintInteractionResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean IsSuccess;
    @SerializedName("Message")
    @Expose
    private String message = null;
    @SerializedName("Data")
    @Expose
    private List<InteractionLogs> data = null;

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

    public List<InteractionLogs> getData() {
        return data;
    }

    public void setData(List<InteractionLogs> data) {
        this.data = data;
    }
}

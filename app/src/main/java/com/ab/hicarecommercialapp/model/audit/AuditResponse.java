package com.ab.hicarecommercialapp.model.audit;

import com.ab.hicarecommercialapp.model.graph.GraphData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/30/2019.
 */
public class AuditResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<Audit> data = null;

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

    public List<Audit> getData() {
        return data;
    }

    public void setData(List<Audit> data) {
        this.data = data;
    }

}

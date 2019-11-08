package com.ab.hicarecommercialapp.model.graph;

import com.ab.hicarecommercialapp.model.invoice.Invoices;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/4/2019.
 */
public class GraphResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<GraphData> data = null;

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

    public List<GraphData> getData() {
        return data;
    }

    public void setData(List<GraphData> data) {
        this.data = data;
    }


}

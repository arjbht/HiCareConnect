package com.ab.hicarecommercialapp.model.company;

import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/13/2019.
 */
public class CompanyResponse {

    @SerializedName("IsSuccess")
    @Expose
    private Boolean IsSuccess;

    @SerializedName("Data")
    @Expose
    private Company data;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;


    public Boolean getSuccess() {
        return IsSuccess;
    }

    public void setSuccess(Boolean success) {
        IsSuccess = success;
    }

    public Company getData() {
        return data;
    }

    public void setData(Company data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

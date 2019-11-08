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
    @SerializedName("Message")
    @Expose
    private String message = null;
    @SerializedName("Data")
    @Expose
    private Company data = null;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage = null;


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

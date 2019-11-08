package com.ab.hicarecommercialapp.model.branch;

import com.ab.hicarecommercialapp.model.company.Company;
import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Arjun Bhatt on 10/16/2019.
 */
public class BranchResponse extends RealmObject {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean IsSuccess;
    @SerializedName("Message")
    @Expose
    private String message = null;
    @SerializedName("Data")
    @Expose
    private RealmList<Branch> data = null;
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

    public RealmList<Branch> getData() {
        return data;
    }

    public void setData(RealmList<Branch> data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

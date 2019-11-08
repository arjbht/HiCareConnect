package com.ab.hicarecommercialapp.model.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 11/2/2019.
 */
public class ComplaintDetailResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private Complaints data ;

    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

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

    public Complaints getData() {
        return data;
    }

    public void setData(Complaints data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

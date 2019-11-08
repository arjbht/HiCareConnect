package com.ab.hicarecommercialapp.model.register_user;

import com.ab.hicarecommercialapp.model.login.Verify;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 10/14/2019.
 */
public class RegisterResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean IsSuccess;
    @SerializedName("Message")
    @Expose
    private String message = null;
    @SerializedName("Data")
    @Expose
    private Verify data = null;
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

    public Verify getData() {
        return data;
    }

    public void setData(Verify data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

package com.ab.hicarecommercialapp.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceRequest {
    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("AccountNo")
    @Expose
    private String AccountNo;
    @SerializedName("OrderNo")
    @Expose
    private String OrderNo;
    @SerializedName("CaseNo")
    @Expose
    private String CaseNo;
    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;
    @SerializedName("DataType")
    @Expose
    private String DataType;
    @SerializedName("IsAdmin")
    @Expose
    private Boolean IsAdmin;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getCaseNo() {
        return CaseNo;
    }

    public void setCaseNo(String caseNo) {
        CaseNo = caseNo;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public Boolean getAdmin() {
        return IsAdmin;
    }

    public void setAdmin(Boolean admin) {
        IsAdmin = admin;
    }
}

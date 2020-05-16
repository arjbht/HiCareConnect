package com.ab.hicarecommercialapp.model.graph;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 10/7/2019.
 */
public class GraphRequest {
    @SerializedName("UserId")
    @Expose
    private String UserId;

    @SerializedName("AccountNo")
    @Expose
    private String AccountNo;

    @SerializedName("StartDate")
    @Expose
    private String StartDate;

    @SerializedName("EndDate")
    @Expose
    private String EndDate;

    @SerializedName("ReportType")
    @Expose
    private String ReportType;

    @SerializedName("IsChildAccount")
    @Expose
    private Boolean IsChildAccount;

    @SerializedName("ShowServiceDayWise")
    @Expose
    private Boolean ShowServiceDayWise;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String reportType) {
        ReportType = reportType;
    }

    public Boolean getChildAccount() {
        return IsChildAccount;
    }

    public void setChildAccount(Boolean childAccount) {
        IsChildAccount = childAccount;
    }

    public Boolean getShowServiceDayWise() {
        return ShowServiceDayWise;
    }

    public void setShowServiceDayWise(Boolean showServiceDayWise) {
        ShowServiceDayWise = showServiceDayWise;
    }
}

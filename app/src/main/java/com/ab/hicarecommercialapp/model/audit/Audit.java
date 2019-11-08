package com.ab.hicarecommercialapp.model.audit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 10/30/2019.
 */
public class Audit {
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("audit_date")
    @Expose
    private String auditDate;
    @SerializedName("audit_rating")
    @Expose
    private String auditRating;
    @SerializedName("audit_customer_feedback")
    @Expose
    private String auditCustomerFeedback;
    @SerializedName("hicare_actions")
    @Expose
    private String hicareActions;
    @SerializedName("pdf_report")
    @Expose
    private String pdfReport;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditRating() {
        return auditRating;
    }

    public void setAuditRating(String auditRating) {
        this.auditRating = auditRating;
    }

    public String getAuditCustomerFeedback() {
        return auditCustomerFeedback;
    }

    public void setAuditCustomerFeedback(String auditCustomerFeedback) {
        this.auditCustomerFeedback = auditCustomerFeedback;
    }

    public String getHicareActions() {
        return hicareActions;
    }

    public void setHicareActions(String hicareActions) {
        this.hicareActions = hicareActions;
    }

    public String getPdfReport() {
        return pdfReport;
    }

    public void setPdfReport(String pdfReport) {
        this.pdfReport = pdfReport;
    }

}

package com.ab.hicarecommercialapp.model.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/21/2019.
 */
public class CreateComplaintRequest {

    @SerializedName("AttachmentList")
    @Expose
    private List<Attachment> attachmentList = null;

    @SerializedName("ComplaintId")
    @Expose
    private Integer complaintId;
    @SerializedName("AccountNo")
    @Expose
    private String accountNo;
    @SerializedName("UserName")
    @Expose
    private String UserName;
    @SerializedName("AccountName")
    @Expose
    private String AccountName;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Mobile")
    @Expose
    private String Mobile;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("ComplaintType")
    @Expose
    private String complaintType;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Attachment")
    @Expose
    private String attachment;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("DataType")
    @Expose
    private String dataType;
    @SerializedName("FreshdeskTicketId")
    @Expose
    private String FreshdeskTicketId;

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getFreshdeskTicketId() {
        return FreshdeskTicketId;
    }

    public void setFreshdeskTicketId(String freshdeskTicketId) {
        FreshdeskTicketId = freshdeskTicketId;
    }
}

package com.ab.hicarecommercialapp.model.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 11/5/2019.
 */
public class Attachment {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Complaint_Id")
    @Expose
    private Integer complaintId;
    @SerializedName("Attachment")
    @Expose
    private String attachment;
    @SerializedName("FileName")
    @Expose
    private String fileName;
    @SerializedName("Attachment_Url")
    @Expose
    private String attachmentUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }
}

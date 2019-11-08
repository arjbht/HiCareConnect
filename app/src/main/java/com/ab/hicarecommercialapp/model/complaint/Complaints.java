package com.ab.hicarecommercialapp.model.complaint;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 9/30/2019.
 */
public class Complaints implements Parcelable {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Complaint_Type")
    @Expose
    private String complaintType;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Attachment_Url")
    @Expose
    private String attachmentUrl;
    @SerializedName("Remark")
    @Expose
    private String remark;
    @SerializedName("AccountKey")
    @Expose
    private String accountKey;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("createdon")
    @Expose
    private String createdon;
    @SerializedName("createdon_text")
    @Expose
    private String createdonText;
    @SerializedName("AdminRemark")
    @Expose
    private Object adminRemark;
    @SerializedName("Freshdesk_Ticket_Id")
    @Expose
    private String freshdeskTicketId;
    @SerializedName("AttachmentList")
    @Expose
    private List<Attachment> attachmentList = null;


    protected Complaints(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        complaintType = in.readString();
        title = in.readString();
        attachmentUrl = in.readString();
        remark = in.readString();
        accountKey = in.readString();
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        status = in.readString();
        createdon = in.readString();
        createdonText = in.readString();
        freshdeskTicketId = in.readString();
    }

    public static final Creator<Complaints> CREATOR = new Creator<Complaints>() {
        @Override
        public Complaints createFromParcel(Parcel in) {
            return new Complaints(in);
        }

        @Override
        public Complaints[] newArray(int size) {
            return new Complaints[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
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

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getCreatedonText() {
        return createdonText;
    }

    public void setCreatedonText(String createdonText) {
        this.createdonText = createdonText;
    }

    public Object getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(Object adminRemark) {
        this.adminRemark = adminRemark;
    }

    public String getFreshdeskTicketId() {
        return freshdeskTicketId;
    }

    public void setFreshdeskTicketId(String freshdeskTicketId) {
        this.freshdeskTicketId = freshdeskTicketId;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(complaintType);
        parcel.writeString(title);
        parcel.writeString(attachmentUrl);
        parcel.writeString(remark);
        parcel.writeString(accountKey);
        if (userId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userId);
        }
        parcel.writeString(status);
        parcel.writeString(createdon);
        parcel.writeString(createdonText);
        parcel.writeString(freshdeskTicketId);
    }
}

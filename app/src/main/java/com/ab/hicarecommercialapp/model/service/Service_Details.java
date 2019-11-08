package com.ab.hicarecommercialapp.model.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Service_Details implements Parcelable {
    @SerializedName("Sequence_No__c")
    @Expose
    private Integer Sequence_No;

    @SerializedName("Order_Number__c")
    @Expose
    private String Order_Number__c;

    @SerializedName("Service_Plan__c")
    @Expose
    private String Service_Plan__c;


    @SerializedName("Scheduled_Date_Time__c_Text")
    @Expose
    private String Scheduled_Date;
    @SerializedName("Status__c")
    @Expose
    private String Status;
    @SerializedName("Completed_Date_Time__c_Text")
    @Expose
    private String Completed_Date;
    @SerializedName("ServiceStep__c")
    @Expose
    private String Service_Step;

    @SerializedName("Service_Group_Code__c")
    @Expose
    private String Service_Group_Code__c;

    @SerializedName("TotalSRCount")
    @Expose
    private Integer TotalSRCount;

    @SerializedName("RelatedTasks")
    @Expose
    private List<ServiceTasks> tasksList = null;

    @SerializedName("Assigned_Resources")
    @Expose
    private List<ServiceTechnicianDetails> technicianDetails = null;


    protected Service_Details(Parcel in) {
        if (in.readByte() == 0) {
            Sequence_No = null;
        } else {
            Sequence_No = in.readInt();
        }
        Order_Number__c = in.readString();
        Service_Plan__c = in.readString();
        Scheduled_Date = in.readString();
        Status = in.readString();
        Completed_Date = in.readString();
        Service_Step = in.readString();
        Service_Group_Code__c = in.readString();
        if (in.readByte() == 0) {
            TotalSRCount = null;
        } else {
            TotalSRCount = in.readInt();
        }
        tasksList = in.createTypedArrayList(ServiceTasks.CREATOR);
        technicianDetails = in.createTypedArrayList(ServiceTechnicianDetails.CREATOR);
    }

    public static final Creator<Service_Details> CREATOR = new Creator<Service_Details>() {
        @Override
        public Service_Details createFromParcel(Parcel in) {
            return new Service_Details(in);
        }

        @Override
        public Service_Details[] newArray(int size) {
            return new Service_Details[size];
        }
    };

    public Integer getSequence_No() {
        return Sequence_No;
    }

    public void setSequence_No(Integer sequence_No) {
        Sequence_No = sequence_No;
    }

    public String getScheduled_Date() {
        return Scheduled_Date;
    }

    public void setScheduled_Date(String scheduled_Date) {
        Scheduled_Date = scheduled_Date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCompleted_Date() {
        return Completed_Date;
    }

    public void setCompleted_Date(String completed_Date) {
        Completed_Date = completed_Date;
    }

    public String getService_Step() {
        return Service_Step;
    }

    public void setService_Step(String service_Step) {
        Service_Step = service_Step;
    }

    public List<ServiceTasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<ServiceTasks> tasksList) {
        this.tasksList = tasksList;
    }

    public String getOrder_Number__c() {
        return Order_Number__c;
    }

    public void setOrder_Number__c(String order_Number__c) {
        Order_Number__c = order_Number__c;
    }

    public String getService_Plan__c() {
        return Service_Plan__c;
    }

    public void setService_Plan__c(String service_Plan__c) {
        Service_Plan__c = service_Plan__c;
    }

    public String getService_Group_Code__c() {
        return Service_Group_Code__c;
    }

    public void setService_Group_Code__c(String service_Group_Code__c) {
        Service_Group_Code__c = service_Group_Code__c;
    }

    public Integer getTotalSRCount() {
        return TotalSRCount;
    }

    public void setTotalSRCount(Integer totalSRCount) {
        TotalSRCount = totalSRCount;
    }

    public List<ServiceTechnicianDetails> getTechnicianDetails() {
        return technicianDetails;
    }

    public void setTechnicianDetails(List<ServiceTechnicianDetails> technicianDetails) {
        this.technicianDetails = technicianDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (Sequence_No == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(Sequence_No);
        }
        parcel.writeString(Order_Number__c);
        parcel.writeString(Service_Plan__c);
        parcel.writeString(Scheduled_Date);
        parcel.writeString(Status);
        parcel.writeString(Completed_Date);
        parcel.writeString(Service_Step);
        parcel.writeString(Service_Group_Code__c);
        if (TotalSRCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(TotalSRCount);
        }
        parcel.writeTypedList(tasksList);
        parcel.writeTypedList(technicianDetails);
    }
}

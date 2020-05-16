package com.ab.hicarecommercialapp.model.service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceTasks implements Parcelable {
    @SerializedName("HR_Status__c")
    @Expose
    private String Technician_Status;

    @SerializedName("HR_Appointment_Start_Date__c")
    @Expose
    private String CustomerAppointedStartDate;

    @SerializedName("HR_Appointment_Start_Time_AMPM__c")
    @Expose
    private String CustomerAppointedStartTime;

    @SerializedName("HR_Appointment_Finish_Date__c")
    @Expose
    private String CustomerAppointedFinishDate;
    @SerializedName("HR_Appointment_Finish_Time_AMPM__c")
    @Expose
    private String CustomerAppointedFinishTime;
    @SerializedName("HR_Assignment_Start_Date__c")
    @Expose
    private String CustomerAssignmentStartDate;
    @SerializedName("HR_Assignment_Start_Time_AMPM__c")
    @Expose
    private String CustomerAssignmentStartTime;
    @SerializedName("HR_Assignment_Finish_Date__c")
    @Expose
    private String CustomerAssignmentFinishDate;
    @SerializedName("HR_Assignment_Finish_Time_AMPM__c")
    @Expose
    private String CustomerAssignmentFinishTime;
    @SerializedName("AppointmentDateTime")
    @Expose
    private String AppointmentDateTime;

    @SerializedName("AppointmentDate")
    @Expose
    private String AppointmentDate;

    @SerializedName("AppointmentTime")
    @Expose
    private String AppointmentTime;

    @SerializedName("Customer_Rating__c")
    @Expose
    private String Customer_Rating__c;

    @SerializedName("OrderNumber__c")
    @Expose
    private String OrderNumber__c;

    @SerializedName("Service_Step__c")
    @Expose
    private String Service_Step__c;

    @SerializedName("Incomplete_reason__c")
    @Expose
    private String Incomplete_reason__c;

    @SerializedName("Service_Sequence_Number__c")
    @Expose
    private Integer Service_Sequence_Number__c;

    @SerializedName("Service_Group_Code__c")
    @Expose
    private String Service_Group_Code__c;

    @SerializedName("TotalSRCount")
    @Expose
    private Integer TotalSRCount;

    @SerializedName("HR_Assigned_Resource__r")
    @Expose
    private ServiceTechnicianDetails TechnicianDetail;

    @SerializedName("Id")
    @Expose
    private String taskId;


    protected ServiceTasks(Parcel in) {
        Technician_Status = in.readString();
        CustomerAppointedStartDate = in.readString();
        CustomerAppointedStartTime = in.readString();
        CustomerAppointedFinishDate = in.readString();
        CustomerAppointedFinishTime = in.readString();
        CustomerAssignmentStartDate = in.readString();
        CustomerAssignmentStartTime = in.readString();
        CustomerAssignmentFinishDate = in.readString();
        CustomerAssignmentFinishTime = in.readString();
        AppointmentDateTime = in.readString();
        AppointmentDate = in.readString();
        AppointmentTime = in.readString();
        Customer_Rating__c = in.readString();
        OrderNumber__c = in.readString();
        Service_Step__c = in.readString();
        Incomplete_reason__c = in.readString();
        if (in.readByte() == 0) {
            Service_Sequence_Number__c = null;
        } else {
            Service_Sequence_Number__c = in.readInt();
        }
        Service_Group_Code__c = in.readString();
        if (in.readByte() == 0) {
            TotalSRCount = null;
        } else {
            TotalSRCount = in.readInt();
        }
        TechnicianDetail = in.readParcelable(ServiceTechnicianDetails.class.getClassLoader());
        taskId = in.readString();
    }

    public static final Creator<ServiceTasks> CREATOR = new Creator<ServiceTasks>() {
        @Override
        public ServiceTasks createFromParcel(Parcel in) {
            return new ServiceTasks(in);
        }

        @Override
        public ServiceTasks[] newArray(int size) {
            return new ServiceTasks[size];
        }
    };

    public String getTechnician_Status() {
        return Technician_Status;
    }

    public void setTechnician_Status(String technician_Status) {
        Technician_Status = technician_Status;
    }

    public String getCustomerAppointedStartDate() {
        return CustomerAppointedStartDate;
    }

    public void setCustomerAppointedStartDate(String customerAppointedStartDate) {
        CustomerAppointedStartDate = customerAppointedStartDate;
    }

    public String getCustomerAppointedStartTime() {
        return CustomerAppointedStartTime;
    }

    public void setCustomerAppointedStartTime(String customerAppointedStartTime) {
        CustomerAppointedStartTime = customerAppointedStartTime;
    }

    public String getCustomerAppointedFinishDate() {
        return CustomerAppointedFinishDate;
    }

    public void setCustomerAppointedFinishDate(String customerAppointedFinishDate) {
        CustomerAppointedFinishDate = customerAppointedFinishDate;
    }

    public String getCustomerAppointedFinishTime() {
        return CustomerAppointedFinishTime;
    }

    public void setCustomerAppointedFinishTime(String customerAppointedFinishTime) {
        CustomerAppointedFinishTime = customerAppointedFinishTime;
    }

    public String getCustomerAssignmentStartDate() {
        return CustomerAssignmentStartDate;
    }

    public void setCustomerAssignmentStartDate(String customerAssignmentStartDate) {
        CustomerAssignmentStartDate = customerAssignmentStartDate;
    }

    public String getCustomerAssignmentStartTime() {
        return CustomerAssignmentStartTime;
    }

    public void setCustomerAssignmentStartTime(String customerAssignmentStartTime) {
        CustomerAssignmentStartTime = customerAssignmentStartTime;
    }

    public String getCustomerAssignmentFinishDate() {
        return CustomerAssignmentFinishDate;
    }

    public void setCustomerAssignmentFinishDate(String customerAssignmentFinishDate) {
        CustomerAssignmentFinishDate = customerAssignmentFinishDate;
    }

    public String getCustomerAssignmentFinishTime() {
        return CustomerAssignmentFinishTime;
    }

    public void setCustomerAssignmentFinishTime(String customerAssignmentFinishTime) {
        CustomerAssignmentFinishTime = customerAssignmentFinishTime;
    }

    public ServiceTechnicianDetails getTechnicianDetail() {
        return TechnicianDetail;
    }

    public void setTechnicianDetail(ServiceTechnicianDetails technicianDetail) {
        TechnicianDetail = technicianDetail;
    }


    public String getAppointmentDateTime() {
        return AppointmentDateTime;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        AppointmentDateTime = appointmentDateTime;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public String getCustomer_Rating__c() {
        return Customer_Rating__c;
    }

    public void setCustomer_Rating__c(String customer_Rating__c) {
        Customer_Rating__c = customer_Rating__c;
    }

    public String getOrderNumber__c() {
        return OrderNumber__c;
    }

    public void setOrderNumber__c(String orderNumber__c) {
        OrderNumber__c = orderNumber__c;
    }

    public String getService_Step__c() {
        return Service_Step__c;
    }

    public void setService_Step__c(String service_Step__c) {
        Service_Step__c = service_Step__c;
    }

    public Integer getService_Sequence_Number__c() {
        return Service_Sequence_Number__c;
    }

    public void setService_Sequence_Number__c(Integer service_Sequence_Number__c) {
        Service_Sequence_Number__c = service_Sequence_Number__c;
    }

    public String getIncomplete_reason__c() {
        return Incomplete_reason__c;
    }

    public void setIncomplete_reason__c(String incomplete_reason__c) {
        Incomplete_reason__c = incomplete_reason__c;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Technician_Status);
        parcel.writeString(CustomerAppointedStartDate);
        parcel.writeString(CustomerAppointedStartTime);
        parcel.writeString(CustomerAppointedFinishDate);
        parcel.writeString(CustomerAppointedFinishTime);
        parcel.writeString(CustomerAssignmentStartDate);
        parcel.writeString(CustomerAssignmentStartTime);
        parcel.writeString(CustomerAssignmentFinishDate);
        parcel.writeString(CustomerAssignmentFinishTime);
        parcel.writeString(AppointmentDateTime);
        parcel.writeString(AppointmentDate);
        parcel.writeString(AppointmentTime);
        parcel.writeString(Customer_Rating__c);
        parcel.writeString(OrderNumber__c);
        parcel.writeString(Service_Step__c);
        parcel.writeString(Incomplete_reason__c);
        if (Service_Sequence_Number__c == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(Service_Sequence_Number__c);
        }
        parcel.writeString(Service_Group_Code__c);
        if (TotalSRCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(TotalSRCount);
        }
        parcel.writeParcelable(TechnicianDetail, i);
        parcel.writeString(taskId);
    }
}

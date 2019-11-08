package com.ab.hicarecommercialapp.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicePlan implements Parcelable {

    @SerializedName("Service_Code__c")
    @Expose
    private String serviceCodeC;
    @SerializedName("SP_Code__c")
    @Expose
    private String sPCodeC;
    @SerializedName("ServiceGroupCode__c")
    @Expose
    private String serviceGroupCodeC;
    @SerializedName("Active__c")
    @Expose
    private String activeC;
    @SerializedName("Account_Type__c")
    @Expose
    private String accountTypeC;
    @SerializedName("Customer_Type__r")
    @Expose
    private String customerTypeR;
    @SerializedName("Service_Area__r")
    @Expose
    private String serviceAreaR;
    @SerializedName("Sub_Type__r")
    @Expose
    private String subTypeR;
    @SerializedName("Frequency__c")
    @Expose
    private String frequencyC;
    @SerializedName("Number_of_Treatments__c")
    @Expose
    private String numberOfTreatmentsC;
    @SerializedName("Validity_Days__c")
    @Expose
    private String validityDaysC;
    @SerializedName("Number_of_Checkups__c")
    @Expose
    private String numberOfCheckupsC;
    @SerializedName("Cost_Sheet_Service_Plan__c")
    @Expose
    private Boolean costSheetServicePlanC;
    @SerializedName("SPCode_PlanName")
    @Expose
    private String sPCodePlanName;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DataType")
    @Expose
    private String dataType;

    protected ServicePlan(Parcel in) {
        serviceCodeC = in.readString();
        sPCodeC = in.readString();
        serviceGroupCodeC = in.readString();
        activeC = in.readString();
        customerTypeR = in.readString();
        serviceAreaR = in.readString();
        subTypeR = in.readString();
        frequencyC = in.readString();
        numberOfTreatmentsC = in.readString();
        validityDaysC = in.readString();
        numberOfCheckupsC = in.readString();
        byte tmpCostSheetServicePlanC = in.readByte();
        costSheetServicePlanC = tmpCostSheetServicePlanC == 0 ? null : tmpCostSheetServicePlanC == 1;
        sPCodePlanName = in.readString();
        id = in.readString();
        name = in.readString();
        dataType = in.readString();
    }

    public static final Creator<ServicePlan> CREATOR = new Creator<ServicePlan>() {
        @Override
        public ServicePlan createFromParcel(Parcel in) {
            return new ServicePlan(in);
        }

        @Override
        public ServicePlan[] newArray(int size) {
            return new ServicePlan[size];
        }
    };

    public String getServiceCodeC() {
        return serviceCodeC;
    }

    public void setServiceCodeC(String serviceCodeC) {
        this.serviceCodeC = serviceCodeC;
    }

    public String getsPCodeC() {
        return sPCodeC;
    }

    public void setsPCodeC(String sPCodeC) {
        this.sPCodeC = sPCodeC;
    }

    public String getServiceGroupCodeC() {
        return serviceGroupCodeC;
    }

    public void setServiceGroupCodeC(String serviceGroupCodeC) {
        this.serviceGroupCodeC = serviceGroupCodeC;
    }

    public String getActiveC() {
        return activeC;
    }

    public void setActiveC(String activeC) {
        this.activeC = activeC;
    }

    public Object getAccountTypeC() {
        return accountTypeC;
    }

    public void setAccountTypeC(String accountTypeC) {
        this.accountTypeC = accountTypeC;
    }

    public String getCustomerTypeR() {
        return customerTypeR;
    }

    public void setCustomerTypeR(String customerTypeR) {
        this.customerTypeR = customerTypeR;
    }

    public String getServiceAreaR() {
        return serviceAreaR;
    }

    public void setServiceAreaR(String serviceAreaR) {
        this.serviceAreaR = serviceAreaR;
    }

    public String getSubTypeR() {
        return subTypeR;
    }

    public void setSubTypeR(String subTypeR) {
        this.subTypeR = subTypeR;
    }

    public String getFrequencyC() {
        return frequencyC;
    }

    public void setFrequencyC(String frequencyC) {
        this.frequencyC = frequencyC;
    }

    public String getNumberOfTreatmentsC() {
        return numberOfTreatmentsC;
    }

    public void setNumberOfTreatmentsC(String numberOfTreatmentsC) {
        this.numberOfTreatmentsC = numberOfTreatmentsC;
    }

    public String getValidityDaysC() {
        return validityDaysC;
    }

    public void setValidityDaysC(String validityDaysC) {
        this.validityDaysC = validityDaysC;
    }

    public String getNumberOfCheckupsC() {
        return numberOfCheckupsC;
    }

    public void setNumberOfCheckupsC(String numberOfCheckupsC) {
        this.numberOfCheckupsC = numberOfCheckupsC;
    }

    public Boolean getCostSheetServicePlanC() {
        return costSheetServicePlanC;
    }

    public void setCostSheetServicePlanC(Boolean costSheetServicePlanC) {
        this.costSheetServicePlanC = costSheetServicePlanC;
    }

    public String getsPCodePlanName() {
        return sPCodePlanName;
    }

    public void setsPCodePlanName(String sPCodePlanName) {
        this.sPCodePlanName = sPCodePlanName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(serviceCodeC);
        parcel.writeString(sPCodeC);
        parcel.writeString(serviceGroupCodeC);
        parcel.writeString(activeC);
        parcel.writeString(customerTypeR);
        parcel.writeString(serviceAreaR);
        parcel.writeString(subTypeR);
        parcel.writeString(frequencyC);
        parcel.writeString(numberOfTreatmentsC);
        parcel.writeString(validityDaysC);
        parcel.writeString(numberOfCheckupsC);
        parcel.writeByte((byte) (costSheetServicePlanC == null ? 0 : costSheetServicePlanC ? 1 : 2));
        parcel.writeString(sPCodePlanName);
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(dataType);
    }
}

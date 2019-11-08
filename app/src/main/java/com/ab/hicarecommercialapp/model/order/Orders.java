package com.ab.hicarecommercialapp.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orders implements Parcelable {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Service_SP_Code__c")
    @Expose
    private String SeviceCode;
    @SerializedName("Order_Number__c")
    @Expose
    private String OrderNumber;
    @SerializedName("End_Date__c")
    @Expose
    private String EndDate;
    @SerializedName("Start_Date__c")
    @Expose
    private String StartDate;
    @SerializedName("Status__c")
    @Expose
    private String CustStatus;
    @SerializedName("Service_Plan_Name__c")
    @Expose
    private String ServicePlanName;
    @SerializedName("Order_Value_with_Tax__c")
    @Expose
    private String Payment;
    @SerializedName("Payment_Type__c")
    @Expose
    private String PaymentType;
    @SerializedName("House_Type__r")
    @Expose
    private HouseDetail houseDetail;
    @SerializedName("Account_Name__r")
    @Expose
    private AccountDetail accountDetail;
    @SerializedName("Quantity__c")
    @Expose
    private String Quantity;
    @SerializedName("Sub_Type1__c")
    @Expose
    private String FlatType;
    @SerializedName("DataType")
    @Expose
    private String ServiceType;
    @SerializedName("Standard_Value__c")
    @Expose
    private String Standard_Value__c;
    @SerializedName("Discount_Offered__c")
    @Expose
    private String Discount_Offered__c;
    @SerializedName("Service_Plan__r")
    @Expose
    private ServicePlan servicePlan;


    protected Orders(Parcel in) {
        id = in.readString();
        SeviceCode = in.readString();
        OrderNumber = in.readString();
        EndDate = in.readString();
        StartDate = in.readString();
        CustStatus = in.readString();
        ServicePlanName = in.readString();
        Payment = in.readString();
        PaymentType = in.readString();
        houseDetail = in.readParcelable(HouseDetail.class.getClassLoader());
        accountDetail = in.readParcelable(AccountDetail.class.getClassLoader());
        Quantity = in.readString();
        FlatType = in.readString();
        ServiceType = in.readString();
        Standard_Value__c = in.readString();
        Discount_Offered__c = in.readString();
        servicePlan = in.readParcelable(ServicePlan.class.getClassLoader());
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeviceCode() {
        return SeviceCode;
    }

    public void setSeviceCode(String seviceCode) {
        SeviceCode = seviceCode;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getCustStatus() {
        return CustStatus;
    }

    public void setCustStatus(String custStatus) {
        CustStatus = custStatus;
    }

    public String getServicePlanName() {
        return ServicePlanName;
    }

    public void setServicePlanName(String servicePlanName) {
        ServicePlanName = servicePlanName;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public HouseDetail getHouseDetail() {
        return houseDetail;
    }

    public void setHouseDetail(HouseDetail houseDetail) {
        this.houseDetail = houseDetail;
    }

    public AccountDetail getAccountDetail() {
        return accountDetail;
    }

    public void setAccountDetail(AccountDetail accountDetail) {
        this.accountDetail = accountDetail;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getFlatType() {
        return FlatType;
    }

    public void setFlatType(String flatType) {
        FlatType = flatType;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public ServicePlan getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(ServicePlan servicePlan) {
        this.servicePlan = servicePlan;
    }

    public String getStandard_Value__c() {
        return Standard_Value__c;
    }

    public void setStandard_Value__c(String standard_Value__c) {
        Standard_Value__c = standard_Value__c;
    }

    public String getDiscount_Offered__c() {
        return Discount_Offered__c;
    }

    public void setDiscount_Offered__c(String discount_Offered__c) {
        Discount_Offered__c = discount_Offered__c;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(SeviceCode);
        parcel.writeString(OrderNumber);
        parcel.writeString(EndDate);
        parcel.writeString(StartDate);
        parcel.writeString(CustStatus);
        parcel.writeString(ServicePlanName);
        parcel.writeString(Payment);
        parcel.writeString(PaymentType);
        parcel.writeParcelable(houseDetail, i);
        parcel.writeParcelable(accountDetail, i);
        parcel.writeString(Quantity);
        parcel.writeString(FlatType);
        parcel.writeString(ServiceType);
        parcel.writeString(Standard_Value__c);
        parcel.writeString(Discount_Offered__c);
        parcel.writeParcelable(servicePlan, i);
    }
}

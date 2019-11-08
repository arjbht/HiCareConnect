package com.ab.hicarecommercialapp.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountDetail implements Parcelable {
    @SerializedName("Id")
    @Expose
    private String Id;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Mobile__c")
    @Expose
    private String Mobile;

    @SerializedName("Account_Type__c")
    @Expose
    private String AccountType;

    @SerializedName("Email__c")
    @Expose
    private String Email;

    @SerializedName("Flat_Number__c")
    @Expose
    private String FlatNumber;

    @SerializedName("Building_Name__c")
    @Expose
    private String BuildingName;

    @SerializedName("Landmark__c")
    @Expose
    private String Landmark;

    @SerializedName("Locality_Suburb__c")
    @Expose
    private String LocalitySuburb;

    @SerializedName("BillingStreet")
    @Expose
    private String BillingStreet;

    @SerializedName("BillingPostalCode")
    @Expose
    private String BillingPoastalCode;

    protected AccountDetail(Parcel in) {
        Id = in.readString();
        Name = in.readString();
        Mobile = in.readString();
        AccountType = in.readString();
        Email = in.readString();
        FlatNumber = in.readString();
        BuildingName = in.readString();
        Landmark = in.readString();
        LocalitySuburb = in.readString();
        BillingStreet = in.readString();
        BillingPoastalCode = in.readString();
    }

    public static final Creator<AccountDetail> CREATOR = new Creator<AccountDetail>() {
        @Override
        public AccountDetail createFromParcel(Parcel in) {
            return new AccountDetail(in);
        }

        @Override
        public AccountDetail[] newArray(int size) {
            return new AccountDetail[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String  getFlatNumber() {
        return FlatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        FlatNumber = flatNumber;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }

    public String getLandmark() {
        return Landmark;
    }

    public void setLandmark(String landmark) {
        Landmark = landmark;
    }

    public String getLocalitySuburb() {
        return LocalitySuburb;
    }

    public void setLocalitySuburb(String localitySuburb) {
        LocalitySuburb = localitySuburb;
    }

    public String getBillingStreet() {
        return BillingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        BillingStreet = billingStreet;
    }

    public String getBillingPoastalCode() {
        return BillingPoastalCode;
    }

    public void setBillingPoastalCode(String billingPoastalCode) {
        BillingPoastalCode = billingPoastalCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Name);
        dest.writeString(Mobile);
        dest.writeString(AccountType);
        dest.writeString(Email);
        dest.writeString(FlatNumber);
        dest.writeString(BuildingName);
        dest.writeString(Landmark);
        dest.writeString(LocalitySuburb);
        dest.writeString(BillingStreet);
        dest.writeString(BillingPoastalCode);
    }
}

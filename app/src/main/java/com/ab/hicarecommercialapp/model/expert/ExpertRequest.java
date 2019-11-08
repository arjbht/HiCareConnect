package com.ab.hicarecommercialapp.model.expert;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 11/4/2019.
 */
public class ExpertRequest {

    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("AccountNo")
    @Expose
    private String accountNo;
    @SerializedName("AccountName")
    @Expose
    private String AccountName;
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Mobile")
    @Expose
    private String Mobile;
    @SerializedName("UserName")
    @Expose
    private String UserName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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
}

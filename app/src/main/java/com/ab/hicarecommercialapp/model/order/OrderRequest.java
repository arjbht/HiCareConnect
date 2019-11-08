package com.ab.hicarecommercialapp.model.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 9/27/2019.
 */
public class OrderRequest {

    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("AccountNo")
    @Expose
    private String accountNo;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("CaseNo")
    @Expose
    private String caseNo;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("DataType")
    @Expose
    private String dataType;
    @SerializedName("IsAdmin")
    @Expose
    private Boolean isAdmin;
    @SerializedName("PageSize")
    @Expose
    private Integer PageSize;
    @SerializedName("PageOffset")
    @Expose
    private Integer PageOffset;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getPageOffset() {
        return PageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        PageOffset = pageOffset;
    }
}

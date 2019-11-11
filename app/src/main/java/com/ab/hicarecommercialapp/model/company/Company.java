package com.ab.hicarecommercialapp.model.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 10/14/2019.
 */
public class Company {
    @SerializedName("CompanyCode")
    @Expose
    private String CompanyCode = null;

    @SerializedName("IsSelfRegisteredAllowed")
    @Expose
    private Integer IsSelfRegisteredAllowed = null;

    @SerializedName("IsGeofenceModeEnabled")
    @Expose
    private Integer IsGeofenceModeEnabled = null;

    @SerializedName("CompanyLogo")
    @Expose
    private String CompanyLogo = null;

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public Integer getIsSelfRegisteredAllowed() {
        return IsSelfRegisteredAllowed;
    }

    public void setIsSelfRegisteredAllowed(Integer isSelfRegisteredAllowed) {
        IsSelfRegisteredAllowed = isSelfRegisteredAllowed;
    }

    public Integer getIsGeofenceModeEnabled() {
        return IsGeofenceModeEnabled;
    }

    public void setIsGeofenceModeEnabled(Integer isGeofenceModeEnabled) {
        IsGeofenceModeEnabled = isGeofenceModeEnabled;
    }

    public String getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        CompanyLogo = companyLogo;
    }
}

package com.ab.hicarecommercialapp.view.company;

import com.ab.hicarecommercialapp.model.company.CompanyResponse;
import com.ab.hicarecommercialapp.model.login.LoginResponse;

/**
 * Created by Arjun Bhatt on 10/13/2019.
 */
public interface CompanyView {
    void showLoading();
    void hideLoading();
    void setCompanyResponse(CompanyResponse response);
    void onErrorLoading(String message);
}

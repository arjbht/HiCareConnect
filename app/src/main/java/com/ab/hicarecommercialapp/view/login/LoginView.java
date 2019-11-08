package com.ab.hicarecommercialapp.view.login;

import com.ab.hicarecommercialapp.model.login.LoginResponse;

import java.util.List;

/**
 * Created by Arjun Bhatt on 9/26/2019.
 */
public interface LoginView {
    void showLoading();
    void hideLoading();
    void setLoginResponse(LoginResponse response);
    void onErrorLoading(String message);
}

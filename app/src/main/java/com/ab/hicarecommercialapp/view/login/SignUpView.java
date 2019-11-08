package com.ab.hicarecommercialapp.view.login;

import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.register_user.RegisterResponse;

/**
 * Created by Arjun Bhatt on 10/14/2019.
 */
public interface SignUpView {
    void showLoading();
    void hideLoading();
    void setRegisterResponse(RegisterResponse response);
    void onErrorLoading(String message);
}

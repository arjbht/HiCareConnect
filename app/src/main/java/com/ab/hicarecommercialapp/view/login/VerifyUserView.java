package com.ab.hicarecommercialapp.view.login;

import com.ab.hicarecommercialapp.model.login.VerifyUserResponse;
import com.ab.hicarecommercialapp.model.register_user.RegisterResponse;

/**
 * Created by Arjun Bhatt on 10/14/2019.
 */
public interface VerifyUserView {
    void showLoading();
    void hideLoading();
    void setVerifyUserResponse(VerifyUserResponse response);
    void onErrorLoading(String message);
}

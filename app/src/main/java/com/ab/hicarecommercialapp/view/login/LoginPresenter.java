package com.ab.hicarecommercialapp.view.login;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 9/26/2019.
 */
public class LoginPresenter {
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    void getLogin(String mobile, String otp, String versionName, final String imei, final String device_info, String mStrPlayerId, String companyCode) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(false)
                .login("password", mobile, otp, "application/x-www-form-urlencoded", imei, versionName, device_info, mStrPlayerId, companyCode)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        view.hideLoading();

                        if (response.isSuccessful() && response.body() != null) {
                            view.setLoginResponse(response.body());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

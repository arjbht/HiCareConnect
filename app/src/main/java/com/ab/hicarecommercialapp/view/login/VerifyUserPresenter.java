package com.ab.hicarecommercialapp.view.login;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.login.VerifyUserResponse;
import com.ab.hicarecommercialapp.model.register_user.RegisterRequest;
import com.ab.hicarecommercialapp.model.register_user.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/14/2019.
 */
public class VerifyUserPresenter {

    private VerifyUserView view;

    public VerifyUserPresenter(VerifyUserView view) {
        this.view = view;
    }

    void getUserVerified(final String mobile, final String code, final boolean isResend) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(false)
                .getVerifyUser(mobile, code, isResend)
                .enqueue(new Callback<VerifyUserResponse>() {
                    @Override
                    public void onResponse(Call<VerifyUserResponse> call, Response<VerifyUserResponse> response) {
                        view.hideLoading();

                        if (response.isSuccessful() && response.body() != null) {
                            view.setVerifyUserResponse(response.body());

                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyUserResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

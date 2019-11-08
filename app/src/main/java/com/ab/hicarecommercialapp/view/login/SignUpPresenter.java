package com.ab.hicarecommercialapp.view.login;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.company.CompanyResponse;
import com.ab.hicarecommercialapp.model.register_user.RegisterRequest;
import com.ab.hicarecommercialapp.model.register_user.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/14/2019.
 */
public class SignUpPresenter {

    private SignUpView view;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
    }

    void getUserRegistered(final RegisterRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(false)
                .getUserRegistered(request)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        view.hideLoading();

                        if (response.isSuccessful() && response.body() != null) {
                            view.setRegisterResponse(response.body());
                        } else {
                                view.onErrorLoading(response.message());

                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

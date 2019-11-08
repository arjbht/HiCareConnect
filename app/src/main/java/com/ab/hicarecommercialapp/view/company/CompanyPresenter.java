package com.ab.hicarecommercialapp.view.company;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.company.CompanyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/13/2019.
 */
public class CompanyPresenter {

    private CompanyView view;

    public CompanyPresenter(CompanyView view) {
        this.view = view;
    }

    void verifyCompanyCode(final String code) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(false)
                .getVerifyCompanyCode(code)
                .enqueue(new Callback<CompanyResponse>() {
                    @Override
                    public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                        view.hideLoading();

                        if (response.isSuccessful() && response.body() != null) {
                            view.setCompanyResponse(response.body());
                        } else {
                                view.onErrorLoading(response.message());

                        }
                    }

                    @Override
                    public void onFailure(Call<CompanyResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

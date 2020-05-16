package com.ab.hicarecommercialapp.view.login;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.branch.BranchResponse;
import com.ab.hicarecommercialapp.model.login.VerifyUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/16/2019.
 */
public class BranchPresenter {
    private BranchView view;

    public BranchPresenter(BranchView view) {
        this.view = view;
    }

    public void getBranchList(final String userId, final String companyCode, final String lat, final String lon) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getBranchList(userId, companyCode, lat, lon)
                .enqueue(new Callback<BranchResponse>() {
                    @Override
                    public void onResponse(Call<BranchResponse> call, Response<BranchResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setBranch(response.body());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<BranchResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

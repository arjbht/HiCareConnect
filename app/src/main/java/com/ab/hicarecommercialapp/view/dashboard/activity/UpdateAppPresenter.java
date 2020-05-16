package com.ab.hicarecommercialapp.view.dashboard.activity;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.audit.AuditResponse;
import com.ab.hicarecommercialapp.model.update.UpdateResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.audits.AuditView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 11/20/2019.
 */
public class UpdateAppPresenter {

    private UpdateAppView view;

    public UpdateAppPresenter(UpdateAppView view) {
        this.view = view;
    }

    public void getUpdateVersion() {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getUpdateApp()
                .enqueue(new Callback<UpdateResponse>() {
                    @Override
                    public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setVersionUpdate(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }
}

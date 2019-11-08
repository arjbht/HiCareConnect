package com.ab.hicarecommercialapp.view.dashboard.fragment.services;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.ServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/17/2019.
 */
public class TodayServicePresenter {

    private TodayServiceView view;

    public TodayServicePresenter(TodayServiceView view) {
        this.view = view;
    }

    public void getTodayServices(final MyServiceRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getTodaysServices(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setTodayService(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

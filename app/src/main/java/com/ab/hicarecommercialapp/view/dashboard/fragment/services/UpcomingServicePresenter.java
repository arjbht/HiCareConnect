package com.ab.hicarecommercialapp.view.dashboard.fragment.services;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.ServiceResponse;
import com.ab.hicarecommercialapp.model.service.UpcomingServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 11/1/2019.
 */
public class UpcomingServicePresenter {

    private UpcomingServiceView view;

    public UpcomingServicePresenter(UpcomingServiceView view) {
        this.view = view;
    }

    public void getUpcomingServices(final MyServiceRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getUpcomingServices(request)
                .enqueue(new Callback<UpcomingServiceResponse>() {
                    @Override
                    public void onResponse(Call<UpcomingServiceResponse> call, Response<UpcomingServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setUpcomingService(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<UpcomingServiceResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }


    public void getServiceRequest(final MyServiceRequest request) {
        BaseApplication.getRetrofitAPI(true)
                .getUpcomingServices(request)
                .enqueue(new Callback<UpcomingServiceResponse>() {
                    @Override
                    public void onResponse(Call<UpcomingServiceResponse> call, Response<UpcomingServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setUpcomingService(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<UpcomingServiceResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }


}

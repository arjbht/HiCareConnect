package com.ab.hicarecommercialapp.view.dashboard.fragment.services;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.order.OrderRequest;
import com.ab.hicarecommercialapp.model.order.OrderResponse;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.ServiceResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.orders.OrderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 9/27/2019.
 */
public class ServicePresenter {

    private ServiceView view;

    public ServicePresenter(ServiceView view) {
        this.view = view;
    }

    public void getAllServices(final MyServiceRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getAllServiceRequest(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setService(response.body().getData());
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

    public void getServicesByOrderId(final MyServiceRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getAllServiceRequest(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setService(response.body().getData());
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

    public void getInvoiceServices(final MyServiceRequest request) {
        BaseApplication.getRetrofitAPI(true)
                .getInvoiceServices(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setService(response.body().getData());
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


    public void getServiceHistory(final String qccountNo, final String sDate, final String eDate, final Integer offset, final Integer pageSize, final Boolean isChld) {
        BaseApplication.getRetrofitAPI(false)
                .getServiceHistory(qccountNo, sDate, eDate, offset, pageSize, isChld)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setService(response.body().getData());
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

package com.ab.hicarecommercialapp.view.dashboard.fragment.orders;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.order.OrderRequest;
import com.ab.hicarecommercialapp.model.order.OrderResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 9/27/2019.
 */
public class OrderPresenter {
    private OrderView view;

    public OrderPresenter(OrderView view) {
        this.view = view;
    }

    public void getAllActiveOrders(final OrderRequest request) {
        BaseApplication.getRetrofitAPI(true)
                .getAllActiveOrders(request)
                .enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setOrders(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }

    public void getOrders(final OrderRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getOrders(request)
                .enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setOrders(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }

}

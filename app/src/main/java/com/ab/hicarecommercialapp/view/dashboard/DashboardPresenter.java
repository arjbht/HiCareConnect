package com.ab.hicarecommercialapp.view.dashboard;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.graph.DashboardResponse;
import com.ab.hicarecommercialapp.model.graph.GraphRequest;
import com.ab.hicarecommercialapp.model.graph.GraphResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.GraphView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 11/18/2019.
 */
public class DashboardPresenter {
    private DashboardView view;

    public DashboardPresenter(DashboardView view) {
        this.view = view;
    }

    public void getDashboardData(GraphRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getDashboardSummary(request)
                .enqueue(new Callback<DashboardResponse>() {
                    @Override
                    public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                        view.hideLoading();

                        if (response.isSuccessful() && response.body() != null) {
                            view.setDashboardData(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<DashboardResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

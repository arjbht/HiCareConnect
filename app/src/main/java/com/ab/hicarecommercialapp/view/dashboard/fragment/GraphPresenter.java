package com.ab.hicarecommercialapp.view.dashboard.fragment;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.graph.GraphRequest;
import com.ab.hicarecommercialapp.model.graph.GraphResponse;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.view.login.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/4/2019.
 */
public class GraphPresenter {
    private GraphView view;

    public GraphPresenter(GraphView view) {
        this.view = view;
    }

    void getGraphData(GraphRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getGraphData(request)
                .enqueue(new Callback<GraphResponse>() {
                    @Override
                    public void onResponse(Call<GraphResponse> call, Response<GraphResponse> response) {
                        view.hideLoading();

                        if (response.isSuccessful() && response.body() != null) {
                            view.setGraph(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<GraphResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

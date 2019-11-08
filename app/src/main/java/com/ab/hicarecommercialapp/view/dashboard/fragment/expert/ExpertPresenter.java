package com.ab.hicarecommercialapp.view.dashboard.fragment.expert;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.expert.ExpertRequest;
import com.ab.hicarecommercialapp.model.expert.ExpertResponse;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.invoice.InvoiceResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoiceView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 11/4/2019.
 */
public class ExpertPresenter {

    private ExpertView view;

    public ExpertPresenter(ExpertView view) {
        this.view = view;
    }

    public void getAnExpert(final ExpertRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(false)
                .getAnExpert(request)
                .enqueue(new Callback<ExpertResponse>() {
                    @Override
                    public void onResponse(Call<ExpertResponse> call, Response<ExpertResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setExpert(response.body());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ExpertResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }

}

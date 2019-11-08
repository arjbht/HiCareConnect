package com.ab.hicarecommercialapp.view.dashboard.fragment.jobcards;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.invoice.InvoiceResponse;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.ServiceResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoiceView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/3/2019.
 */
public class JobCardPresenter {

    private JobCardView view;

    public JobCardPresenter(JobCardView view) {
        this.view = view;
    }

    public void getAccountJobCards(final MyServiceRequest request) {
        BaseApplication.getRetrofitAPI(true)
                .getAccountJobCards(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setJobCards(response.body().getData());
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


    public void getInvoiceJobCards(final MyServiceRequest request) {
        BaseApplication.getRetrofitAPI(true)
                .getInvoiceJobCards(request)
                .enqueue(new Callback<ServiceResponse>() {
                    @Override
                    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setJobCards(response.body().getData());
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

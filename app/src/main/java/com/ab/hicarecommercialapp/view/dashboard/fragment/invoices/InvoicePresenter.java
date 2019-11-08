package com.ab.hicarecommercialapp.view.dashboard.fragment.invoices;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.invoice.InvoiceResponse;
import com.ab.hicarecommercialapp.model.order.OrderRequest;
import com.ab.hicarecommercialapp.model.order.OrderResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.orders.OrderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 9/29/2019.
 */
public class InvoicePresenter {

    private InvoiceView view;

    public InvoicePresenter(InvoiceView view) {
        this.view = view;
    }

    public void getInvoices(final InvoiceRequest request) {
        BaseApplication.getRetrofitAPI(true)
                .getInvoices(request)
                .enqueue(new Callback<InvoiceResponse>() {
                    @Override
                    public void onResponse(Call<InvoiceResponse> call, Response<InvoiceResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setInvoices(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<InvoiceResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }

}

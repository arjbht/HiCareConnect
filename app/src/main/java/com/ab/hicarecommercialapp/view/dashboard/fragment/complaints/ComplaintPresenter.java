package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.complaint.ComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.ComplaintResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintTypeResponse;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.invoice.InvoiceResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoiceView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 9/30/2019.
 */
public class ComplaintPresenter {
    private ComplaintView view;

    public ComplaintPresenter(ComplaintView view) {
        this.view = view;
    }

    public void getComplaints(final ComplaintRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getComplaints(request)
                .enqueue(new Callback<ComplaintResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintResponse> call, Response<ComplaintResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setComplaints(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }






}

package com.ab.hicarecommercialapp.view.dashboard.fragment.audits;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.audit.AuditResponse;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.invoice.InvoiceResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoiceView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/30/2019.
 */
public class AuditPresenter {
    private AuditView view;

    public AuditPresenter(AuditView view) {
        this.view = view;
    }

    public void getCustomerAudit(final String accountId, final String startDate, final String endDate) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getCustomerAudits(accountId, startDate, endDate)
                .enqueue(new Callback<AuditResponse>() {
                    @Override
                    public void onResponse(Call<AuditResponse> call, Response<AuditResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setAudits(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<AuditResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }

}

package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.complaint.ComplaintDetailResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintInteractionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 11/3/2019.
 */
public class ComplaintInteractionPresenter {
    private ComplaintInteractionView view;

    public ComplaintInteractionPresenter(ComplaintInteractionView view) {
        this.view = view;
    }

    public void getComplaintInteractions(final int complaintId) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getComplaintInteractions(complaintId)
                .enqueue(new Callback<ComplaintInteractionResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintInteractionResponse> call, Response<ComplaintInteractionResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setInteractions(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintInteractionResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }
}

package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.complaint.ComplaintDetailResponse;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 11/2/2019.
 */
public class ComplaintDetailPresenter {
    private ComplaintDetailView view;

    public ComplaintDetailPresenter(ComplaintDetailView view) {
        this.view = view;
    }

    public void getComplaintById(final int complaintId) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getComplaintsByCaseNumber(complaintId)
                .enqueue(new Callback<ComplaintDetailResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintDetailResponse> call, Response<ComplaintDetailResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setComplaintDetail(response.body());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintDetailResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }
}

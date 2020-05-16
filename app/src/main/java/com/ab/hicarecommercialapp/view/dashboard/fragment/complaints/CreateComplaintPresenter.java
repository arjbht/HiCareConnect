package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.complaint.ComplaintTypeResponse;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/21/2019.
 */
public class CreateComplaintPresenter {
    private CreateComplaintView view;

    public CreateComplaintPresenter(CreateComplaintView view) {
        this.view = view;
    }

    public void getCreateComplaint(final CreateComplaintRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getCreateComplaints(request)
                .enqueue(new Callback<CreateComplaintResponse>() {
                    @Override
                    public void onResponse(Call<CreateComplaintResponse> call, Response<CreateComplaintResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setCreateComplaint(response.body());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateComplaintResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }

    public void getCreateSOSComplaint(final CreateComplaintRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getCreateSOSComplaints(request)
                .enqueue(new Callback<CreateComplaintResponse>() {
                    @Override
                    public void onResponse(Call<CreateComplaintResponse> call, Response<CreateComplaintResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setCreateComplaint(response.body());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateComplaintResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }

}

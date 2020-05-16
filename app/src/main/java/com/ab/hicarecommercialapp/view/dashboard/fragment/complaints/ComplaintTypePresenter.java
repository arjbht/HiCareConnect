package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.complaint.ComplaintTypeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 10/20/2019.
 */
public class ComplaintTypePresenter {

    private ComplaintTypeView view;

    public ComplaintTypePresenter(ComplaintTypeView view) {
        this.view = view;
    }

    public void getComplaintType() {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getComplaintTypes()
                .enqueue(new Callback<ComplaintTypeResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintTypeResponse> call, Response<ComplaintTypeResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setComplaintType(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintTypeResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }


}

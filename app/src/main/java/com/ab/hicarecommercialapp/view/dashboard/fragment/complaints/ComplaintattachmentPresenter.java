package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.complaint.ComplaintAttachmentRequest;
import com.ab.hicarecommercialapp.model.complaint.ComplaintAttachmentResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 11/5/2019.
 */
public class ComplaintattachmentPresenter {
    private ComplaintAttachmentView view;

    public ComplaintattachmentPresenter(ComplaintAttachmentView view) {
        this.view = view;
    }

    public void getAttachment(final ComplaintAttachmentRequest request) {
        view.showLoading();
        BaseApplication.getRetrofitAPI(true)
                .getUploadComplaintAttachment(request)
                .enqueue(new Callback<ComplaintAttachmentResponse>() {
                    @Override
                    public void onResponse(Call<ComplaintAttachmentResponse> call, Response<ComplaintAttachmentResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setAttachment(response.body());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintAttachmentResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }

                });
    }
}

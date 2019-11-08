package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.model.complaint.CreateComplaintResponse;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/21/2019.
 */
public interface CreateComplaintView {
    void showLoading();

    void hideLoading();

    void setCreateComplaint(CreateComplaintResponse response);

    void onErrorLoading(String message);
}

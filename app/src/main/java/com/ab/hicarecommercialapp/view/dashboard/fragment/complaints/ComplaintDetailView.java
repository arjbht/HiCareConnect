package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.model.complaint.ComplaintDetailResponse;
import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintResponse;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Arjun Bhatt on 11/2/2019.
 */
public interface ComplaintDetailView {

    void showLoading();
    void hideLoading();
    void setComplaintDetail(ComplaintDetailResponse complaints);
    void onErrorLoading(String message);
}

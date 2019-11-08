package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.model.complaint.Complaints;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/20/2019.
 */
public interface ComplaintTypeView {
    void showLoading();
    void hideLoading();
    void setComplaintType(List<ComplaintType> types);
    void onErrorLoading(String message);
}

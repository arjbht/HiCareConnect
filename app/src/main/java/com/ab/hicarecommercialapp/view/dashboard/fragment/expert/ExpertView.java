package com.ab.hicarecommercialapp.view.dashboard.fragment.expert;

import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.expert.ExpertResponse;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/4/2019.
 */
public interface ExpertView {
    void showLoading();
    void hideLoading();
    void setExpert(ExpertResponse response);
    void onErrorLoading(String message);
}

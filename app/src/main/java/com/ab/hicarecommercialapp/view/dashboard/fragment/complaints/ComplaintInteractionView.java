package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.complaint.InteractionLogs;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/3/2019.
 */
public interface ComplaintInteractionView {
    void showLoading();
    void hideLoading();
    void setInteractions(List<InteractionLogs> complaints);
    void onErrorLoading(String message);
}

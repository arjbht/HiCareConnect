package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.invoice.Invoices;

import java.util.List;

/**
 * Created by Arjun Bhatt on 9/30/2019.
 */
public interface ComplaintView {
    void showLoading();
    void hideLoading();
    void setComplaints(List<Complaints> complaints);
    void onErrorLoading(String message);
}

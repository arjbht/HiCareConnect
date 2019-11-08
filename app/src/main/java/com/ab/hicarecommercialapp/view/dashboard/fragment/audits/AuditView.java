package com.ab.hicarecommercialapp.view.dashboard.fragment.audits;

import com.ab.hicarecommercialapp.model.audit.Audit;
import com.ab.hicarecommercialapp.model.complaint.Complaints;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/30/2019.
 */
public interface AuditView {
    void showLoading();
    void hideLoading();
    void setAudits(List<Audit> audits);
    void onErrorLoading(String message);
}

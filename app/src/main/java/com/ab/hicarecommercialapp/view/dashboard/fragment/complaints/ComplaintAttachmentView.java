package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;

import com.ab.hicarecommercialapp.model.company.CompanyResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintAttachmentResponse;

/**
 * Created by Arjun Bhatt on 11/5/2019.
 */
public interface ComplaintAttachmentView {
    void showLoading();
    void hideLoading();
    void setAttachment(ComplaintAttachmentResponse response);
    void onErrorLoading(String message);
}

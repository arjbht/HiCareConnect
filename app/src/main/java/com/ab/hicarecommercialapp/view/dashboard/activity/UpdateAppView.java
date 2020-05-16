package com.ab.hicarecommercialapp.view.dashboard.activity;

import com.ab.hicarecommercialapp.model.audit.Audit;
import com.ab.hicarecommercialapp.model.update.UpdateData;
import com.ab.hicarecommercialapp.model.update.UpdateResponse;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/20/2019.
 */
public interface UpdateAppView {
    void showLoading();
    void hideLoading();
    void setVersionUpdate(UpdateData response);
    void onErrorLoading(String message);
}

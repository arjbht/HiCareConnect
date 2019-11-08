package com.ab.hicarecommercialapp.view.dashboard.fragment.services;

import com.ab.hicarecommercialapp.model.service.ServiceTasks;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/5/2019.
 */
public interface ServiceHistoryView {
    void showLoading();

    void hideLoading();

    void setService(List<ServiceTasks> services);

    void onErrorLoading(String message);
}

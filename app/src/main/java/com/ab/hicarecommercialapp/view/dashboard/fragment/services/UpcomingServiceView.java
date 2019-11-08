package com.ab.hicarecommercialapp.view.dashboard.fragment.services;

import com.ab.hicarecommercialapp.model.service.ServiceTasks;
import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/1/2019.
 */
public interface UpcomingServiceView {
    void showLoading();

    void hideLoading();

    void setUpcomingService(List<ServiceTasks> upcomingServices);

    void onErrorLoading(String message);
}

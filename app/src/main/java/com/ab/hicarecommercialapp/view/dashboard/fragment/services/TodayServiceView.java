package com.ab.hicarecommercialapp.view.dashboard.fragment.services;

import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/17/2019.
 */
public interface TodayServiceView {
    void showLoading();

    void hideLoading();

    void setTodayService(List<Service_Details> services);

    void onErrorLoading(String message);
}

package com.ab.hicarecommercialapp.view.dashboard.fragment.services;

import com.ab.hicarecommercialapp.model.order.Orders;
import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.List;

/**
 * Created by Arjun Bhatt on 9/27/2019.
 */
public interface ServiceView {
    void showLoading();

    void hideLoading();

    void setService(List<Service_Details> services);

    void onErrorLoading(String message);
}

package com.ab.hicarecommercialapp.view.dashboard.fragment.jobcards;

import com.ab.hicarecommercialapp.model.order.Orders;
import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/3/2019.
 */
public interface JobCardView {

    void showLoading();

    void hideLoading();

    void setJobCards(List<Service_Details> jobCards);

    void onErrorLoading(String message);
}

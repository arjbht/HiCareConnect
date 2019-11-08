package com.ab.hicarecommercialapp.view.dashboard.fragment.orders;

import com.ab.hicarecommercialapp.model.order.Orders;

import java.util.List;

/**
 * Created by Arjun Bhatt on 9/27/2019.
 */
public interface OrderView {
    void showLoading();
    void hideLoading();
    void setOrders(List<Orders> orders);
    void onErrorLoading(String message);
}

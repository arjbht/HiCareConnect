package com.ab.hicarecommercialapp.view.dashboard.fragment.notifications;

import com.ab.hicarecommercialapp.model.invoice.Invoices;
import com.ab.hicarecommercialapp.model.notification.Notifications;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/20/2019.
 */
public interface NotificationView {
    void showLoading();
    void hideLoading();
    void setNotifications(List<Notifications> notifications);
    void onErrorLoading(String message);
}

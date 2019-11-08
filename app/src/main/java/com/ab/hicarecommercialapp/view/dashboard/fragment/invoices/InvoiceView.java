package com.ab.hicarecommercialapp.view.dashboard.fragment.invoices;

import com.ab.hicarecommercialapp.model.invoice.Invoices;
import com.ab.hicarecommercialapp.model.order.Orders;

import java.util.List;

/**
 * Created by Arjun Bhatt on 9/29/2019.
 */
public interface InvoiceView {
    void showLoading();
    void hideLoading();
    void setInvoices(List<Invoices> invoices);
    void onErrorLoading(String message);
}

package com.ab.hicarecommercialapp.view.dashboard;

import com.ab.hicarecommercialapp.model.graph.Dashboard;
import com.ab.hicarecommercialapp.model.graph.GraphData;
import com.ab.hicarecommercialapp.model.graph.GraphResponse;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/18/2019.
 */
public interface DashboardView {
    void showLoading();

    void hideLoading();

    void setDashboardData(Dashboard data);

    void onErrorLoading(String message);
}

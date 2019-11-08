package com.ab.hicarecommercialapp.view.dashboard.fragment;

import com.ab.hicarecommercialapp.model.graph.GraphData;
import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/4/2019.
 */
public interface GraphView {
    void showLoading();

    void hideLoading();

    void setGraph(List<GraphData> data);

    void onErrorLoading(String message);
}

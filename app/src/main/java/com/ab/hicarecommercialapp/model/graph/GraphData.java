package com.ab.hicarecommercialapp.model.graph;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 10/4/2019.
 */
public class GraphData {

    @SerializedName("xAxis")
    @Expose
    private String xAxis;
    @SerializedName("dataset1")
    @Expose
    private String dataset1;
    @SerializedName("dataset2")
    @Expose
    private String dataset2;

    public String getXAxis() {
        return xAxis;
    }

    public void setXAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getDataset1() {
        return dataset1;
    }

    public void setDataset1(String dataset1) {
        this.dataset1 = dataset1;
    }

    public String getDataset2() {
        return dataset2;
    }

    public void setDataset2(String dataset2) {
        this.dataset2 = dataset2;
    }

}

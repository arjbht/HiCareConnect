package com.ab.hicarecommercialapp.model.graph;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Bhatt on 11/18/2019.
 */
public class Dashboard {
    @SerializedName("TotalSRPlanned")
    @Expose
    private String TotalSRPlanned;
    @SerializedName("TotalSRDelivered")
    @Expose
    private String TotalSRDelivered;
    @SerializedName("TotalComplaint")
    @Expose
    private String TotalComplaint;
    @SerializedName("TotalResolvedComplaint")
    @Expose
    private String TotalResolvedComplaint;

    public String getTotalSRPlanned() {
        return TotalSRPlanned;
    }

    public void setTotalSRPlanned(String totalSRPlanned) {
        TotalSRPlanned = totalSRPlanned;
    }

    public String getTotalSRDelivered() {
        return TotalSRDelivered;
    }

    public void setTotalSRDelivered(String totalSRDelivered) {
        TotalSRDelivered = totalSRDelivered;
    }

    public String getTotalComplaint() {
        return TotalComplaint;
    }

    public void setTotalComplaint(String totalComplaint) {
        TotalComplaint = totalComplaint;
    }

    public String getTotalResolvedComplaint() {
        return TotalResolvedComplaint;
    }

    public void setTotalResolvedComplaint(String totalResolvedComplaint) {
        TotalResolvedComplaint = totalResolvedComplaint;
    }
}

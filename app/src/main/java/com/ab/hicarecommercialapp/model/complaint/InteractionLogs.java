package com.ab.hicarecommercialapp.model.complaint;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 9/30/2019.
 */
public class InteractionLogs {

    @SerializedName("body_text")
    @Expose
    private String body_text;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("created_at_text")
    @Expose
    private String created_at_text;

    public String getBody_text() {
        return body_text;
    }

    public void setBody_text(String body_text) {
        this.body_text = body_text;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at_text() {
        return created_at_text;
    }

    public void setCreated_at_text(String created_at_text) {
        this.created_at_text = created_at_text;
    }
}

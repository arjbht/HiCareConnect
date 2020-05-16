package com.ab.hicarecommercialapp.model.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 11/20/2019.
 */
public class Notifications {
    @SerializedName("Id")
    @Expose
    private String Id;
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Notification_DateTime")
    @Expose
    private String Notification_DateTime;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNotification_DateTime() {
        return Notification_DateTime;
    }

    public void setNotification_DateTime(String notification_DateTime) {
        Notification_DateTime = notification_DateTime;
    }
}

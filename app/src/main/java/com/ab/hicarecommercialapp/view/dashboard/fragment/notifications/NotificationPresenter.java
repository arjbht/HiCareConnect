package com.ab.hicarecommercialapp.view.dashboard.fragment.notifications;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.model.notification.NotificationResponse;
import com.ab.hicarecommercialapp.model.service.ServiceResponse;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServiceView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 11/20/2019.
 */
public class NotificationPresenter {

    private NotificationView view;

    public NotificationPresenter(NotificationView view) {
        this.view = view;
    }

    public void getNotifications(final String resourceid, final String accountId) {
        BaseApplication.getRetrofitAPI(true)
                .getNotifications(resourceid, accountId)
                .enqueue(new Callback<NotificationResponse>() {
                    @Override
                    public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                        view.hideLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.setNotifications(response.body().getData());
                        } else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationResponse> call, Throwable t) {
                        view.hideLoading();
                        view.onErrorLoading(t.getLocalizedMessage());
                    }
                });
    }
}

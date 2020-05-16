package com.ab.hicarecommercialapp.view.dashboard.fragment.notifications;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewNotificationAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.notification.Notifications;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoicePresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServicePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment implements NotificationView {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    @BindView(R.id.emptyBox)
    LinearLayout emptyBox;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewNotificationAdapter mAdapter;
    RealmResults<Branch> mBranchRealmResults;

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        Bundle args = new Bundle();
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Notifications");
        showBackButton(false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewNotificationAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        getNotifications();
    }

    private void getNotifications() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            String resourceId = mloginRealmModel.get(0).getUserId();
            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    NotificationPresenter presenter = new NotificationPresenter(this);
                    presenter.getNotifications(resourceId, accountNo);
                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        NotificationPresenter presenter = new NotificationPresenter(this);
                        presenter.getNotifications(resourceId, accountNo);
                    }
                }
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setNotifications(List<Notifications> notifications) {
//        progressBar.setVisibility(View.GONE);
        if (notifications != null) {
            if (notifications.size() > 0) {
                mAdapter.setData(notifications);
                mAdapter.notifyDataSetChanged();
                emptyBox.setVisibility(View.GONE);
            } else {
                emptyBox.setVisibility(View.VISIBLE);
            }
        } else {
            emptyBox.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onErrorLoading(String message) {

    }
}

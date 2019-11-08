package com.ab.hicarecommercialapp.view.dashboard.fragment.services;


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
import com.ab.hicarecommercialapp.adapter.RecyclerViewServiceAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewUpcomingAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.ServiceTasks;
import com.ab.hicarecommercialapp.model.service.Service_Details;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingServicesFragment extends BaseFragment implements UpcomingServiceView {


    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.shimmerService)
    LinearLayout shimmerService;
    private RecyclerViewUpcomingAdapter adapter;
    RealmResults<Branch> mBranchRealmResults;


    public UpcomingServicesFragment() {
        // Required empty public constructor
    }

    public static UpcomingServicesFragment newInstance() {
        Bundle args = new Bundle();
        UpcomingServicesFragment fragment = new UpcomingServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_services, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Upcoming Services");
        showBackButton(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.navigationBorder).setVisibility(View.GONE);

        adapter =
                new RecyclerViewUpcomingAdapter(getActivity(), false);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setClipToPadding(false);
        recycleView.setAdapter(adapter);

        getAllUpcomingServices();
    }

    private void getAllUpcomingServices() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                String mobile = mloginRealmModel.get(0).getMobile();
                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    MyServiceRequest request = new MyServiceRequest();
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    request.setAccountNo(accountNo);
                    request.setPageSize(5);
                    request.setIsAdmin(isAdmin);
                    request.setMobileNo(mobile);
                    request.setPageOffset(5);
                    UpcomingServicePresenter presenter = new UpcomingServicePresenter(this);
                    presenter.getUpcomingServices(request);
                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        MyServiceRequest request = new MyServiceRequest();
                        request.setAccountNo(accountNo);
                        request.setPageSize(5);
                        request.setIsAdmin(isAdmin);
                        request.setMobileNo(mobile);
                        request.setPageOffset(5);
                        UpcomingServicePresenter presenter = new UpcomingServicePresenter(this);
                        presenter.getUpcomingServices(request);
                    }
                }

            }
        }
    }

    @Override
    public void showLoading() {
        shimmerService.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        shimmerService.setVisibility(View.GONE);
    }

    @Override
    public void setUpcomingService(List<ServiceTasks> upcomingServices) {
        if (upcomingServices != null) {
            if (upcomingServices.size() > 0) {
                adapter.setData(upcomingServices);
                adapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error", message);
    }
}

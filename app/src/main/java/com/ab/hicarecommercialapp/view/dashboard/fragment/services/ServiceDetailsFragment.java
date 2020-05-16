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
import android.widget.ProgressBar;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewOrderServicesAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
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
public class ServiceDetailsFragment extends BaseFragment implements ServiceView {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.shimmerService)
    LinearLayout shimmerService;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private static final String ARG_ORDER = "ARG_ORDER";
    private Integer pageNumber = 0;
    RecyclerViewOrderServicesAdapter adapter;
    RealmResults<Branch> mBranchRealmResults;
    private String orderNo = "";

    public ServiceDetailsFragment() {
        // Required empty public constructor
    }

    public static ServiceDetailsFragment newInstance(String orderNumber) {
        Bundle args = new Bundle();
        args.putString(ARG_ORDER, orderNumber);
        ServiceDetailsFragment fragment = new ServiceDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderNo = getArguments().getString(ARG_ORDER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_details, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Service Details");
        showBackButton(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.bottom_navigation2).setVisibility(View.GONE);
//        getActivity().findViewById(R.id.navigationBorder).setVisibility(View.GONE);
        adapter = new RecyclerViewOrderServicesAdapter(getActivity(), false);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setClipToPadding(false);
        recycleView.setAdapter(adapter);

        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && isLastItemDisplaying(recycleView)) {
                    pageNumber += 10;
                    progressBar.setVisibility(View.VISIBLE);
                    getServiceDetails();
                }
            }
        });

        getServiceDetails();
    }

    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION
                    && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    private void getServiceDetails() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();

            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                String userId = mloginRealmModel.get(0).getMobile();
                String mobile = mloginRealmModel.get(0).getMobile();
                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    MyServiceRequest request = new MyServiceRequest();
                    request.setAccountNo(accountNo);
                    request.setPageSize(10);
                    request.setIsAdmin(isAdmin);
                    request.setUserId(userId);
                    request.setMobileNo(mobile);
                    request.setOrderNo(orderNo);
                    request.setPageOffset(pageNumber);
                    ServicePresenter presenter = new ServicePresenter(this);
                    presenter.getServicesByOrderId(request);
                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        MyServiceRequest request = new MyServiceRequest();
                        request.setAccountNo(accountNo);
                        request.setPageSize(10);
                        request.setIsAdmin(isAdmin);
                        request.setUserId(userId);
                        request.setMobileNo(mobile);
                        request.setOrderNo(orderNo);
                        request.setPageOffset(pageNumber);
                        ServicePresenter presenter = new ServicePresenter(this);
                        presenter.getServicesByOrderId(request);
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
    public void setService(List<Service_Details> services) {
        progressBar.setVisibility(View.GONE);
        if (services != null) {
            if (pageNumber == 0 && services.size() > 0) {
                adapter.setData(services);
                adapter.notifyDataSetChanged();
            } else if (services.size() > 0) {
                adapter.addData(services);
                adapter.notifyDataSetChanged();
            } else {
                pageNumber -= 10;
            }
        }
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error ", message);
    }
}

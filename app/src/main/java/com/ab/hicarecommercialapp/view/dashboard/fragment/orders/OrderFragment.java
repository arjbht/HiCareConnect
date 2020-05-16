package com.ab.hicarecommercialapp.view.dashboard.fragment.orders;


import android.graphics.Color;
import android.graphics.PorterDuff;
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
import com.ab.hicarecommercialapp.adapter.RecyclerViewOrderAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.order.OrderRequest;
import com.ab.hicarecommercialapp.model.order.Orders;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.complaints.ComplaintDetailsFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.jobcards.JobCardPresenter;
import com.ab.hicarecommercialapp.view.login.LoginPresenter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment implements OrderView {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.shimmerOrders)
    LinearLayout shimmerOrders;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Integer pageNumber = 0;

    private RecyclerViewOrderAdapter adapter;
    RealmResults<Branch> mBranchRealmResults;

    private List<Orders> oList = new ArrayList<>();

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Orders");
        showBackButton(false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.bottom_navigation2).setVisibility(View.VISIBLE);
//        getActivity().findViewById(R.id.navigationBorder).setVisibility(View.VISIBLE);
        adapter =
                new RecyclerViewOrderAdapter(getActivity());
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
                    pageNumber += 10 ;
                    progressBar.setVisibility(View.VISIBLE);
                    getOrders();
                }
            }
        });
        getOrders();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pageNumber = 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pageNumber = 0;
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

    private void getOrders() {
        progressBar.setVisibility(View.GONE);
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
//            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
//                String accountNo = mloginRealmModel.get(0).getAccounts();
//                Boolean isAdmin = Boolean.valueOf(mloginRealmModel.get(0).getIsAdmin());
//                OrderRequest request = new OrderRequest();
//                request.setAccountNo(accountNo);
//                request.setIsAdmin(isAdmin);
//                request.setPageOffset(pageNumber);
//                request.setPageSize(10);
//                OrderPresenter presenter = new OrderPresenter(this);
//                presenter.getOrders(request);
//            }

            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                String userId = mloginRealmModel.get(0).getMobile();
                String mobile = mloginRealmModel.get(0).getMobile();
                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    OrderRequest request = new OrderRequest();
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    request.setAccountNo(accountNo);
                    request.setPageSize(10);
                    request.setIsAdmin(isAdmin);
                    request.setMobileNo(mobile);
                    request.setUserId(userId);
                    request.setPageOffset(pageNumber);
                    OrderPresenter presenter = new OrderPresenter(this);
                    presenter.getOrders(request);

                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        OrderRequest request = new OrderRequest();
                        request.setAccountNo(accountNo);
                        request.setPageSize(10);
                        request.setIsAdmin(isAdmin);
                        request.setUserId(userId);
                        request.setMobileNo(mobile);
                        request.setPageOffset(pageNumber);
                        OrderPresenter presenter = new OrderPresenter(this);
                        presenter.getOrders(request);

                    }
                }

            }
        }

    }

    @Override
    public void showLoading() {
        shimmerOrders.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        shimmerOrders.setVisibility(View.GONE);
    }

    @Override
    public void setOrders(List<Orders> orders) {
        progressBar.setVisibility(View.GONE);
        if (orders != null) {
            if (pageNumber == 0 && orders.size() > 0) {
                adapter.setData(orders);
                adapter.notifyDataSetChanged();
            } else if (orders.size() > 0) {
                adapter.addData(orders);
                adapter.notifyDataSetChanged();
            } else {
                pageNumber -= 10;
            }
            adapter.setOnItemClickListener((view, position) -> replaceFragment(OrderDetailsFragment.newInstance(adapter.getItem(position)), "OrderFragment - OrderDetailsFragment"));
        }
    }
    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error ", message);
    }
}

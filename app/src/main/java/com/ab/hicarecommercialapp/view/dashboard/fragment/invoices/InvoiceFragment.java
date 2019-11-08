package com.ab.hicarecommercialapp.view.dashboard.fragment.invoices;


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
import com.ab.hicarecommercialapp.adapter.RecyclerViewInvoiceAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewOrderAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.complaint.ComplaintRequest;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.invoice.Invoices;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.order.OrderRequest;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.complaints.ComplaintPresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.orders.OrderDetailsFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.orders.OrderPresenter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceFragment extends BaseFragment implements InvoiceView {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.shimmerInvoices)
    LinearLayout shimmerInvoices;
    private Integer pageNumber = 0;
    private RecyclerViewInvoiceAdapter adapter;
    RealmResults<Branch> mBranchRealmResults;
    public InvoiceFragment() {
        // Required empty public constructor
    }

    public static InvoiceFragment newInstance() {
        Bundle args = new Bundle();
        InvoiceFragment fragment = new InvoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Invoices");
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
                new RecyclerViewInvoiceAdapter(getActivity());
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
                    getInvoices();
                }
            }
        });
        getInvoices();
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

    private void getInvoices() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
//            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
//                String accountNo = mloginRealmModel.get(0).getAccounts();
//                String userId = mloginRealmModel.get(0).getMobile();
//                String mobile = mloginRealmModel.get(0).getMobile();
//                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
//                InvoiceRequest request = new InvoiceRequest();
//                request.setAccountNo(accountNo);
//                request.setUserId(userId);
//                request.setMobileNo(mobile);
//                request.setIsAdmin(isAdmin);
//                request.setPageOffset(pageNumber);
//                request.setPageSize(10);
//                InvoicePresenter presenter = new InvoicePresenter(this);
//                presenter.getInvoices(request);
//            }

            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                String userId = mloginRealmModel.get(0).getMobile();
                String mobile = mloginRealmModel.get(0).getMobile();
                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    InvoiceRequest request = new InvoiceRequest();
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    request.setAccountNo(accountNo);
                    request.setPageSize(5);
                    request.setIsAdmin(isAdmin);
                    request.setMobileNo(mobile);
                    request.setUserId(userId);
                    request.setPageOffset(pageNumber);
                    InvoicePresenter presenter = new InvoicePresenter(this);
                    presenter.getInvoices(request);

                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        InvoiceRequest request = new InvoiceRequest();
                        request.setAccountNo(accountNo);
                        request.setPageSize(5);
                        request.setIsAdmin(isAdmin);
                        request.setUserId(userId);
                        request.setMobileNo(mobile);
                        request.setPageOffset(pageNumber);
                        InvoicePresenter presenter = new InvoicePresenter(this);
                        presenter.getInvoices(request);

                    }
                }

            }
        }
    }

    @Override
    public void showLoading() {
        shimmerInvoices.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        shimmerInvoices.setVisibility(View.GONE);
    }

    @Override
    public void setInvoices(List<Invoices> invoices) {
        progressBar.setVisibility(View.GONE);
        if (invoices != null) {
            if (pageNumber == 0 && invoices.size() > 0) {
                adapter.setData(invoices);
                adapter.notifyDataSetChanged();
            } else if (invoices.size() > 0) {
                adapter.addData(invoices);
                adapter.notifyDataSetChanged();
            } else {
                pageNumber -= 10;
            }
            adapter.setOnItemClickListener((view, position) -> replaceFragment(InvoiceDetailsFragment.newInstance(invoices.get(position)), "ComplaintHistoryFragment - ViewComplaintFragment"));
        }
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error ", message);
    }
}

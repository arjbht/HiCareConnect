package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.ab.hicarecommercialapp.adapter.RecyclerViewComplaintAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewInvoiceAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.complaint.ComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoicePresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServicePresenter;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintFragment extends BaseFragment implements ComplaintView {
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.fab)
    FloatingActionButton fabAdd;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.emptyTask)
    LinearLayout emptyTask;

    @BindView(R.id.shimmerComplaint)
    LinearLayout shimmerComplaint;
    private Integer pageNumber = 0;
    private RecyclerViewComplaintAdapter adapter;
    RealmResults<Branch> mBranchRealmResults;

    public ComplaintFragment() {
        // Required empty public constructor
    }

    public static ComplaintFragment newInstance() {
        Bundle args = new Bundle();
        ComplaintFragment fragment = new ComplaintFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaint, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Complaints");
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
        adapter = new RecyclerViewComplaintAdapter(getActivity());
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setClipToPadding(false);
        recycleView.setAdapter(adapter);
        getComplaints();
        fabAdd.setOnClickListener(view1 -> replaceFragment(CreateComplaintFragment.newInstance(), "ComplaintFragment-CreateComplaintFragment"));
    }


    private void getComplaints() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();

            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                String userId = mloginRealmModel.get(0).getUserId();
                String mobile = mloginRealmModel.get(0).getMobile();
                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    ComplaintRequest request = new ComplaintRequest();
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    request.setAccountNo(accountNo);
                    request.setPageSize(10);
                    request.setIsAdmin(isAdmin);
                    request.setMobileNo(mobile);
                    request.setUserId(userId);
                    request.setPageOffset(pageNumber);
                    ComplaintPresenter presenter = new ComplaintPresenter(this);
                    presenter.getComplaints(request);

                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        ComplaintRequest request = new ComplaintRequest();
                        request.setAccountNo(accountNo);
                        request.setPageSize(10);
                        request.setIsAdmin(isAdmin);
                        request.setUserId(userId);
                        request.setMobileNo(mobile);
                        request.setPageOffset(pageNumber);
                        ComplaintPresenter presenter = new ComplaintPresenter(this);
                        presenter.getComplaints(request);
                    }
                }

            }
        }
    }

    @Override
    public void showLoading() {
        shimmerComplaint.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        shimmerComplaint.setVisibility(View.GONE);
    }

    @Override
    public void setComplaints(List<Complaints> complaints) {

        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener((view, position) -> {
            replaceFragment(ComplaintDetailsFragment.newInstance(complaints.get(position).getId()), "ComplaintHistoryFragment - ViewComplaintFragment");
            getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        });

        progressBar.setVisibility(View.GONE);
        if (complaints != null) {
            if (pageNumber == 0 && complaints.size() > 0) {
                adapter.setData(complaints);
                adapter.notifyDataSetChanged();
                emptyTask.setVisibility(View.GONE);
            } else if (complaints.size() > 0) {
                adapter.addData(complaints);
                adapter.notifyDataSetChanged();
                emptyTask.setVisibility(View.GONE);
            } else {
                pageNumber -= 10;
                emptyTask.setVisibility(View.VISIBLE);
            }
        } else {
            emptyTask.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error ", message);
    }
}

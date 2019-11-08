package com.ab.hicarecommercialapp.view.dashboard.fragment.invoices;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewJobCardAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewServiceAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.invoice.Invoices;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.Service_Details;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.jobcards.JobCardPresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.jobcards.JobCardView;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServicePresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServiceView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceDetailsFragment extends BaseFragment implements ServiceView, JobCardView {

    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;

    @BindView(R.id.txtGross)
    TextView txtGross;

    @BindView(R.id.txtNet)
    TextView txtNet;

    @BindView(R.id.txtTax1)
    TextView txtTax1;

    @BindView(R.id.txtTax2)
    TextView txtTax2;

    @BindView(R.id.txtCreditedAmt)
    TextView txtCreditedAmt;

    @BindView(R.id.txtDebitedAmt)
    TextView txtDebitedAmt;

    @BindView(R.id.txtKnockOff)
    TextView txtKnockOff;

    @BindView(R.id.txtOutstanding)
    TextView txtOutstanding;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private RecyclerViewJobCardAdapter mAdapter;

    private RecyclerViewServiceAdapter mAdapter2;


    public static final String ARGS_INVOICES = "ARGS_INVOICES";
    private Invoices model;

    private Integer pageNumberJ = 0;
    private Integer pageNumberS = 0;
    RealmResults<Branch> mBranchRealmResults;

    public InvoiceDetailsFragment() {
        // Required empty public constructor
    }


    public static InvoiceDetailsFragment newInstance(Invoices model) {
        Bundle args = new Bundle();
        args.putParcelable(ARGS_INVOICES, model);
        InvoiceDetailsFragment fragment = new InvoiceDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            model = getArguments().getParcelable(ARGS_INVOICES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invoice_details, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("INV-" + model.getSFDCInvoiceNumberC());
        showBackButton(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager.setAdapter(new InvoiceViewPager());
        viewpagertab.setViewPager(pager);
        getInvoiceJobCards();
        getInvoiceServices();

        txtGross.setText("\u20B9 " + "958");
        txtNet.setText("\u20B9 " + model.getNetCostC());
        txtOutstanding.setText("\u20B9 " + model.getOutstandingAmountC());
        txtCreditedAmt.setText("\u20B9 " + model.getTotalCreditedAmountC());
        txtDebitedAmt.setText("\u20B9 " + model.getTotalDebitedAmountC());
        txtKnockOff.setText("\u20B9 " + model.getTotalKnockOffPaymentC());
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

    private void getInvoiceJobCards() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
//            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
//                String accountNo = mloginRealmModel.get(0).getAccounts();
//                String userId = mloginRealmModel.get(0).getMobile();
//                String mobile = mloginRealmModel.get(0).getMobile();
//                String caseId = model.getName();
//                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
//                MyServiceRequest request = new MyServiceRequest();
//                request.setAccountNo(accountNo);
//                request.setUserId(userId);
//                request.setMobileNo(mobile);
//                request.setIsAdmin(isAdmin);
//                request.setCaseNo(caseId);
//                request.setPageOffset(pageNumberJ);
//                request.setPageSize(10);
//                JobCardPresenter presenter = new JobCardPresenter(this);
//                presenter.getInvoiceJobCards(request);
//            }

            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                String userId = mloginRealmModel.get(0).getMobile();
                String mobile = mloginRealmModel.get(0).getMobile();
                String caseId = model.getName();
                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    MyServiceRequest request = new MyServiceRequest();
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    request.setAccountNo(accountNo);
                    request.setPageSize(5);
                    request.setIsAdmin(isAdmin);
                    request.setMobileNo(mobile);
                    request.setUserId(userId);
                    request.setCaseNo(caseId);
                    request.setPageOffset(pageNumberJ);
                    JobCardPresenter presenter = new JobCardPresenter(this);
                    presenter.getInvoiceJobCards(request);

                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        MyServiceRequest request = new MyServiceRequest();
                        request.setAccountNo(accountNo);
                        request.setPageSize(5);
                        request.setIsAdmin(isAdmin);
                        request.setUserId(userId);
                        request.setMobileNo(mobile);
                        request.setCaseNo(caseId);
                        request.setPageOffset(pageNumberJ);
                        JobCardPresenter presenter = new JobCardPresenter(this);
                        presenter.getInvoiceJobCards(request);

                    }
                }

            }

        }
    }

    private void getInvoiceServices() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
//            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
//                String accountNo = mloginRealmModel.get(0).getAccounts();
//                String userId = mloginRealmModel.get(0).getMobile();
//                String mobile = mloginRealmModel.get(0).getMobile();
//
//                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
//                MyServiceRequest request = new MyServiceRequest();
//                request.setAccountNo(accountNo);
//                request.setUserId(userId);
//                request.setMobileNo(mobile);
//                request.setIsAdmin(isAdmin);
//                request.setPageOffset(pageNumberS);
//                request.setPageSize(10);
//                request.setCaseNo(caseId);
//                ServicePresenter presenter = new ServicePresenter(this);
//                presenter.getInvoiceServices(request);
//            }

            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                String caseId = model.getName();
                String userId = mloginRealmModel.get(0).getMobile();
                String mobile = mloginRealmModel.get(0).getMobile();
                boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    MyServiceRequest request = new MyServiceRequest();
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    request.setAccountNo(accountNo);
                    request.setPageSize(5);
                    request.setIsAdmin(isAdmin);
                    request.setMobileNo(mobile);
                    request.setUserId(userId);
                    request.setCaseNo(caseId);
                    request.setPageOffset(pageNumberS);
                    ServicePresenter presenter = new ServicePresenter(this);
                    presenter.getInvoiceServices(request);

                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        MyServiceRequest request = new MyServiceRequest();
                        request.setAccountNo(accountNo);
                        request.setPageSize(5);
                        request.setIsAdmin(isAdmin);
                        request.setUserId(userId);
                        request.setMobileNo(mobile);
                        request.setCaseNo(caseId);
                        request.setPageOffset(pageNumberS);
                        ServicePresenter presenter = new ServicePresenter(this);
                        presenter.getInvoiceServices(request);

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
    public void setJobCards(List<Service_Details> jobCards) {
        progressBar.setVisibility(View.GONE);
        if (jobCards != null) {
            if (pageNumberJ == 0 && jobCards.size() > 0) {
                mAdapter.setData(jobCards);
                mAdapter.notifyDataSetChanged();
            } else if (jobCards.size() > 0) {
                mAdapter.addData(jobCards);
                mAdapter.notifyDataSetChanged();
            } else {
                pageNumberJ -= 10;
            }
        }

    }

    @Override
    public void setService(List<Service_Details> services) {
        progressBar.setVisibility(View.GONE);
        if (services != null) {
            if (pageNumberS == 0 && services.size() > 0) {
                mAdapter2.setData(services);
                mAdapter.notifyDataSetChanged();
            } else if (services.size() > 0) {
                mAdapter2.addData(services);
                mAdapter.notifyDataSetChanged();
            } else {
                pageNumberS -= 10;
            }
        }
    }

    @Override
    public void onErrorLoading(String message) {
        progressBar.setVisibility(View.GONE);
    }
    public class InvoiceViewPager extends PagerAdapter {

        private final LayoutInflater mLayoutInflater;

        public InvoiceViewPager() {
            mLayoutInflater =
                    (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = null;

            switch (position) {
                case 0:
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    itemView = (ViewGroup) inflater.inflate(R.layout.pager_invoice_jobcard, container, false);
                    RecyclerView recycleView = itemView.findViewById(R.id.recycleInvoiceJobcard);
                    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recycleView.setLayoutManager(linearLayoutManager);
                    recycleView.setHasFixedSize(true);
                    mAdapter = new RecyclerViewJobCardAdapter(getActivity());
                    recycleView.setAdapter(mAdapter);
                    recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                        }

                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (dy > 0 && isLastItemDisplaying(recycleView)) {
                                pageNumberJ += 10;
                                progressBar.setVisibility(View.VISIBLE);
                                getInvoiceJobCards();
                            }
                        }
                    });
                    break;

                case 1:
                    LayoutInflater inflater2 = LayoutInflater.from(getContext());
                    itemView = (ViewGroup) inflater2.inflate(R.layout.pager_invoice_service, container, false);
                    RecyclerView recyclerView2 = itemView.findViewById(R.id.recycleInvoiceService);
                    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
                    recyclerView2.setLayoutManager(linearLayoutManager2);
                    recyclerView2.setHasFixedSize(true);
                    mAdapter2 =
                            new RecyclerViewServiceAdapter(getActivity());
                    recyclerView2.setAdapter(mAdapter2);
                    recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                        }

                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (dy > 0 && isLastItemDisplaying(recyclerView2)) {
                                pageNumberS += 10;
                                progressBar.setVisibility(View.VISIBLE);
                                getInvoiceServices();
                            }
                        }
                    });
                    break;

            }

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Job Cards";
                case 1:
                    return "Services";

            }
            return "NA";
        }

    }
}

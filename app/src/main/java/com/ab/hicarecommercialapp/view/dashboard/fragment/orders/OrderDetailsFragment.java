package com.ab.hicarecommercialapp.view.dashboard.fragment.orders;


import android.graphics.Color;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewOrderServicesAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewServiceAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.complaint.ComplaintRequest;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.order.OrderRequest;
import com.ab.hicarecommercialapp.model.order.Orders;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.Service_Details;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.utils.TimeUtil;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.complaints.ComplaintPresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServiceDetailsFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServicePresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServiceView;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.UpcomingServicesFragment;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDetailsFragment extends BaseFragment implements ServiceView {

    @BindView(R.id.txt_service)
    TextView txtService;

    @BindView(R.id.txt_status)
    TextView txtStatus;

    @BindView(R.id.txtStartDate)
    TextView txtStartDate;

    @BindView(R.id.txtEndDate)
    TextView txtEndDate;

    @BindView(R.id.txt_Actualservice)
    TextView txtActualservice;

    @BindView(R.id.txt_schedule)
    TextView txtSchedule;

    @BindView(R.id.txt_apartment)
    TextView txtApartment;

    @BindView(R.id.txt_total)
    TextView txtTotal;

    @BindView(R.id.txt_quantity)
    TextView txtQuantity;

    @BindView(R.id.txt_centralrs)
    TextView txtCentralrs;

    @BindView(R.id.txt_staters)
    TextView txtStaters;

    @BindView(R.id.final_amount)
    TextView finalAmount;

    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.txt_address)
    TextView txtAddress;

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    @BindView(R.id.shimmerOrderDetails)
    FrameLayout shimmerOrderDetails;

    @BindView(R.id.lnrDetails)
    LinearLayout lnrDetails;

    @BindView(R.id.lnrSDetails)
    LinearLayout lnrSDetails;

    @BindView(R.id.btnViewMore)
    LinearLayout btnViewMore;

    @BindView(R.id.serviceDetails)
    ConstraintLayout serviceDetails;

    public static final String ARGS_ORDERS = "ARGS_ORDERS";
    Orders model;
    RealmResults<Branch> mBranchRealmResults;
    RecyclerViewOrderServicesAdapter adapter;
    private Integer pageNumber = 0;

    public OrderDetailsFragment() {
        // Required empty public constructor
    }

    public static OrderDetailsFragment newInstance(Orders model) {
        Bundle args = new Bundle();
        args.putParcelable(ARGS_ORDERS, model);
        OrderDetailsFragment fragment = new OrderDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            model = getArguments().getParcelable(ARGS_ORDERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_details, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Order #" + model.getOrderNumber());
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
        adapter = new RecyclerViewOrderServicesAdapter(getActivity(), true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);

        btnViewMore.setOnClickListener(view1 -> replaceFragment(ServiceDetailsFragment.newInstance(model.getOrderNumber()), "OrderDetailFragment-ServiceDetailsFragment"));
        getServicesByOrderId();
//        imgType.setImageResource(AppUtils.getImageByCode(model.getServicePlan().getServiceGroupCodeC()));
        if (model.getCustStatus().equalsIgnoreCase("Active")) {
            txtStatus.setTextColor(Color.parseColor("#0E8C3A"));
        } else {
            txtStatus.setTextColor(Color.parseColor("#4682B4"));
        }
        txtStatus.setText(model.getCustStatus());
        txtService.setText(AppUtils.GetSMSServiceType(model.getServicePlan().getServiceGroupCodeC()));
        txtAddress.setText(model.getAccountDetail().getFlatNumber() + ", "
                + model.getAccountDetail().getBuildingName() + ", "
                + model.getAccountDetail().getLandmark() + ", "
                + model.getAccountDetail().getLocalitySuburb() + ", "
                + model.getAccountDetail().getBillingStreet() + ", "
                + model.getAccountDetail().getBillingPoastalCode());
        finalAmount.setText("\u20B9 " + model.getPayment());
        txtName.setText(model.getAccountDetail().getName() + " | " + model.getAccountDetail().getMobile());
        try {
            String start = TimeUtil.reFormatDate(model.getStartDate(), "MMM dd, yyyy");
            String end = TimeUtil.reFormatDate(model.getEndDate(), "MMM dd, yyyy");
            txtStartDate.setText("Started On : " + start);
            txtEndDate.setText("Ended On : " + end);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtActualservice.setText(model.getServicePlanName());
        if (model.getHouseDetail() != null) {
            txtApartment.setText("Select Apartment Size - " + model.getHouseDetail().getName() + " " + model.getFlatType());
        }
        txtTotal.setText("\u20B9 " + model.getPayment());
        if (model.getQuantity() != null) {
            txtQuantity.setText("Qty: " + model.getQuantity() );
        } else {
            txtQuantity.setText("Qty: " + "0");
        }
    }

    private void getServicesByOrderId() {
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
                    request.setPageSize(5);
                    request.setIsAdmin(isAdmin);
                    request.setUserId(userId);
                    request.setMobileNo(mobile);
                    request.setOrderNo(model.getOrderNumber());
                    request.setPageOffset(1);
                    ServicePresenter presenter = new ServicePresenter(this);
                    presenter.getServicesByOrderId(request);
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
                        request.setOrderNo(model.getOrderNumber());
                        request.setPageOffset(1);
                        ServicePresenter presenter = new ServicePresenter(this);
                        presenter.getServicesByOrderId(request);
                    }
                }

            }
        }
    }

    @Override
    public void showLoading() {
        shimmerOrderDetails.setVisibility(View.VISIBLE);
        lnrDetails.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        shimmerOrderDetails.setVisibility(View.GONE);
        lnrDetails.setVisibility(View.VISIBLE);
    }

    @Override
    public void setService(List<Service_Details> services) {

        if (services != null) {
            if (services.size() > 0) {
                adapter.addData(services);
                serviceDetails.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
                btnViewMore.setVisibility(View.VISIBLE);
            } else {
                btnViewMore.setVisibility(View.GONE);
                serviceDetails.setVisibility(View.GONE);
                pageNumber -= 5;
            }
        } else {
            btnViewMore.setVisibility(View.GONE);
            serviceDetails.setVisibility(View.GONE);
        }

    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error ", message);
    }
}

package com.ab.hicarecommercialapp.view.dashboard.fragment.services;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceHistoryFragment extends BaseFragment implements ServiceView {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.shimmerService)
    LinearLayout shimmerService;

    @BindView(R.id.lnrDays)
    LinearLayout lnrDays;

    @BindView(R.id.lnrOneMonth)
    LinearLayout lnrOneMonth;

    @BindView(R.id.lnrThreeMonth)
    LinearLayout lnrThreeMonth;

    @BindView(R.id.txtDays)
    TextView txtDays;

    @BindView(R.id.txtOneMonth)
    TextView txtOneMonth;

    @BindView(R.id.txtThreeMonth)
    TextView txtThreeMonth;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.emptyBox)
    LinearLayout emptyBox;

    private RecyclerViewServiceAdapter adapter;
    RealmResults<Branch> mBranchRealmResults;

    private Integer pageNumber = 0;
    private String sDate = "";
    private String eDate = "";
    private String type = "default";

    public ServiceHistoryFragment() {
        // Required empty public constructor
    }

    public static ServiceHistoryFragment newInstance() {
        Bundle args = new Bundle();
        ServiceHistoryFragment fragment = new ServiceHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_history, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Service History");
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
        adapter =
                new RecyclerViewServiceAdapter(getActivity());
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
                    setServiceHistory();
                }
            }
        });
        setServiceHistory();
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

    private void setServiceHistory() {


        DateTime current = DateTime.now();
        DateTime previous15D = current.minusDays(15);
        DateTime previous3M = current.minusMonths(3).dayOfMonth().withMinimumValue();
        DateTime previous1Y = current.minusYears(1).dayOfMonth().withMinimumValue();
        SimpleDateFormat backFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (type.equals("default")) {
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous3M.toDate());
            getServiceHistory();

        } else if (type.equals("days")) {
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous15D.toDate());
            getServiceHistory();

        } else if (type.equals("months")) {
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous3M.toDate());
            getServiceHistory();

        } else if (type.equals("years")) {
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous1Y.toDate());
            getServiceHistory();

        }


        lnrDays.setOnClickListener(view -> {
            type = "days";
            pageNumber = 0;
            lnrDays.setBackgroundColor(Color.parseColor("#4682B4"));
            lnrOneMonth.setBackgroundColor(Color.TRANSPARENT);
            lnrThreeMonth.setBackgroundColor(Color.TRANSPARENT);
            txtDays.setTextColor(Color.WHITE);
            txtOneMonth.setTextColor(Color.BLACK);
            txtThreeMonth.setTextColor(Color.BLACK);
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous15D.toDate());
            Log.e("CAL", sDate + " - " + eDate);
            getServiceHistory();
        });

        lnrOneMonth.setOnClickListener(view -> {
            type = "months";
            pageNumber = 0;
            lnrDays.setBackgroundColor(Color.TRANSPARENT);
            lnrOneMonth.setBackgroundColor(Color.parseColor("#4682B4"));
            lnrThreeMonth.setBackgroundColor(Color.TRANSPARENT);
            txtDays.setTextColor(Color.BLACK);
            txtOneMonth.setTextColor(Color.WHITE);
            txtThreeMonth.setTextColor(Color.BLACK);
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous3M.toDate());
            Log.e("CAL", sDate + " - " + eDate);
            getServiceHistory();
        });

        lnrThreeMonth.setOnClickListener(view -> {
            type = "years";
            pageNumber = 0;
            lnrDays.setBackgroundColor(Color.TRANSPARENT);
            lnrOneMonth.setBackgroundColor(Color.TRANSPARENT);
            lnrThreeMonth.setBackgroundColor(Color.parseColor("#4682B4"));
            txtDays.setTextColor(Color.BLACK);
            txtOneMonth.setTextColor(Color.BLACK);
            txtThreeMonth.setTextColor(Color.WHITE);
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous1Y.toDate());
            Log.e("CAL", sDate + " - " + eDate);
            getServiceHistory();
        });
//        getServiceHistory();
    }

    private void getServiceHistory() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    ServicePresenter presenter = new ServicePresenter(this);
                    presenter.getServiceHistory(accountNo, sDate, eDate, pageNumber, 10, true);
                    adapter.notifyDataSetChanged();
                } else {
                    mBranchRealmResults = getRealm().where(Branch.class).findAll();
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        ServicePresenter presenter = new ServicePresenter(this);
                        presenter.getServiceHistory(accountNo, sDate, eDate, pageNumber, 10, true);
                        adapter.notifyDataSetChanged();
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
        if (services != null ) {
            if (pageNumber == 0 && services.size() > 0) {
                adapter.setData(services);
                adapter.notifyDataSetChanged();
                emptyBox.setVisibility(View.GONE);
            } else if (services.size() > 0) {
                adapter.addData(services);
                adapter.notifyDataSetChanged();
                emptyBox.setVisibility(View.GONE);
            } else {
                pageNumber -= 10;
                adapter.notifyDataSetChanged();
                emptyBox.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error", message);
    }
}

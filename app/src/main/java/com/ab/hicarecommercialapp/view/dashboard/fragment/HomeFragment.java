package com.ab.hicarecommercialapp.view.dashboard.fragment;


import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;

import com.ab.hicarecommercialapp.adapter.RecyclerViewServiceAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewUpcomingAdapter;
import com.ab.hicarecommercialapp.adapter.ViewPagerTodaysAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.expert.ExpertRequest;
import com.ab.hicarecommercialapp.model.expert.ExpertResponse;
import com.ab.hicarecommercialapp.model.graph.GraphData;
import com.ab.hicarecommercialapp.model.graph.GraphRequest;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.ServiceTasks;
import com.ab.hicarecommercialapp.model.service.Service_Details;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.FixedSpeedScroller;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.audits.AuditFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.complaints.ComplaintFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.expert.ExpertPresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.expert.ExpertView;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoiceFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.jobcards.JobCardFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.payments.PaymentFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServiceFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServicePresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServiceView;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.TodayServicePresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.TodayServiceView;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.UpcomingServicePresenter;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.UpcomingServiceView;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.UpcomingServicesFragment;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements GraphView, TodayServiceView, UpcomingServiceView, ExpertView {

    @BindView(R.id.shimmerHome)
    LinearLayout shimmerHome;

    @BindView(R.id.lnrToday)
    LinearLayout lnrToday;

    @BindView(R.id.lnrLeft)
    RelativeLayout lnrLeft;

    @BindView(R.id.lnrRight)
    RelativeLayout lnrRight;

    @BindView(R.id.imgLeft)
    ImageView imgLeft;

    @BindView(R.id.imgLeftDefault)
    ImageView imgLeftDefault;

    @BindView(R.id.imgRight)
    ImageView imgRight;

    @BindView(R.id.imgRightDefault)
    ImageView imgRightDefault;

    @BindView(R.id.scrollHome)
    NestedScrollView scrollHome;

    @BindView(R.id.recycleTodayService)
    RecyclerView recycleTodayService;

    @BindView(R.id.recycleOrders)
    RecyclerView recycleOrders;

    @BindView(R.id.chart)
    CombinedChart chart;

    @BindView(R.id.btnViewMore)
    LinearLayout btnViewMore;

    @BindView(R.id.lnrAudit)
    LinearLayout lnrAudit;

    @BindView(R.id.lnrExpert)
    LinearLayout lnrExpert;

    @BindView(R.id.lnrPayment)
    LinearLayout lnrPayment;

    @BindView(R.id.lnrService)
    LinearLayout lnrService;

    @BindView(R.id.lnrComplaint)
    LinearLayout lnrComplaint;

    @BindView(R.id.auditOuter)
    LinearLayout auditOuter;

    @BindView(R.id.auditInner)
    LinearLayout auditInner;

    @BindView(R.id.expertOuter)
    LinearLayout expertOuter;

    @BindView(R.id.expertInner)
    LinearLayout expertInner;

    @BindView(R.id.paymentOuter)
    LinearLayout paymentOuter;

    @BindView(R.id.paymentInner)
    LinearLayout paymentInner;

    @BindView(R.id.complaintOuter)
    LinearLayout complaintOuter;

    @BindView(R.id.complaintInner)
    LinearLayout complaintInner;

    @BindView(R.id.serviceOuter)
    LinearLayout serviceOuter;

    @BindView(R.id.serviceInner)
    LinearLayout serviceInner;

    @BindView(R.id.lnrWeek)
    LinearLayout lnrWeeks;

    @BindView(R.id.lnrMonth)
    LinearLayout lnrMonths;

    @BindView(R.id.lnrYear)
    LinearLayout lnrYears;

    @BindView(R.id.lnrNoGraph)
    LinearLayout lnrNoGraph;

    @BindView(R.id.txtWeek)
    TextView txtWeeks;

    @BindView(R.id.txtMonth)
    TextView txtMonths;

    @BindView(R.id.txtYear)
    TextView txtYears;

    @BindView(R.id.txtDuration)
    TextView txtDuration;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.todaysPager)
    ViewPager todaysPager;

    @BindView(R.id.lnrUpcoming)
    LinearLayout lnrUpcoming;

    @BindView(R.id.emptyBox)
    LinearLayout emptyBox;


    RecyclerView.LayoutManager layoutManager;
    private Integer pageNumber = 0;
    private RecyclerViewUpcomingAdapter adapter;
    private RecyclerViewServiceAdapter madapter;
    int counterLeft = 0;
    int counterRight = 0;
    int counter;
    private String type = "";
    private String sDate = "";
    private String eDate = "";
    public int mIfCounter;

    ArrayList<Entry> dataset1;
    ArrayList<BarEntry> dataset2;
    ArrayList<String> xAxisLabel;

    int currentPage = 0;
    int NUM_PAGES = 0;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;

    DateTime current = DateTime.now();
    DateTime previous3 = current.minusMonths(2).dayOfMonth().withMinimumValue();
    DateTime previous6 = current.minusMonths(5).dayOfMonth().withMinimumValue();
    DateTime previousYear = current.minusYears(1).dayOfMonth().withMinimumValue();
    SimpleDateFormat backFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat frontFormat = new SimpleDateFormat("dd MMM, yyyy");

    List<GraphData> graphList = new ArrayList<>();
    Interpolator sInterpolator = new AccelerateInterpolator();
    RealmResults<Branch> mBranchRealmResults;
    ViewPagerTodaysAdapter pagerAdapter;
    private static boolean run = true;



    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        showBackButton(false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter =
                new RecyclerViewUpcomingAdapter(getActivity(), true);
        recycleOrders.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleOrders.setHasFixedSize(true);
        recycleOrders.setClipToPadding(false);
        recycleOrders.setAdapter(adapter);

        if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_TODAY)) {
            madapter =
                    new RecyclerViewServiceAdapter(getActivity());
            pagerAdapter = new ViewPagerTodaysAdapter(getActivity(), todaysPager);
            todaysPager.setAdapter(pagerAdapter);
        } else {
            todaysPager.setVisibility(View.GONE);
        }

//        try {
//            Field mScroller;
//            mScroller = ViewPager.class.getDeclaredField("mScroller");
//            mScroller.setAccessible(true);
//            FixedSpeedScroller scroller = new FixedSpeedScroller(todaysPager.getContext(), sInterpolator);
//            // scroller.setFixedDuration(5000);
//            mScroller.set(todaysPager, scroller);
//        } catch (NoSuchFieldException e) {
//        } catch (IllegalArgumentException e) {
//        } catch (IllegalAccessException e) {
//        }
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.navigationBorder).setVisibility(View.VISIBLE);
        dataset1 = new ArrayList<>();
        dataset2 = new ArrayList<>();
        xAxisLabel = new ArrayList<>();
        mBranchRealmResults = getRealm().where(Branch.class).findAll();
        getUpcomingServices();
        getTodaysServices();
        setGraphByCalendar();
        setTasks();
        btnViewMore.setOnClickListener(view123 -> replaceFragment(UpcomingServicesFragment.newInstance(), "HomeFragment-UpcomingServicesFragment"));
    }

    private void getTodaysService() {

        final Handler handler = new Handler();
        Timer timer = new Timer();
        final Runnable update = () -> {

            todaysPager.setCurrentItem(currentPage, true);
            if (currentPage == 3) {
                currentPage = todaysPager.getCurrentItem() - 1;
            } else {
                ++currentPage;
            }
        };


        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
                if (run) {
                    handler.post(update);
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 2000, 3000);
        run = false;
    }


    private void getTodaysServices() {
        for (int i = 0; i <= 3; i++)
            NUM_PAGES = 3;
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == NUM_PAGES) {
                currentPage = 0;
            }
            todaysPager.setCurrentItem(currentPage++, true);
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        setGraphByCalendar();
//        getTodaysServices();
    }

    private void setGraphByCalendar() {

        type = "Month";
        eDate = backFormat.format(current.toDate());
        sDate = backFormat.format(previous6.toDate());

        getGraphData();

        lnrWeeks.setOnClickListener(view -> {
            type = "Month";
            lnrWeeks.setBackgroundColor(Color.parseColor("#4682B4"));
            lnrMonths.setBackgroundColor(Color.TRANSPARENT);
            lnrYears.setBackgroundColor(Color.TRANSPARENT);
            txtWeeks.setTextColor(Color.WHITE);
            txtMonths.setTextColor(Color.BLACK);
            txtYears.setTextColor(Color.BLACK);
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous3.toDate());
            Log.e("CAL", sDate + " - " + eDate);
            getGraphData();
        });

        lnrMonths.setOnClickListener(view -> {
            type = "Month";
            lnrWeeks.setBackgroundColor(Color.TRANSPARENT);
            lnrMonths.setBackgroundColor(Color.parseColor("#4682B4"));
            lnrYears.setBackgroundColor(Color.TRANSPARENT);
            txtWeeks.setTextColor(Color.BLACK);
            txtMonths.setTextColor(Color.WHITE);
            txtYears.setTextColor(Color.BLACK);
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previous6.toDate());
            Log.e("CAL", sDate + " - " + eDate);
            getGraphData();
        });

        lnrYears.setOnClickListener(view -> {
            type = "Year";
            lnrWeeks.setBackgroundColor(Color.TRANSPARENT);
            lnrMonths.setBackgroundColor(Color.TRANSPARENT);
            lnrYears.setBackgroundColor(Color.parseColor("#4682B4"));
            txtWeeks.setTextColor(Color.BLACK);
            txtMonths.setTextColor(Color.BLACK);
            txtYears.setTextColor(Color.WHITE);
            eDate = backFormat.format(current.toDate());
            sDate = backFormat.format(previousYear.toDate());
            Log.e("CAL", sDate + " - " + eDate);
            getGraphData();
        });


    }

    private void getGraphData() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    GraphRequest request = new GraphRequest();
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    request.setAccountNo(accountNo);
                    request.setUserId(mloginRealmModel.get(0).getMobile());
                    request.setReportType(type);
                    request.setStartDate(sDate);
                    request.setEndDate(eDate);
                    GraphPresenter presenter = new GraphPresenter(this);
                    presenter.getGraphData(request);
                } else {
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        GraphRequest request = new GraphRequest();
                        request.setAccountNo(accountNo);
                        request.setUserId(mloginRealmModel.get(0).getMobile());
                        request.setReportType(type);
                        request.setStartDate(sDate);
                        request.setEndDate(eDate);
                        GraphPresenter presenter = new GraphPresenter(this);
                        presenter.getGraphData(request);
                    }
                }

            }
        }
    }

    private void getUpcomingServices() {
        progressBar.setVisibility(View.GONE);
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
//                    TodayServicePresenter todayServicePresenter = new TodayServicePresenter(this);
//                    todayServicePresenter.getTodayServices(request);
                } else {
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
//                        TodayServicePresenter todayServicePresenter = new TodayServicePresenter(this);
//                        todayServicePresenter.getTodayServices(request);
                    }
                }

            }
        }
    }

    private void setTasks() {

        ((GradientDrawable) auditOuter.getBackground()).setColor(Color.parseColor("#4682B4"));
        ((GradientDrawable) auditInner.getBackground()).setColor(Color.parseColor("#add8e6"));

        ((GradientDrawable) expertOuter.getBackground()).setColor(Color.parseColor("#4682B4"));
        ((GradientDrawable) expertInner.getBackground()).setColor(Color.parseColor("#add8e6"));

        ((GradientDrawable) complaintOuter.getBackground()).setColor(Color.parseColor("#4682B4"));
        ((GradientDrawable) complaintInner.getBackground()).setColor(Color.parseColor("#add8e6"));

        ((GradientDrawable) serviceOuter.getBackground()).setColor(Color.parseColor("#4682B4"));
        ((GradientDrawable) serviceInner.getBackground()).setColor(Color.parseColor("#add8e6"));

        lnrAudit.setOnClickListener(view -> replaceFragment(AuditFragment.newInstance(), "HomeFragment-AuditFragment"));
        lnrExpert.setOnClickListener(view -> replaceFragment(JobCardFragment.newInstance(), "HomeFragment-JobCardFragment"));

        lnrExpert.setOnClickListener(view -> getAnExpertRequest());
        lnrPayment.setOnClickListener(view -> replaceFragment(PaymentFragment.newInstance(), "HomeFragment-PaymentFragment"));
        lnrService.setOnClickListener(view -> replaceFragment(ServiceFragment.newInstance(), "HomeFragment-ServiceFragment"));
        lnrComplaint.setOnClickListener(view -> replaceFragment(ComplaintFragment.newInstance(), "HomeFragment-ComplaintFragment"));
    }

    private void getAnExpertRequest() {
        if ((HomeActivity) getActivity() != null) {
            if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                RealmResults<LoginResponse> mloginRealmModel =
                        BaseApplication.getRealm().where(LoginResponse.class).findAll();
                ExpertPresenter presenter = new ExpertPresenter(this);
                ExpertRequest request = new ExpertRequest();
                request.setUserId(mloginRealmModel.get(0).getUserId());
                request.setAccountName(mBranchRealmResults.get(0).getAccountName());
                request.setAccountNo(mBranchRealmResults.get(0).getAccountKey());
                request.setEmail(mloginRealmModel.get(0).getEmail());
                request.setTitle("");
                request.setMobile(mloginRealmModel.get(0).getMobile());
                request.setUserName(mloginRealmModel.get(0).getName());
                presenter.getAnExpert(request);
            }
        }

    }


    @Override
    public void showLoading() {
        shimmerHome.setVisibility(View.VISIBLE);
        scrollHome.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        shimmerHome.setVisibility(View.GONE);
        scrollHome.setVisibility(View.VISIBLE);
    }

    @Override
    public void setExpert(ExpertResponse response) {
        if (response.getSuccess()) {
            getSuccessDialog();
        }
    }

    private void getSuccessDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.layout_expert_dialog, null);
        Button cancel = (Button) dialogview.findViewById(R.id.dialog_cancel);
        builder.setView(dialogview);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();

        cancel.setOnClickListener(view -> {
            dialog.cancel();
        });
    }

    @Override
    public void setUpcomingService(List<ServiceTasks> upcomingServices) {
        progressBar.setVisibility(View.GONE);
        if (upcomingServices != null) {
            if (pageNumber == 0 && upcomingServices.size() > 0) {
                adapter.setData(upcomingServices);
                adapter.notifyDataSetChanged();
                lnrUpcoming.setVisibility(View.VISIBLE);
                emptyBox.setVisibility(View.GONE);
            } else if (upcomingServices.size() > 0) {
                adapter.addData(upcomingServices);
                adapter.notifyDataSetChanged();
                lnrUpcoming.setVisibility(View.VISIBLE);
                emptyBox.setVisibility(View.GONE);

            } else {
                lnrUpcoming.setVisibility(View.GONE);
                emptyBox.setVisibility(View.VISIBLE);
                pageNumber -= 5;
            }
        } else {
            lnrUpcoming.setVisibility(View.GONE);
            emptyBox.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setTodayService(List<Service_Details> services) {
        progressBar.setVisibility(View.GONE);
        if (services != null) {
            lnrToday.setVisibility(View.VISIBLE);
            if (pageNumber == 0 && services.size() > 0) {
                madapter.setData(services);
                madapter.notifyDataSetChanged();
            } else if (services.size() > 0) {
                madapter.addData(services);
                madapter.notifyDataSetChanged();
            } else {
                pageNumber -= 5;
            }
        } else {
            lnrToday.setVisibility(View.GONE);
        }
    }


    @Override
    public void setGraph(List<GraphData> data) {
        if (data.size() > 0) {
            lnrNoGraph.setVisibility(View.GONE);
            graphList = data;
            setCombineChart();
        } else {
            lnrNoGraph.setVisibility(View.VISIBLE);
            graphList = data;
            setCombineChart();
        }

    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error ", message);
    }

    private void setCombineChart() {
        chart.getDescription().setText("");
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        // draw bars behind lines
        chart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.LINE});
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.animateXY(2000, 2000);

        Legend l = chart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);
//        leftAxis.setGranularity(2);
//        leftAxis.setLabelCount(8, true);
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        xAxisLabel.clear();
        dataset1.clear();
        dataset2.clear();

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
//        xAxis.setLabelRotationAngle(45);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        for (int i = 0; i < graphList.size(); i++) {
            xAxisLabel.add(graphList.get(i).getXAxis());
            dataset1.add(new Entry(i, Float.parseFloat(graphList.get(i).getDataset1())));
            dataset2.add(new BarEntry(i, Float.parseFloat(graphList.get(i).getDataset2())));
            Log.i("chart", graphList.get(i).getXAxis());
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        CombinedData data = new CombinedData();
        try {
            data.setData(generateBarData());
            data.setData(generateLineData());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        xAxis.setAxisMaximum(data.getXMax() + 1f);
        xAxis.setAxisMinimum(data.getXMin() - .5f);
        xAxis.setAxisMaximum(data.getXMax() + .5f);
        chart.setData(data);
        chart.invalidate();
    }

    private LineData generateLineData() {
        LineData d = new LineData();
        ArrayList<Entry> entries = new ArrayList<Entry>();

//        for (int index = 0; index < 8; index++)
//            entries.add(new Entry(index, getRandom(10, 40)));

        LineDataSet set = new LineDataSet(dataset1, "Planned");
        set.setColor(getResources().getColor(R.color.colorPrimary));
        set.setLineWidth(1.5f);
        set.setCircleHoleRadius(2f);
        set.setCircleColor(getResources().getColor(R.color.colorPrimary));
        set.setDrawCircles(true);
        set.setDrawValues(false);
        set.setValueTextSize(8f);
        set.setDrawHorizontalHighlightIndicator(true);
        d.addDataSet(set);
        return d;
    }

    private BarData generateBarData() {

        BarData d = new BarData();
        d.setBarWidth(0.3f);
//        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

//        for (int index = 0; index < 8; index++)
//            entries.add(new BarEntry(index, getRandom(15, 30)));
        try {
            BarDataSet set = new BarDataSet(dataset2, "Actual");
            set.setColors(getResources().getColor(R.color.fade_blue));
            set.setHighlightEnabled(false);
//        set.setValueTextSize(8f);
            set.setDrawValues(false);
//        set.setBarBorderWidth(0.2f);
            d.addDataSet(set);
            set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

    @Override
    public void onPause() {
        super.onPause();
//        swipeTimer.cancel();
//        swipeTimer.purge();
    }
}

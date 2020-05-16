package com.ab.hicarecommercialapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.service.Service_Details;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 10/31/2019.
 */
public class ViewPagerTodaysAdapter extends PagerAdapter {
    private List<Service_Details> items;
    private Context mContext;
    @BindView(R.id.txtService)
    TextView txtService;
    @BindView(R.id.txtType)
    TextView txtType;
    @BindView(R.id.imgCancel)
    ImageView imgCancel;
    private ViewPager pager;

    public ViewPagerTodaysAdapter(Context context, ViewPager todaysPager) {
        mContext = context;
        this.pager = todaysPager;
        if (items == null) {
            items = new ArrayList<>();
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void setData(List<Service_Details> data) {
        items.clear();
        items.addAll(data);
    }

    public void addData(List<Service_Details> data) {
        items.addAll(data);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_todays_service_pager,
                viewGroup, false);
        ButterKnife.bind(this, view);
        txtService.setText(items.get(position).getService_Plan__c());
        txtType.setText(items.get(position).getTasksList().get(0).getTechnicianDetail().getTechnicianName());
        imgCancel.setOnClickListener(view1 -> pager.setVisibility(View.GONE));
        imgCancel.setOnClickListener(view12 -> {
            pager.setVisibility(View.GONE);
            SharedPreferencesUtility.savePrefBoolean(mContext, SharedPreferencesUtility.IS_TODAY, false);
        });
        viewGroup.addView(view);
        return view;
    }


}
package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.order.Orders;
import com.ab.hicarecommercialapp.model.service.Service_Details;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 10/1/2019.
 */
public class RecyclerViewOrderServicesAdapter extends RecyclerView.Adapter<RecyclerViewOrderServicesAdapter.RecyclerViewHolder> {

    private List<Service_Details> items;
    private Activity context;
    private static RecyclerViewOrderServicesAdapter.ClickListener clickListener;
    private Boolean isTrue = false;

    public RecyclerViewOrderServicesAdapter( FragmentActivity activity, Boolean isTrue) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.isTrue = isTrue;
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewOrderServicesAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_services_adapter,
                viewGroup, false);
        return new RecyclerViewOrderServicesAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewOrderServicesAdapter.RecyclerViewHolder holder, int position) {
        String srCount = String.valueOf(items.get(position).getTotalSRCount());
        holder.txtServiceCount.setText("Service Count-"
                + String.valueOf(items.get(position).getSequence_No())
                + "/"
                + srCount);

        holder.txtSequence.setText(String.valueOf(items.get(position).getSequence_No()));
        holder.txtAction.setText(items.get(position).getService_Step());
        holder.txtStatus.setText(items.get(position).getStatus());
        if (items.get(position).getTechnicianDetails() != null) {
            holder.lnrTechDetail.setVisibility(View.VISIBLE);
            holder.txtTechName.setText(items.get(position).getTechnicianDetails().get(0).getTechnicianName());
        } else {
            holder.txtTechName.setText("N/D");
        }
        if (items.get(position).getCompleted_Date() != null && items.get(position).getCompleted_Date().length() > 0) {
            holder.lnrCompleted.setVisibility(View.VISIBLE);
            holder.txtCompleted.setText(items.get(position).getCompleted_Date());
        } else {
            holder.txtCompleted.setText("N/D");
        }
        String status = items.get(position).getStatus();
        if (status.equalsIgnoreCase("Open")) {
            holder.txtStatus.setTextColor(Color.parseColor("#0E8C3A"));
        } else if (status.equalsIgnoreCase("Closed")) {
            holder.txtStatus.setTextColor(Color.parseColor("#f51b00"));
        } else {
            holder.txtStatus.setTextColor(Color.parseColor("#4682B4"));
        }

        try {
            String year = TimeUtil.reFormatDateTime(items.get(position).getScheduled_Date(), "yyyy");
            String day = TimeUtil.reFormatDateTime(items.get(position).getScheduled_Date(), "dd");
            String month = TimeUtil.reFormatDateTime(items.get(position).getScheduled_Date(), "MMM");
            holder.txtDay.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
            holder.txtYear.setText(year);
            holder.txtDay.setText(day);
            holder.txtMonth.setText(month.toUpperCase());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setData(List<Service_Details> data) {
        items.clear();
        items.addAll(data);
    }

    public void addData(List<Service_Details> data) {
        items.addAll(data);
    }


    @Override
    public int getItemCount() {
        if (isTrue && items.size() == 5) {
            return 5;
        } else {
            return items.size();
        }

    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtAction)
        TextView txtAction;

        @BindView(R.id.txtSequence)
        TextView txtSequence;

        @BindView(R.id.txtStatus)
        TextView txtStatus;

        @BindView(R.id.txtDay)
        TextView txtDay;

        @BindView(R.id.txtMonth)
        TextView txtMonth;

        @BindView(R.id.txtYear)
        TextView txtYear;

        @BindView(R.id.btnView)
        LinearLayout btnView;

        @BindView(R.id.lnrCompleted)
        LinearLayout lnrCompleted;

        @BindView(R.id.lnrTechDetail)
        LinearLayout lnrTechDetail;

        @BindView(R.id.txtCompleted)
        TextView txtCompleted;

        @BindView(R.id.txtServiceCount)
        TextView txtServiceCount;

        @BindView(R.id.txtName)
        TextView txtTechName;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            btnView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(RecyclerViewOrderServicesAdapter.ClickListener clickListener) {
        RecyclerViewOrderServicesAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}


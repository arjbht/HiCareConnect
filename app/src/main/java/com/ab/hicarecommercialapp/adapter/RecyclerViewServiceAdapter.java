package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.service.Service_Details;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/23/2019.
 */
public class RecyclerViewServiceAdapter extends RecyclerView.Adapter<RecyclerViewServiceAdapter.RecyclerViewHolder> {

    private List<Service_Details> items;
    private Activity context;
//    private static ClickListener clickListener;

    public RecyclerViewServiceAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewServiceAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_adapter,
                viewGroup, false);
        return new RecyclerViewServiceAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewServiceAdapter.RecyclerViewHolder holder, int position) {
        holder.txtService.setText(items.get(position).getService_Plan__c());
        String srCount = String.valueOf(items.get(position).getTotalSRCount());
        holder.txtSequence.setText(String.valueOf((int) items.get(position).getSequence_No()) + "/" + srCount);
        String status = items.get(position).getStatus();
        if (status.equalsIgnoreCase("Open")) {
            holder.txtStatus.setTextColor(Color.parseColor("#0E8C3A"));
        } else if (status.equalsIgnoreCase("Closed")) {
            holder.txtStatus.setTextColor(Color.parseColor("#f51b00"));
        } else {
            holder.txtStatus.setTextColor(Color.parseColor("#4682B4"));
        }
        holder.txtStatus.setText(items.get(position).getStatus());
        holder.txtDay.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        try {
            String year = TimeUtil.reFormatDateTime(items.get(position).getScheduled_Date(), "yyyy");
            String day = TimeUtil.reFormatDateTime(items.get(position).getScheduled_Date(), "dd");
            String month = TimeUtil.reFormatDateTime(items.get(position).getScheduled_Date(), "MMM");

            holder.txtYear.setText(year);
            holder.txtDay.setText(day);
            holder.txtMonth.setText(month.toUpperCase());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txtType.setText(AppUtils.GetSMSServiceType(items.get(position).getService_Group_Code__c()));

        if (items.get(position).getCompleted_Date() != null && items.get(position).getCompleted_Date().length() > 0) {
            holder.lnrCompletion.setVisibility(View.VISIBLE);
            holder.txtCompletion.setText(items.get(position).getCompleted_Date());
        } else {
            holder.txtCompletion.setText("N/D");
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
        return items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtSequence)
        TextView txtSequence;

        @BindView(R.id.txtService)
        TextView txtService;

        @BindView(R.id.txtStatus)
        TextView txtStatus;

        @BindView(R.id.txtOrderNo)
        TextView txtOrderNo;

        @BindView(R.id.txtCompletion)
        TextView txtCompletion;

        @BindView(R.id.txtDay)
        TextView txtDay;

        @BindView(R.id.txtType)
        TextView txtType;

        @BindView(R.id.txtMonth)
        TextView txtMonth;

        @BindView(R.id.txtYear)
        TextView txtYear;

        @BindView(R.id.lnrCompletion)
        LinearLayout lnrCompletion;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            clickListener.onClick(v, getAdapterPosition());
        }
    }

//    public void setOnItemClickListener(ClickListener clickListener) {
//        RecyclerViewServiceAdapter.clickListener = clickListener;
//    }


//    public interface ClickListener {
//        void onClick(View view, int position);
//    }
}


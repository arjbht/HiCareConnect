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
import com.ab.hicarecommercialapp.model.service.ServiceTasks;
import com.ab.hicarecommercialapp.model.service.Service_Details;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 10/29/2019.
 */
public class RecyclerViewUpcomingAdapter extends RecyclerView.Adapter<RecyclerViewUpcomingAdapter.RecyclerViewHolder> {

    private List<ServiceTasks> items;
    private Activity context;
    private Boolean isTrue;
//    private static ClickListener clickListener;

    public RecyclerViewUpcomingAdapter(FragmentActivity activity, boolean isTrue) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
        this.isTrue = isTrue;
    }

    @NonNull
    @Override
    public RecyclerViewUpcomingAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_services_adapter,
                viewGroup, false);
        return new RecyclerViewUpcomingAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewUpcomingAdapter.RecyclerViewHolder holder, int position) {
        holder.txtOrderNo.setText(items.get(position).getOrderNumber__c());
        String srCount = String.valueOf((int)items.get(position).getTotalSRCount());
        holder.txtSequence.setText(String.valueOf((int) items.get(position).getService_Sequence_Number__c()) + "/" + srCount);
        String status = items.get(position).getTechnician_Status();
        if (status.equalsIgnoreCase("Open")) {
            holder.txtStatus.setTextColor(Color.parseColor("#0E8C3A"));
        } else if (status.equalsIgnoreCase("Closed")) {
            holder.txtStatus.setTextColor(Color.parseColor("#f51b00"));
        } else {
            holder.txtStatus.setTextColor(Color.parseColor("#4682B4"));
        }
        holder.txtStatus.setText(items.get(position).getTechnician_Status());
        try {
            String year = TimeUtil.reFormatDateTime(items.get(position).getAppointmentDate(), "yyyy");
            String day = TimeUtil.reFormatDateTime(items.get(position).getAppointmentDate(), "dd");
            String month = TimeUtil.reFormatDateTime(items.get(position).getAppointmentDate(), "MMM");
            holder.txtDay.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
            holder.txtYear.setText(year);
            holder.txtDay.setText(day);
            holder.txtMonth.setText(month.toUpperCase());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txtType.setText(AppUtils.GetSMSServiceType(items.get(position).getService_Group_Code__c()));
    }

    public void setData(List<ServiceTasks> data) {
        items.clear();
        items.addAll(data);
    }

    public void addData(List<ServiceTasks> data) {
        items.addAll(data);
    }


    @Override
    public int getItemCount() {
        if (isTrue && items.size() == 3) {
            return 3;
        } else {
            return items.size();
        }

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

        @BindView(R.id.txtType)
        TextView txtType;

        @BindView(R.id.txtYear)
        TextView txtYear;

        @BindView(R.id.txtDay)
        TextView txtDay;

        @BindView(R.id.txtMonth)
        TextView txtMonth;


//        @BindView(R.id.txtCompletion)
//        TextView txtCompletion;


        @BindView(R.id.btnView)
        LinearLayout btnView;

//        @BindView(R.id.lnrCompletion)
//        LinearLayout lnrCompletion;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(this);
//            btnView.setOnClickListener(this);
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


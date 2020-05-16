package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.invoice.Invoices;
import com.ab.hicarecommercialapp.model.order.Orders;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/20/2019.
 */
public class RecyclerViewOrderAdapter extends RecyclerView.Adapter<RecyclerViewOrderAdapter.RecyclerViewHolder> {

    private List<Orders> items;
    private Activity context;
    private static ClickListener clickListener;

    public RecyclerViewOrderAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewOrderAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_adapter,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewOrderAdapter.RecyclerViewHolder holder, int position) {
        holder.txtOrderNo.setText("Order #" + items.get(position).getOrderNumber());
        holder.txtPlan.setText(items.get(position).getServicePlanName());
        holder.txtType.setText(AppUtils.GetSMSServiceType(items.get(position).getServicePlan().getServiceGroupCodeC()));
        if(items.get(position).getCustStatus().equalsIgnoreCase("Active")){
            holder.txtStatus.setTextColor(Color.parseColor("#0E8C3A"));
        }
        else {
            holder.txtStatus.setTextColor(Color.parseColor("#4682B4"));
        }
        holder.txtStatus.setText(items.get(position).getCustStatus());
        holder.txtStrikeAmount.setText("\u20B9 " + items.get(position).getStandard_Value__c());
        holder.txtStrikeAmount.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        holder.txtStrikeAmount.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        holder.txtDay.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
//        holder.txtDiscount.setPrimaryText(items.get(position).getDiscount_Offered__c()+"%");
        try {
            String year = TimeUtil.reFormatDate(items.get(position).getStartDate(), "yyyy");
            String day = TimeUtil.reFormatDate(items.get(position).getStartDate(), "dd");
            String month = TimeUtil.reFormatDate(items.get(position).getStartDate(), "MMM");

            holder.txtYear.setText(year);
            holder.txtDay.setText(day);
            holder.txtMonth.setText(month.toUpperCase());


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setData(List<Orders> data) {
        items.clear();
        items.addAll(data);
    }

    public void addData(List<Orders> data) {
        items.addAll(data);
    }

    public Orders getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtOrderNo)
        TextView txtOrderNo;

//        @BindView(R.id.txtDiscount)
//        TriangleLabelView txtDiscount;

        @BindView(R.id.txtStatus)
        TextView txtStatus;

        @BindView(R.id.txtStrikeAmount)
        TextView txtStrikeAmount;

        @BindView(R.id.txtPlan)
        TextView txtPlan;

        @BindView(R.id.txtDay)
        TextView txtDay;

        @BindView(R.id.txtMonth)
        TextView txtMonth;

        @BindView(R.id.txtYear)
        TextView txtYear;

        @BindView(R.id.txtType)
        TextView txtType;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewOrderAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}


package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.audit.Audit;
import com.ab.hicarecommercialapp.model.invoice.Invoices;
import com.ab.hicarecommercialapp.utils.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 10/30/2019.
 */
public class RecyclerViewAuditAdapter extends RecyclerView.Adapter<RecyclerViewAuditAdapter.RecyclerViewHolder> {

    private List<Audit> items;
    private Activity context;
    private static RecyclerViewAuditAdapter.ClickListener clickListener;

    public RecyclerViewAuditAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewAuditAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_audits_adapter,
                viewGroup, false);
        return new RecyclerViewAuditAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAuditAdapter.RecyclerViewHolder holder, int position) {
        try {
            String year = TimeUtil.reFormatDateTime(items.get(position).getAuditDate(), "yyyy");
            String day = TimeUtil.reFormatDateTime(items.get(position).getAuditDate(), "dd");
            String month = TimeUtil.reFormatDateTime(items.get(position).getAuditDate(), "MMM");
            holder.txtDay.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
            holder.txtYear.setText(year);
            holder.txtDay.setText(day);
            holder.txtMonth.setText(month.toUpperCase());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.txtTitle.setText(items.get(position).getHicareActions());
        if (items.get(position).getAuditCustomerFeedback().length() > 0) {
            holder.txtDescription.setText(items.get(position).getAuditCustomerFeedback());
        } else {
            holder.txtDescription.setText("NA");
        }
        try {
            float rating = Float.parseFloat(items.get(position).getAuditRating()) / 2;
            holder.ratingBar.setRating(rating);
        } catch (Exception e) {
            holder.ratingBar.setRating(0);
        }

    }

    public void setData(List<Audit> data) {
        items.clear();
        items.addAll(data);
    }

    public void addData(List<Audit> data) {
        items.addAll(data);
    }

    public void removeAll() {
        items.removeAll(items);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtYear)
        TextView txtYear;

        @BindView(R.id.txtDay)
        TextView txtDay;

        @BindView(R.id.txtMonth)
        TextView txtMonth;

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.txtDescription)
        TextView txtDescription;

//        @BindView(R.id.imgDownload)
//        ImageView imgDownload;

        @BindView(R.id.rating)
        RatingBar ratingBar;

        @BindView(R.id.lnrMenu)
        RelativeLayout lnrMenu;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(this);
            lnrMenu.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnMenuItemClickListener(RecyclerViewAuditAdapter.ClickListener clickListener) {
        RecyclerViewAuditAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}



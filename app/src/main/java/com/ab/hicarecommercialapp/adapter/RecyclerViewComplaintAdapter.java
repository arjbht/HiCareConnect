package com.ab.hicarecommercialapp.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.invoice.Invoices;
import com.ab.hicarecommercialapp.utils.TimeUtil;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/23/2019.
 */
public class RecyclerViewComplaintAdapter extends RecyclerView.Adapter<RecyclerViewComplaintAdapter.RecyclerViewHolder> {

    private List<Complaints> items;
    private Activity context;
    private static ClickListener clickListener;

    public RecyclerViewComplaintAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewComplaintAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complaint_adapter,
                viewGroup, false);
        return new RecyclerViewComplaintAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewComplaintAdapter.RecyclerViewHolder holder, int position) {
        try {
            String mDate = TimeUtil.reFormatDateTime(items.get(position).getCreatedonText(), "MMM dd, yyyy");
            holder.txtComplaintDate.setText(mDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (items.get(position).getTitle() != null) {
            holder.txtTitle.setText(items.get(position).getTitle());
        } else {
            holder.txtTitle.setText("N/D");
        }
        if (items.get(position).getRemark() != null) {
            holder.txtDescription.setText(items.get(position).getRemark());
        } else {
            holder.txtDescription.setText("N/D");
        }
        if (items.get(position).getStatus() != null) {
            holder.txtStatus.setText(items.get(position).getStatus());
        } else {
            holder.txtStatus.setText("N/D");
        }
        holder.txtDay.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        try {
            String year = TimeUtil.reFormatDateTime(items.get(position).getCreatedonText(), "yyyy");
            String day = TimeUtil.reFormatDateTime(items.get(position).getCreatedonText(), "dd");
            String month = TimeUtil.reFormatDateTime(items.get(position).getCreatedonText(), "MMM");

            holder.txtYear.setText(year);
            holder.txtDay.setText(day);
            holder.txtMonth.setText(month.toUpperCase());


        } catch (ParseException e) {
            e.printStackTrace();
        }
//        if (items.get(position).getAttachmentUrl() != null && items.get(position).getAttachmentUrl().length() > 0) {
//            holder.noImg.setVisibility(View.GONE);
//            holder.imgComplaint.setVisibility(View.VISIBLE);
//            Picasso.get().load(items.get(position).getAttachmentUrl()).error(R.drawable.ic_image).into(holder.imgComplaint);
//        } else {
//            holder.noImg.setVisibility(View.VISIBLE);
//            holder.imgComplaint.setVisibility(View.GONE);
//            holder.noImg.setImageResource(R.drawable.ic_image);
//        }
    }

    public void setData(List<Complaints> data) {
        items.clear();
        items.addAll(data);
    }

    public void addData(List<Complaints> data) {
        items.addAll(data);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.txtType)
        TextView txtType;

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.txtDescription)
        TextView txtDescription;

        @BindView(R.id.txtStatus)
        TextView txtStatus;

        @BindView(R.id.txtComplaintDate)
        TextView txtComplaintDate;

        @BindView(R.id.imgComplaint)
        ImageView imgComplaint;

        @BindView(R.id.noImg)
        ImageView noImg;

        @BindView(R.id.txtDay)
        TextView txtDay;

        @BindView(R.id.txtMonth)
        TextView txtMonth;

        @BindView(R.id.txtYear)
        TextView txtYear;

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
        RecyclerViewComplaintAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}


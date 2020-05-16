package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.notification.Notifications;
import com.ab.hicarecommercialapp.utils.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/24/2019.
 */
public class RecyclerViewNotificationAdapter extends RecyclerView.Adapter<RecyclerViewNotificationAdapter.RecyclerViewHolder> {

    private List<Notifications> items;
    private Activity context;

    //    private static ClickListener clickListener;
    public RecyclerViewNotificationAdapter(/*List<Categories.Category> categories,*/  FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewNotificationAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification_adapter,
                viewGroup, false);
        return new RecyclerViewNotificationAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewNotificationAdapter.RecyclerViewHolder holder, int position) {
        holder.txtTitle.setText(items.get(position).getTitle());
        holder.txtDescription.setText(items.get(position).getDescription());
        String date = items.get(position).getNotification_DateTime();
        try {
            holder.txtDate.setText(TimeUtil.reFormatDate(date, "MMM dd, yyyy"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setData(List<Notifications> data) {
        items.clear();
        items.addAll(data);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgNotification)
        ImageView imgNotification;

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.txtDescription)
        TextView txtDescription;

        @BindView(R.id.txtDate)
        TextView txtDate;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            clickListener.onClick(v, getAdapterPosition());
        }
    }


//    public void setOnItemClickListener(ClickListener clickListener) {
//        RecyclerViewOrderAdapter.clickListener = clickListener;
//    }
//
//
//    public interface ClickListener {
//        void onClick(View view, int position);
//    }
}


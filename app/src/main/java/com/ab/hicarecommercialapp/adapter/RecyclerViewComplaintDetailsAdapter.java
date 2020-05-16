package com.ab.hicarecommercialapp.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.text.Html;
import android.text.SpannableStringBuilder;
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
import com.ab.hicarecommercialapp.model.complaint.InteractionLogs;
import com.ab.hicarecommercialapp.model.order.Orders;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.TimeUtil;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 10/1/2019.
 */
public class RecyclerViewComplaintDetailsAdapter extends RecyclerView.Adapter<RecyclerViewComplaintDetailsAdapter.RecyclerViewHolder> {

    private Activity context;
    private static RecyclerViewComplaintDetailsAdapter.ClickListener clickListener;
    private List<InteractionLogs> items = null;
    SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");


    public RecyclerViewComplaintDetailsAdapter(FragmentActivity activity) {

        this.context = activity;
        if (items == null) {
            items = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public RecyclerViewComplaintDetailsAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_interaction_adapter,
                viewGroup, false);
        return new RecyclerViewComplaintDetailsAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewComplaintDetailsAdapter.RecyclerViewHolder holder, int position) {
//        holder.txtInteraction.setText(Html.fromHtml(items.get(position).getBody_text().trim().trim()));
        CharSequence trimmer = items.get(position).getBody();
        SpannableStringBuilder ssb = new SpannableStringBuilder(trimmer);
        for (int i = 0; i < ssb.length() - 1; i++) {
            if (ssb.charAt(i) == '\n' && ssb.charAt(i + 1) == ' ') {
                ssb.replace(i + 1, i + 2, "");
            }
        }

        holder.txtInteraction.setText(Html.fromHtml(items.get(position).getBody_text()));
        try {
            DateTime today = DateTime.now();
            DateTime yesterday = today.minusDays(1);
            String mToday = format.format(today.toDate());
            String mYesterday = format.format(yesterday.toDate());
            String mDate = TimeUtil.reFormatDateTime(items.get(position).getCreated_at_text(), "E, MMM dd, yyyy");
            String mTodayDate = TimeUtil.reFormatDateTime(items.get(position).getCreated_at_text(), "MMM dd, yyyy");
            String mDay = TimeUtil.reFormatDateTime(items.get(position).getCreated_at_text(), "EEEE");


            if (mToday.equalsIgnoreCase(items.get(position).getCreated_at_text())) {
                holder.txtDay.setText("Today, ");
                holder.txtDate.setText(mTodayDate);
            } else if (mYesterday.equalsIgnoreCase(items.get(position).getCreated_at_text())) {
                holder.txtDay.setText("Yesterday, ");
                holder.txtDate.setText(mTodayDate);
            } else {
                holder.txtDay.setText(mDay + ", ");
                holder.txtDate.setText(mTodayDate);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setData(List<InteractionLogs> data) {
        items.clear();
        items.addAll(data);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtInteraction)
        TextView txtInteraction;

        @BindView(R.id.txtDate)
        TextView txtDate;

        @BindView(R.id.txtDay)
        TextView txtDay;


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
        RecyclerViewComplaintDetailsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}


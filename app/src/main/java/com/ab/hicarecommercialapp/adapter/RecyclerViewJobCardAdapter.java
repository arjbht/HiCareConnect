package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/26/2019.
 */
public class RecyclerViewJobCardAdapter extends RecyclerView.Adapter<RecyclerViewJobCardAdapter.RecyclerViewHolder> {

    private List<Service_Details> items;
    private Activity context;
    private static ClickListener clickListener;

    public RecyclerViewJobCardAdapter(FragmentActivity activity) {
        if(items == null){
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewJobCardAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jobcard_adapter,
                viewGroup, false);
        return new RecyclerViewJobCardAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewJobCardAdapter.RecyclerViewHolder holder, int position) {

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
        @BindView(R.id.lnrMenu)
        LinearLayout lnrMenu;

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

//    public void setOnItemClickListener(ClickListener clickListener) {
//        RecyclerViewJobCardAdapter.clickListener = clickListener;
//    }

    public void setOnMenuItemClickListener(ClickListener clickListener) {
        RecyclerViewJobCardAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}


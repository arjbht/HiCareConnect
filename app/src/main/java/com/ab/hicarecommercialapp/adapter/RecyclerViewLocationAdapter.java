package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/23/2019.
 */
public class RecyclerViewLocationAdapter extends RecyclerView.Adapter<RecyclerViewLocationAdapter.RecyclerViewHolder> {

    private List<Branch> items;
    private Activity context;
    private static RecyclerViewLocationAdapter.ClickListener clickListener;

    public RecyclerViewLocationAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewLocationAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_location_adapter,
                viewGroup, false);
        return new RecyclerViewLocationAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewLocationAdapter.RecyclerViewHolder holder, int position) {
        holder.txtBranch.setText(items.get(position).getAccountName());
        holder.txtAddress.setText(items.get(position).getBillingStreet());
    }

    public void setData(List<Branch> data) {
        items.clear();
        items.addAll(data);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtBranch)
        TextView txtBranch;

        @BindView(R.id.txtAddress)
        TextView txtAddress;

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
        RecyclerViewLocationAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}



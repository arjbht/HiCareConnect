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
import com.ab.hicarecommercialapp.model.invoice.Invoices;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/23/2019.
 */
public class RecyclerViewInvoiceAdapter extends RecyclerView.Adapter<RecyclerViewInvoiceAdapter.RecyclerViewHolder> {

    private List<Invoices> items;
    private Activity context;
    private static ClickListener clickListener;

    public RecyclerViewInvoiceAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewInvoiceAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_invoice_adapter,
                viewGroup, false);
        return new RecyclerViewInvoiceAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewInvoiceAdapter.RecyclerViewHolder holder, int position) {
        holder.txtInvoiceNo.setText("Invoice  #" + items.get(position).getSFDCInvoiceNumberC());
        holder.txtInvoiceDate.setText(items.get(position).getInvoiceDateText());
        holder.txtInvoiceAmount.setText("\u20B9" + items.get(position).getNetCostC());
    }

    public void setData(List<Invoices> data) {
        items.clear();
        items.addAll(data);
    }

    public void addData(List<Invoices> data) {
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
        @BindView(R.id.txtInvoiceNo)
        TextView txtInvoiceNo;

        @BindView(R.id.txtInvoiceDate)
        TextView txtInvoiceDate;

        @BindView(R.id.txtInvoiceAmount)
        TextView txtInvoiceAmount;


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
        RecyclerViewInvoiceAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}


package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 10/23/2019.
 */
public class RecyclerViewPaymentAdapter extends RecyclerView.Adapter<RecyclerViewPaymentAdapter.RecyclerViewHolder> {

    private List<Service_Details> items;
    private Activity context;
//    private static RecyclerViewOrderServicesAdapter.ClickListener clickListener;

    public RecyclerViewPaymentAdapter( FragmentActivity activity) {
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewPaymentAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_payment_adapter,
                viewGroup, false);
        return new RecyclerViewPaymentAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPaymentAdapter.RecyclerViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            btnView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            clickListener.onClick(v, getAdapterPosition());
        }
    }

//    public void setOnItemClickListener(RecyclerViewOrderServicesAdapter.ClickListener clickListener) {
//        RecyclerViewOrderServicesAdapter.clickListener = clickListener;
//    }


//    public interface ClickListener {
//        void onClick(View view, int position);
//    }
}


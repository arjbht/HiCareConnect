package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.complaint.Attachment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 11/6/2019.
 */
public class RecyclerViewComplaintGalleryAdapter extends RecyclerView.Adapter<RecyclerViewComplaintGalleryAdapter.RecyclerViewHolder> {

    private List<Attachment> items;
    private Activity context;
    private static RecyclerViewComplaintGalleryAdapter.ClickListener clickListener;

    public RecyclerViewComplaintGalleryAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewComplaintGalleryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complaint_gallery,
                viewGroup, false);

        return new RecyclerViewComplaintGalleryAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewComplaintGalleryAdapter.RecyclerViewHolder holder, int position) {
        if (items.get(position).getAttachmentUrl() != null) {
            Picasso.get().load(items.get(position).getAttachmentUrl()).into(holder.imageView);
        }
    }

    public void setData(List<Attachment> data) {
        items.clear();
        items.addAll(data);
    }

    public void addData(List<Attachment> data) {
        items.addAll(data);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.img)
        ImageView imageView;

        @BindView(R.id.lnrHighlight)
        LinearLayout lnrHighlight;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition(), itemView);
        }
    }


    public void setOnImageClickListener(RecyclerViewComplaintGalleryAdapter.ClickListener clickListener) {
        RecyclerViewComplaintGalleryAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position, View bgHighlight);
    }
}


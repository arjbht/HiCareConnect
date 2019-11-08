package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.ab.hicarecommercialapp.R;
import com.devs.vectorchildfinder.VectorChildFinder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/24/2019.
 */
public class RecyclerViewNotificationAdapter extends RecyclerView.Adapter<RecyclerViewNotificationAdapter.RecyclerViewHolder> {

    //    private List<Categories.Category> categories;
    private Activity context;

    //    private static ClickListener clickListener;
    public RecyclerViewNotificationAdapter(/*List<Categories.Category> categories,*/  FragmentActivity activity) {
//        this.categories = categories;
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
//        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_notify_bell);
//        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
//
//        if (position % 7 == 0) {
//            DrawableCompat.setTint(wrappedDrawable, Color.YELLOW);
//        } else if (position % 7 == 1) {
//            DrawableCompat.setTint(wrappedDrawable, Color.BLUE);
//        } else if (position % 7 == 2) {
//            DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#E56B00"));
//        } else if (position % 7 == 3) {
//            DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#F4A200"));
//        } else if (position % 7 == 4) {
//            DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#78008A"));
//        } else if (position % 7 == 5) {
//            DrawableCompat.setTint(wrappedDrawable, Color.YELLOW);
//        } else if (position % 7 == 6) {
//            DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#E56B00"));
//        }
    }


    @Override
    public int getItemCount() {
        return 8;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgNotification)
        ImageView imgNotification;

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


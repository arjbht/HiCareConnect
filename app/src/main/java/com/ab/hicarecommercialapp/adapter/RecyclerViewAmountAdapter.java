package com.ab.hicarecommercialapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;

import butterknife.ButterKnife;

/**
 * Created by Arjun Bhatt on 9/20/2019.
 */
public class RecyclerViewAmountAdapter extends RecyclerView.Adapter<RecyclerViewAmountAdapter.RecyclerViewHolder> {

    //    private List<Categories.Category> categories;
    private Activity context;
//    private static RecyclerViewAmountAdapter.ClickListener clickListener;

    public RecyclerViewAmountAdapter(/*List<Categories.Category> categories,*/ FragmentActivity activity) {
//        this.categories = categories;
        this.context = activity;
    }

    @NonNull
    @Override
    public RecyclerViewAmountAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_amount_adapter,
                viewGroup, false);
        return new RecyclerViewAmountAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAmountAdapter.RecyclerViewHolder viewHolder, int i) {

//        String strCategoryThum = categories.get(i).getStrCategoryThumb();
//        Picasso.get().load(strCategoryThum).placeholder(R.drawable.ic_circle).into(viewHolder.categoryThumb);
//
//        String strCategoryName = categories.get(i).getStrCategory();
//        viewHolder.categoryName.setText(strCategoryName);
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        @BindView(R.id.categoryThumb)
//        ImageView categoryThumb;
//        @BindView(R.id.categoryName)
//        TextView categoryName;
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
//        RecyclerViewAmountAdapter.clickListener = clickListener;
//    }


//    public interface ClickListener {
//        void onClick(View view, int position);
//    }
}



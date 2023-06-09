package com.example.medipharm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medipharm.Domain.CategoryDomain;
import com.example.medipharm.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryDomain> categoryDomains;
    private Context context;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains, Context context) {
        this.categoryDomains = categoryDomains;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl = "";

        switch (position) {
            case 0:
                picUrl = "pills1";
                holder.mainlayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.pill_background1));
                break;
            case 1:
                picUrl = "pills2";
                holder.mainlayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.pill_background2));
                break;
            case 2:
                picUrl = "pills3";
                holder.mainlayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.pill_background3));
                break;
            case 3:
                picUrl = "pills4";
                holder.mainlayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.pill_background4));
                break;
            case 4:
                picUrl = "pills5";
                holder.mainlayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.pill_background5));
                break;
        }

        int drawableResourceId = context.getResources().getIdentifier(picUrl, "drawable", context.getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainlayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

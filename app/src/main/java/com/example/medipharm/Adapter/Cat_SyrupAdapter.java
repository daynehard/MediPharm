package com.example.medipharm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medipharm.Activity.ShowDetailsActivity;
import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.R;

import java.util.ArrayList;

public class Cat_SyrupAdapter extends RecyclerView.Adapter<Cat_SyrupAdapter.ViewHolder> {
    private ArrayList<DrugDomain> popularDrug;
    private Context context;

    public Cat_SyrupAdapter(ArrayList<DrugDomain> popularDrug, Context context) {
        this.popularDrug = popularDrug;
        this.context = context;
    }

    public void setData(ArrayList<DrugDomain> newData) {
        this.popularDrug = newData;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_syrup, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (popularDrug != null && position >= 0 && position < popularDrug.size()) {
            holder.title.setText(popularDrug.get(position).getTitle());
            holder.fee.setText(String.valueOf(popularDrug.get(position).getFee()));

            int drawableResourceId = context.getResources().getIdentifier(popularDrug.get(position).getPic(), "drawable", context.getPackageName());

            Glide.with(context)
                    .load(drawableResourceId)
                    .into(holder.pic);

            holder.addbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                    intent.putExtra("object", popularDrug.get(position));
                    holder.itemView.getContext().startActivity(intent);
                }
            });
            holder.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                    intent.putExtra("object", popularDrug.get(position));
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return popularDrug != null ? popularDrug.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        TextView addbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addbtn = itemView.findViewById(R.id.addbtn);
        }
    }
}

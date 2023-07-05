package com.example.medipharm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.Helper.ManagementCart;
import com.example.medipharm.Interface.ChangeNumberItemsListener;
import com.example.medipharm.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<DrugDomain> drugDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<DrugDomain> drugDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.drugDomains = drugDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(drugDomains.get(holder.getAdapterPosition()).getTitle());
        holder.feeEachItem.setText(String.valueOf(drugDomains.get(holder.getAdapterPosition()).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((drugDomains.get(holder.getAdapterPosition()).getNumberInCart() * drugDomains.get(holder.getAdapterPosition()).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(drugDomains.get(holder.getAdapterPosition()).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(drugDomains.get(holder.getAdapterPosition()).getPic(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberDrug(drugDomains, holder.getAdapterPosition(), new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberDrug(drugDomains, holder.getAdapterPosition(), new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return drugDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
        }
    }
}

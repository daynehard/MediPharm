package com.example.medipharm.Adapter;

import android.content.Context;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<DrugDomain> listDrugSelected;
    private Context context;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<DrugDomain> listDrugSelected, Context context,
                           ChangeNumberItemsListener changeNumberItemsListener, ManagementCart managementCart) {
        this.listDrugSelected = listDrugSelected;
        this.context = context;
        this.changeNumberItemsListener = changeNumberItemsListener;
        this.managementCart = managementCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrugDomain item = listDrugSelected.get(position);

        holder.title.setText(item.getTitle());
        holder.feeEachItem.setText("ksh" + item.getFee());
        holder.totalEachItem.setText("ksh" + Math.round(item.getNumberInCart() * item.getFee()));
        holder.num.setText(String.valueOf(item.getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(item.getPic(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(view -> managementCart.plusNumberDrug(listDrugSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

        holder.minusItem.setOnClickListener(v -> {
            int currentNumber = item.getNumberInCart();
            if (currentNumber > 1) {
                managementCart.minusNumberDrug(listDrugSelected, position, () -> {
                    notifyDataSetChanged();
                    changeNumberItemsListener.changed();
                });
            } else {
                // If the number is less than or equal to 1, remove the item from the cart
                deleteCartItem(item);
            }
        });
    }

    private void deleteCartItem(DrugDomain item) {
        String itemId = item.getItemId();

        // Check if the item has a valid itemId
        if (itemId != null && !itemId.isEmpty()) {
            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("cart_items");
            cartRef.child(itemId).removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            // Item deleted successfully from the database
                            // Now remove the item from the local list and notify the adapter
                            listDrugSelected.remove(item);
                            notifyDataSetChanged();
                            // Recalculate the cart total after item removal
                            changeNumberItemsListener.changed();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("CartListAdapter", "Failed to delete cart item: " + e.getMessage());
                        }
                    });
        } else {
            Log.e("CartListAdapter", "Failed to delete cart item: ItemId is null or empty");
        }
    }


    @Override
    public int getItemCount() {
        return listDrugSelected.size();
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

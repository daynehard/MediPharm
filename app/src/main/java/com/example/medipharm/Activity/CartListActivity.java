package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.medipharm.Adapter.CartListAdapter;
import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.Helper.ManagementCart;
import com.example.medipharm.Interface.ChangeNumberItemsListener;
import com.example.medipharm.R;

public class CartListActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
private ManagementCart managementCart;
TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
private double tax;
private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        LinearLayout profilBtn = findViewById(R.id.profilbtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,Profile.class));
            }
        });
    }
    private void initView() {
        recyclerViewList = findViewById(R.id.cartView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList = findViewById(R.id.recyclerView);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList = findViewById(R.id.recyclerView);

        if (recyclerViewList == null) {
            // Handle the case where the RecyclerView is not found in the layout
            // You might want to check the correct ID or the layout's structure
            Log.e("CartListActivity", "RecyclerView is null");
            return;
        }
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            // Output the items added to cart
            StringBuilder itemsText = new StringBuilder();
            for (DrugDomain drug : managementCart.getListCart()) {
                itemsText.append(drug.getTitle()).append(", ");
            }
            // Remove the trailing comma and space
            if (itemsText.length() > 2) {
                itemsText.setLength(itemsText.length() - 2);
            }
            // Display the items in a TextView or logcat
            Log.d("CartListActivity", "Items in cart: " + itemsText.toString());
            // Alternatively, you can set the items text to a TextView if you have one
            // itemsTextView.setText(itemsText.toString());
        }
    }

    private void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double total = Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("ksh"+itemTotal);
        taxTxt.setText("ksh"+tax);
        deliveryTxt.setText("ksh"+delivery);
        totalTxt.setText("ksh"+total);
    }
}
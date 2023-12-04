package com.example.medipharm.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.Helper.ManagementCart;
import com.example.medipharm.R;

public class CatDetails extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, howtouseTxt;
    private ImageView plusBtn, minusBtn, picDet;
    private DrugDomain object;
    int numberOrder = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        managementCart = new ManagementCart(this);
        initview();
        getBundle();
    }

    private void getBundle() {
        object= (DrugDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId= this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picDet);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("ksh"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        howtouseTxt.setText(object.getHowtouse());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder= numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder>1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertDrug(object);
            }
        });
    }

    private void initview() {
        addToCartBtn= findViewById(R.id.addToCartBtn);
        titleTxt= findViewById(R.id.titleTxt);
        feeTxt= findViewById(R.id.priceTxt);
        descriptionTxt= findViewById(R.id.descriptionTxt);
        howtouseTxt = findViewById(R.id.howtouseTxt);
        numberOrderTxt= findViewById(R.id.numberOrderTxt);
        plusBtn= findViewById(R.id.plusBtn);
        minusBtn= findViewById(R.id.minusBtn);
        picDet= findViewById(R.id.picCat);
    }
}
package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medipharm.Domain.VitaminsDomain;
import com.example.medipharm.Helper.ManagementCart;
import com.example.medipharm.Helper.ManagerVitamin;
import com.example.medipharm.R;

public class ShowCategoryDetails extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, howtouseTxt;
    private ImageView plusBtn, minusBtn, picVit;
    private VitaminsDomain object;
    int numberOrder = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category_details);

        managementCart = new ManagementCart(this);
        initview();
        getBundle();
    }

    private void getBundle() {
        object= (VitaminsDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId= this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picVit);

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
                ManagerVitamin.insertVit(object);
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
        picVit= findViewById(R.id.picdrug);
    }
}
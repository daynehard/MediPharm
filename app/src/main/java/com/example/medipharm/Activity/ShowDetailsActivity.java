package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.Helper.ManagementCart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.medipharm.R;

public class ShowDetailsActivity extends AppCompatActivity {
private TextView addtoCartBtn;
private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, howtouseTxt, totalPriceTxt;
private ImageView plusBtn, minusBtn, picDrug;
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
                .into(picDrug);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("ksh"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        howtouseTxt.setText(object.getHowtouse());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        totalPriceTxt.setText("ksh"+numberOrder * object.getFee());

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder= numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText("ksh"+numberOrder * object.getFee());
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder>1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText("ksh"+numberOrder * object.getFee());
            }
        });

        addtoCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertDrug(object);

                // Get a reference to the Realtime Database
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                // Define the node where you want to store the cart items (e.g., "cart_items")
                String cartNode = "cart_items";

                // Push the cart item data to the Realtime Database
                String cartItemId = databaseReference.child(cartNode).push().getKey();
                databaseReference.child(cartNode).child(cartItemId).setValue(object);

                // Display a confirmation message or handle any other logic here
            }
        });
    }

    private void initview() {
        addtoCartBtn= findViewById(R.id.addtoCartBtn);
        titleTxt= findViewById(R.id.titletxt);
        feeTxt= findViewById(R.id.pricetxt);
        descriptionTxt= findViewById(R.id.descriptiontxt);
        howtouseTxt = findViewById(R.id.howtousetxt);
        numberOrderTxt= findViewById(R.id.numberItemtxt);
        plusBtn= findViewById(R.id.plusCartBtn2);
        minusBtn= findViewById(R.id.minusCartBtn2);
        picDrug= findViewById(R.id.picdrug);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
    }
}
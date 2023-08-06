package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.medipharm.Fragments.DevicesFragment;
import com.example.medipharm.Fragments.PainReliefFragment;
import com.example.medipharm.Fragments.SyrupsFragment;
import com.example.medipharm.Fragments.VaccinesFragment;
import com.example.medipharm.Fragments.VitaminsFragment;
import com.example.medipharm.R;

public class ShowCategory extends AppCompatActivity {

    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category);

        btnback = findViewById(R.id.back_pressed);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowCategory.this, MainActivity.class));
            }
        });

        RelativeLayout vitamins = findViewById(R.id.vitamins);
        RelativeLayout pain = findViewById(R.id.pain);
        RelativeLayout vaccine = findViewById(R.id.vaccin);
        RelativeLayout device = findViewById(R.id.device);
        RelativeLayout syrup = findViewById(R.id.syrup);


        vitamins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, VitaminsFragment.class));
            }
        });
        pain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, PainReliefFragment.class));
            }
        });
        vaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, VaccinesFragment.class));
            }
        });
        device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, DevicesFragment.class));
            }
        });
        syrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, SyrupsFragment.class));
            }
        });
    }
}
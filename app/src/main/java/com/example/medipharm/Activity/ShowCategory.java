package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

        Button expandvits = findViewById(R.id.expandvitamins);
        Button expandpain = findViewById(R.id.expandpain);
        Button expandvac = findViewById(R.id.expandvaccine);
        Button expanddev = findViewById(R.id.expanddevice);
        Button expandsyrup = findViewById(R.id.expandsyrup);


        expandvits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, VitaminsFragment.class));
            }
        });
        expandpain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, PainReliefFragment.class));
            }
        });
        expandvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, VaccinesFragment.class));
            }
        });
        expanddev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, DevicesFragment.class));
            }
        });
        expandsyrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowCategory.this, SyrupsFragment.class));
            }
        });
    }
}
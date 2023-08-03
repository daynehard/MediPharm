package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.medipharm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigation();
    }
        private void bottomNavigation() {
            LinearLayout homeBtn = findViewById(R.id.homebtn);
            FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
            LinearLayout supportBtn = findViewById(R.id.supportbtn);
            LinearLayout settingsBtn = findViewById(R.id.settingsbtn);


            homeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Profile.this, MainActivity.class));
                }
            });
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Profile.this,CartListActivity.class));
                }
            });
            supportBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Profile.this,Support.class));
                }
            });
            settingsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Profile.this,Settings.class));
                }
            });
        }

    }




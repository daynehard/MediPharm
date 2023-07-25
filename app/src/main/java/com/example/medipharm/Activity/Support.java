package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.medipharm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Support extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        bottomNavigation();
    }
    private void bottomNavigation(){
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        LinearLayout profileBtn = findViewById(R.id.profilbtn);
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout settingsBtn = findViewById(R.id.settingsbtn);


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Support.this, MainActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, MainActivity.class));
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Support.this,CartListActivity.class));
            }
        });
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this,Settings.class));
            }
        });
    }
}
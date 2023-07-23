package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.medipharm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigation();
    }
    private void bottomNavigation(){
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);


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
    }
}
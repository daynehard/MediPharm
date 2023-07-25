package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.medipharm.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// BaseActivity.java
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_bottom_nav);

        // Set up the static bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            // Handle bottom navigation item clicks here
            switch (item.getItemId()) {
                case R.id.home:
                    // Start the HomeActivity
                    startActivity(new Intent(this, MainActivity.class));
                    return false;
                case R.id.profile:
                    // Start the ProfileActivity
                    startActivity(new Intent(this, Profile.class));
                    return false;
                case R.id.cart:
                    // Start the ProfileActivity
                    startActivity(new Intent(this, CartListActivity.class));
                    return false;
                case R.id.support:
                    // Start the ProfileActivity
                    startActivity(new Intent(this, Support.class));
                    return false;
                case R.id.setting:
                    // Start the SettingsActivity
                    startActivity(new Intent(this, Settings.class));
                    return false;
            }
            return false;
        });
    }
}

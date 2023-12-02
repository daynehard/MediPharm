package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.medipharm.R;

public class HealthDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);

        final Button myButton = findViewById(R.id.savebtn);

        // Set the animation
        final Animation buttonPressAnimation = AnimationUtils.loadAnimation(this, R.anim.button_press_animation);

        // Set a listener for the button press
        myButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Start the animation when the button is pressed
                    myButton.startAnimation(buttonPressAnimation);
                }
                return false;
            }
        });

        ImageView back = findViewById(R.id.back_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthDetails.this, Profile.class));
            }
        });
    }
}
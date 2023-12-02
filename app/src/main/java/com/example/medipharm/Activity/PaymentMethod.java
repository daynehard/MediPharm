package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.medipharm.R;

public class PaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        Button button1 = findViewById(R.id.paypal);
        Button button2 = findViewById(R.id.mpesa);

        // Apply button press animation to each button
        applyButtonPressAnimation(button1);
        applyButtonPressAnimation(button2);
    }

    private void applyButtonPressAnimation(final Button button) {
        final Animation buttonPressAnimation = AnimationUtils.loadAnimation(this, R.anim.button_press_animation);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Start the animation when the button is pressed
                    button.startAnimation(buttonPressAnimation);
                }
                return false;
            }
        });

        Button Paypal = findViewById(R.id.paypal);
        Button Mpesa = findViewById(R.id.mpesa);

        Paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentMethod.this, PayPalCheckoutActivity.class));
            }
        });
        Mpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentMethod.this, MpesaCheckout.class));
            }
        });

    }
}
package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.medipharm.R;

public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;
    Animation topAnim;
    ImageView splashimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);

        splashimage = findViewById(R.id.imagesplash);

        splashimage.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, AIntroActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity to prevent going back to the splash screen
            }
        }, SPLASH_SCREEN);

    }
}
package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.medipharm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigation();

        ImageView imageView = findViewById(R.id.imageView4);
        AppCompatButton chooseImage = findViewById(R.id.chooseImageBtn);
        AppCompatButton takePhoto = findViewById(R.id.takePhotoBtn);

        final ImageChooser imageChooser = new ImageChooser(this, getActivityResultRegistry());

        getLifecycle().addObserver(imageChooser);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser.selectImages(new ImagesSelectedListener() {
                    @Override
                    public void images(List<Bitmap> bitmaps, List<Uri> uris, int selectedImagesCount) {
                        if (selectedImagesCount > 0) {
                            imageView.setImageBitmap(bitmaps.get(0));
                        }
                    }
                });
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser.takeImage(new ImagesSelectedListener() {
                    @Override
                    public void images(List<Bitmap> bitmaps, List<Uri> uris, int selectedImagesCount) {
                        if (selectedImagesCount > 0) {
                            imageView.setImageBitmap(bitmaps.get(0));
                        }
                    }
                });
            }
     });
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







package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.medipharm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigation();

        ImageView profileImage = findViewById(R.id.profileImage);
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
                            Bitmap circularBitmap = getCircularBitmap(bitmaps.get(0));
                            profileImage.setImageBitmap(circularBitmap);
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
                            Bitmap circularBitmap = getCircularBitmap(bitmaps.get(0));
                            profileImage.setImageBitmap(circularBitmap);
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
                startActivity(new Intent(Profile.this, CartListActivity.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Support.class));
            }
        });
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Settings.class));
            }
        });
    }

    private Bitmap getCircularBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int diameter = Math.min(width, height);

        Bitmap output = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, diameter, diameter);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(diameter / 2f, diameter / 2f, diameter / 2f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, new Rect(width / 2 - diameter / 2, height / 2 - diameter / 2,
                width / 2 + diameter / 2, height / 2 + diameter / 2), rect, paint);

        return output;
    }
}

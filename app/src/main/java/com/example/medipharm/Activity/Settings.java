package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.example.medipharm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {

    private ImageView profileImage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ImageView profileImage = findViewById(R.id.imageprofile); // Replace with the ID of the ImageView in activity_settings.xml

        AppCompatButton edit = findViewById(R.id.edit);
        ImageView back = findViewById(R.id.back_btn);
        RelativeLayout account = findViewById(R.id.accbtn);
        RelativeLayout privacy = findViewById(R.id.privacybtn);
        RelativeLayout help = findViewById(R.id.helpbtn);
        RelativeLayout about = findViewById(R.id.aboutbtn);
        RelativeLayout terms = findViewById(R.id.termsbtn);
        RelativeLayout logout = findViewById(R.id.logoutbtn);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, Profile.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, MainActivity.class));
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, Support.class));
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, terms_and_conditions.class));
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, LoginActivity.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, About.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Settings.this, AIntroActivity.class));
            }
        });

        // Load and set the profile image
        loadProfileImage(profileImage);

        // Set click listeners for buttons and other views...

    }

    private void loadProfileImage(ImageView profileImage) {
        DatabaseReference userRef = databaseReference.child("users").child(currentUser.getUid());
        userRef.child("profileImageUrl").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imageUrl = dataSnapshot.getValue(String.class);
                if (imageUrl != null) {
                    // Load the profile image using Glide
                    Glide.with(Settings.this)
                            .load(imageUrl)
                            .into(profileImage);
                } else {
                    // Set the default profile image using Glide
                    Glide.with(Settings.this)
                            .load(R.drawable.default_profile_image) // Replace with your default image resource
                            .into(profileImage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any error that occurs while loading the image
                // Set the default profile image using Glide
                Glide.with(Settings.this)
                        .load(R.drawable.default_profile_image) // Replace with your default image resource
                        .into(profileImage);
            }
        });
    }


    private void loadProfileImageWithGlide(String imageUrl) {
        // Replace this with your actual implementation of loading the image using Glide or any other library
        // For example, if you are using Glide:
        // Glide.with(this).load(imageUrl).into(profileImage);
    }
}

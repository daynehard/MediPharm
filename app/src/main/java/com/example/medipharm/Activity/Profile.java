package com.example.medipharm.Activity;

import androidx.annotation.NonNull;
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
import android.widget.RelativeLayout;

import com.example.medipharm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private CircleImageView profileImage;
    private Bitmap circularBitmap;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore firestore;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigation();


        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        ImageView profileImage = findViewById(R.id.profileImage);
        AppCompatButton chooseImage = findViewById(R.id.chooseImageBtn);
        AppCompatButton takePhoto = findViewById(R.id.takePhotoBtn);
        RelativeLayout edituser = findViewById(R.id.userbtn);
        RelativeLayout shipping = findViewById(R.id.deliverybtn);
        RelativeLayout order = findViewById(R.id.orderbtn);
        RelativeLayout payment = findViewById(R.id.cardsbtn);

        shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, ShippingAddress.class));
            }
        });
        edituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, HealthDetails.class));
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MpesaCheckout.class));
            }
        });


        final ImageChooser imageChooser = new ImageChooser(this, getActivityResultRegistry());

        getLifecycle().addObserver(imageChooser);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser.selectImages(new ImagesSelectedListener() {
                    @Override
                    public void images(List<Bitmap> bitmaps, List<Uri> uris, int selectedImagesCount) {
                        if (selectedImagesCount > 0) {
                            circularBitmap = getCircularBitmap(bitmaps.get(0));
                            profileImage.setImageBitmap(circularBitmap);
                            saveImageToFirebase();
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
                            circularBitmap = getCircularBitmap(bitmaps.get(0));
                            profileImage.setImageBitmap(circularBitmap);
                            saveImageToFirebase();
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
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawCircle(width / 2f, height / 2f, width / 2f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return output;
    }

    private void saveImageToFirebase() {
        if (circularBitmap != null) {
            // Convert the Bitmap to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            circularBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();

            // Upload the byte array to Firebase Storage
            StorageReference profileImageRef = storageReference.child("profile_images")
                    .child(currentUser.getUid() + ".png");

            UploadTask uploadTask = profileImageRef.putBytes(data);
            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        // Image uploaded successfully, get the download URL
                        profileImageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    // Save the download URL to Firebase Realtime Database
                                    Uri downloadUrl = task.getResult();
                                    saveImageUrlToRealtimeDatabase(downloadUrl.toString());
                                } else {
                                    // Handle the failure case here
                                }
                            }
                        });
                    } else {
                        // Handle the failure case here
                    }
                }
            });
        }
    }

    private void saveImageUrlToRealtimeDatabase(String imageUrl) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = databaseReference.child("users").child(currentUser.getUid());
        userRef.child("profileImageUrl").setValue(imageUrl)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Image URL saved to Realtime Database successfully
                        } else {
                            // Handle the failure case here
                        }
                    }
                });
    }

}
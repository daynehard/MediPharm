package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.medipharm.R;

public class Support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);


        ImageView back = findViewById(R.id.back_btn);
        RelativeLayout whatsapp = findViewById(R.id.whatsapp);
        RelativeLayout email = findViewById(R.id.email);
        RelativeLayout sms = findViewById(R.id.sms);
        RelativeLayout about = findViewById(R.id.about);
        RelativeLayout terms = findViewById(R.id.termsbt);
        RelativeLayout instagram = findViewById(R.id.instagram);
        RelativeLayout settings = findViewById(R.id.settingsbt);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, MainActivity.class));
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp("+254798890551");
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGmail(new String[]{"iandeinhard254@gmail.com"}, "", "");

            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSMS("+254798890551", "Hello, this is a sample SMS message.");
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagram("medip_harm");
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, About.class));
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, terms_and_conditions.class));
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, Settings.class));
            }
        });

    }
    private void openWhatsApp(String contactNumber) {
        String url = "https://api.whatsapp.com/send?phone=" + contactNumber;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.setPackage("com.whatsapp");
        startActivity(intent);
    }

    private void openGmail(String[] emailIds, String subject, String body) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, emailIds);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("message/rfc822");
        intent.setPackage("com.google.android.gm");
        startActivity(intent);
    }

    private void openSMS(String phoneNumber, String message) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", message);

        // Check if the device has an SMS app that can handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If no SMS app is found, display a message to the user
            Toast.makeText(this, "No SMS app found.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openInstagram(String username) {
        String instagramUrl = "https://www.instagram.com/" + username;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));

        // Check if the device has the Instagram app installed
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If the Instagram app is not installed, open Instagram's website in a browser
            String instagramWebUrl = "https://www.instagram.com/" + username;
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramWebUrl));
            startActivity(webIntent);
        }
    }



}
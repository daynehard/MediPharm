package com.example.medipharm.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medipharm.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.*;

public class MpesaCheckout extends AppCompatActivity {

    private double totalAmount;
    private static final String BASE_URL = "https://sandbox.safaricom.co.ke";
    private static final String CONSUMER_KEY = "O61JglK7xDwqrniAnMdCDIChG19yS0lA";
    private static final String CONSUMER_SECRET = "snTelHPGXAMDNAgm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa_checkout);

        Button button1 = findViewById(R.id.buttonInitiatePayment);
        applyButtonPressAnimation(button1);

        totalAmount = getIntent().getDoubleExtra("TOTAL_AMOUNT", 0.0);

        TextView textViewAmount = findViewById(R.id.textViewAmount);
        Button buttonInitiatePayment = findViewById(R.id.buttonInitiatePayment);

        textViewAmount.setText("ksh " + totalAmount);

        buttonInitiatePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MpesaPaymentTask().execute();
            }
        });
    }

    private void applyButtonPressAnimation(final Button button) {
        final Animation buttonPressAnimation = AnimationUtils.loadAnimation(this, R.anim.button_press_animation);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button.startAnimation(buttonPressAnimation);
                }
                return false;
            }
        });
    }

    private class MpesaPaymentTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            String accessToken = getAccessToken(CONSUMER_KEY, CONSUMER_SECRET);
            if (accessToken != null) {
                return initiateMpesaPayment(totalAmount, accessToken);
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(MpesaCheckout.this, "Payment initiated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MpesaCheckout.this, "Payment initiation failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean initiateMpesaPayment(double amount, String accessToken) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        // Replace these placeholders with your actual values
        String shortcode = "YourShortcode";
        String lipaNaMpesaOnlinePasskey = "YourPasskey";
        String lipaNaMpesaOnlineCallbackUrl = "YourCallbackUrl";

        String requestBody = "{"
                + "\"BusinessShortCode\": \"" + shortcode + "\","
                + "\"Password\": \"" + generatePassword(CONSUMER_KEY, CONSUMER_SECRET, lipaNaMpesaOnlinePasskey) + "\","
                + "\"Timestamp\": \"" + generateTimestamp() + "\","
                + "\"TransactionType\": \"CustomerPayBillOnline\","
                + "\"Amount\": \"" + amount + "\","
                + "\"PartyA\": \"YourPhoneNumber\","
                + "\"PartyB\": \"" + shortcode + "\","
                + "\"PhoneNumber\": \"YourPhoneNumber\","
                + "\"CallBackURL\": \"" + lipaNaMpesaOnlineCallbackUrl + "\","
                + "\"AccountReference\": \"MediPharm\","
                + "\"TransactionDesc\": \"MediPharm Payment\""
                + "}";

        RequestBody body = RequestBody.create(mediaType, requestBody);

        Request request = new Request.Builder()
                .url(BASE_URL + "/mpesa/stkpush/v1/processrequest")
                .post(body)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.d("MpesaCheckout", "Response Code: " + response.code());
            Log.d("MpesaCheckout", "Response Body: " + response.body().string());
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MpesaCheckout", "Network error: " + e.getMessage());
            return false;
        }
    }

    private String generatePassword(String consumerKey, String consumerSecret, String lipaNaMpesaOnlinePasskey) {
        return Base64.encodeToString((consumerKey + ":" + consumerSecret + ":" + lipaNaMpesaOnlinePasskey).getBytes(), Base64.NO_WRAP);
    }

    private String generateTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    private String getAccessToken(String consumerKey, String consumerSecret) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .method("GET", null)
                .addHeader("Authorization", "Basic cFJZcjZ6anEwaThMMXp6d1FETUxwWkIzeVBDa2hNc2M6UmYyMkJmWm9nMHFRR2xWOQ==")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d("MpesaCheckout", "Raw Response: " + responseBody);
                return extractAccessToken(responseBody);
            } else {
                Log.e("MpesaCheckout", "Non-successful response: " + response.code());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MpesaCheckout", "Error obtaining access token: " + e.getMessage());
            return null;
        }
    }

    private String extractAccessToken(String response) {
        String[] parts = response.split(":");
        if (parts.length == 2) {
            return parts[1];
        } else {
            return null;
        }
    }
}

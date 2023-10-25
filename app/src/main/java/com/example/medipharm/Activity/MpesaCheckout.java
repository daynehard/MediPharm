package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medipharm.R;
import okhttp3.*;

public class MpesaCheckout extends AppCompatActivity {

    private EditText phoneEditText, amountEditText;

    private static final String BASE_URL = "https://sandbox.safaricom.co.ke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa_checkout);

        phoneEditText = findViewById(R.id.phoneno);
        amountEditText = findViewById(R.id.amount);

        Button mpesaPayButton = findViewById(R.id.mpesapay);
        mpesaPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get phone number and amount entered by the user
                String phoneNumber = phoneEditText.getText().toString();
                String amount = amountEditText.getText().toString();

                // Initiate M-Pesa payment using Daraja API
                initiateMpesaPayment(phoneNumber, amount);
            }
        });
    }

    private void initiateMpesaPayment(String phoneNumber, String amount) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = "{\"BusinessShortCode\": \"YOUR_SHORTCODE\", " +
                "\"Password\": \"YOUR_PASSWORD\", " +
                "\"Timestamp\": \"YOUR_TIMESTAMP\", " +
                "\"TransactionType\": \"CustomerPayBillOnline\", " +
                "\"Amount\": \"" + amount + "\", " +
                "\"PartyA\": \"" + phoneNumber + "\", " +
                "\"PartyB\": \"YOUR_SHORTCODE\", " +
                "\"PhoneNumber\": \"" + phoneNumber + "\", " +
                "\"CallBackURL\": \"YOUR_CALLBACK_URL\", " +
                "\"AccountReference\": \"MediPharm\", " +
                "\"TransactionDesc\": \"MediPharm Payment\"}";

        RequestBody body = RequestBody.create(mediaType, requestBody);

        Request request = new Request.Builder()
                .url(BASE_URL + "/mpesa/stkpush/v1/processrequest")
                .post(body)
                .addHeader("Authorization", "Bearer " + getAccessToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Handle successful payment initiation response
                Toast.makeText(this, "Payment initiated successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Handle payment initiation failure
                Toast.makeText(this, "Payment initiation failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle network or other errors
        }
    }

    private String getAccessToken() {
        // TODO: Implement your access token retrieval logic here
        return "YOUR_ACCESS_TOKEN";
    }
}

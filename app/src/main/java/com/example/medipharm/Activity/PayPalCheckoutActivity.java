package com.example.medipharm.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.Locale;

public class PayPalCheckoutActivity extends AppCompatActivity {

    private static final int PAYPAL_REQUEST_CODE = 7171;

    private TextView totalAmountTextView;
    private Button payButton;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("AZjlDQYKnDsiKHtOJ-1ZG8Lv5zKO2TGDKGvZ_PbHSWOdT8p7hlpIRq4S_mOJaasbdke7ZgBBMzU1ypzJ"); // Replace with your actual client ID

    private double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal_checkout2);

        totalAmount = getIntent().getDoubleExtra("TOTAL_AMOUNT", 0.0);

        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        payButton = findViewById(R.id.payButton);



        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });

        // Calculate and update the total amount from Firebase
        calculateTotalAmountFromFirebase();
    }

    private void calculateTotalAmountFromFirebase() {
        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("cart_items");

        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                totalAmount = 0.0; // Reset the totalAmount

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DrugDomain cartItem = snapshot.getValue(DrugDomain.class);

                    if (cartItem != null) {
                        totalAmount += cartItem.getFee();
                    }
                }

                // Update the total amount in the PayPalCheckoutActivity
                updateTotalAmountInUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle onCancelled
            }
        });
    }

    private void updateTotalAmountInUI() {
        // Update the totalAmountTextView or perform any other actions with the totalAmount
        totalAmountTextView.setText(String.format(Locale.getDefault(), "ksh%.2f", totalAmount));
    }

    private void processPayment() {
        PayPalPayment payPalPayment = new PayPalPayment(
                new BigDecimal(totalAmount),
                "USD",
                "Payment for your order",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        Toast.makeText(this, "Payment Successful!\n" + paymentDetails, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_SHORT).show();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Toast.makeText(this, "Invalid Payment", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}

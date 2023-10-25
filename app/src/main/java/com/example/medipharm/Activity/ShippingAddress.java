package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.medipharm.Domain.Address;
import com.example.medipharm.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShippingAddress extends AppCompatActivity {

    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);

        // Initialize Firebase Realtime Database
        databaseRef = FirebaseDatabase.getInstance().getReference();

        Button updateButton = findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddressToDatabase();
            }
        });
    }

    private void saveAddressToDatabase() {
        EditText countryEditText = findViewById(R.id.country);
        EditText countyEditText = findViewById(R.id.county);
        EditText constituencyEditText = findViewById(R.id.constituency);

        String country = countryEditText.getText().toString();
        String county = countyEditText.getText().toString();
        String constituency = constituencyEditText.getText().toString();

        Address address = new Address(country, county, constituency);

        // Generate a new unique key for the address entry
        String addressKey = databaseRef.child("addresses").push().getKey();

        // Save the address data to the Realtime Database
        databaseRef.child("addresses").child(addressKey).setValue(address)
                .addOnSuccessListener(aVoid -> {
                    // Address saved successfully
                    startActivity(new Intent(ShippingAddress.this, Profile.class));
                })
                .addOnFailureListener(e -> {
                    // Handle error
                });
    }
}

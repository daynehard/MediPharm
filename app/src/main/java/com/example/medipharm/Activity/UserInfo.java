package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.medipharm.Domain.Address;
import com.example.medipharm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInfo extends AppCompatActivity {

    private DatabaseReference databaseRef;

    private TextView countryTextView, countyTextView, constituencyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Initialize Firebase Realtime Database
        databaseRef = FirebaseDatabase.getInstance().getReference();

        countryTextView = findViewById(R.id.countryTextView);
        countyTextView = findViewById(R.id.countyTextView);
        constituencyTextView = findViewById(R.id.constituencyTextView);

        // Fetch user's saved information
        fetchUserAddressInfo();
    }

    private void fetchUserAddressInfo() {
        // Replace "userId" with the actual user ID you're using
        String userId = "addresses";

        databaseRef.child("addresses").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Address address = dataSnapshot.getValue(Address.class);
                    if (address != null) {
                        // Populate TextViews with address details
                        countryTextView.setText("Country: " + address.getCountry());
                        countyTextView.setText("County: " + address.getCounty());
                        constituencyTextView.setText("Constituency: " + address.getConstituency());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}

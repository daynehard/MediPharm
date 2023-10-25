package com.example.medipharm.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medipharm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ReauthenticationActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private Button reauthenticateButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reauthentication);

        passwordEditText = findViewById(R.id.editTextPassword);
        reauthenticateButton = findViewById(R.id.buttonReauthenticate);

        mAuth = FirebaseAuth.getInstance();

        reauthenticateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reauthenticateUser();
            }
        });
    }

    private void reauthenticateUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String newPassword = passwordEditText.getText().toString();
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), newPassword);

            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Re-authentication successful
                                Toast.makeText(ReauthenticationActivity.this, "Re-authentication successful", Toast.LENGTH_SHORT).show();
                                // Now you can perform actions like accessing the database
                            } else {
                                // Re-authentication failed
                                Toast.makeText(ReauthenticationActivity.this, "Re-authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}

package com.example.medipharm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medipharm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirm;
    private TextView terms;
    private Button login;
    private ConstraintLayout signupbtn;
    private ImageView google;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        pd = new ProgressDialog(CreateActivity.this);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        terms = findViewById(R.id.terms);
        login = findViewById(R.id.login);
        signupbtn = findViewById(R.id.signupbtn);
        google = findViewById(R.id.google);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Please wait...");
                pd.show();

                String regUsername = username.getText().toString().trim();
                String regemail = email.getText().toString().trim();
                String regpass = password.getText().toString().trim();
                String confpass = confirm.getText().toString().trim();

                if (TextUtils.isEmpty(regUsername) || TextUtils.isEmpty(regemail) ||
                        TextUtils.isEmpty(regpass) || TextUtils.isEmpty(confpass)) {
                    Toast.makeText(CreateActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    return;
                }

                register(regUsername, regemail, regpass, confpass);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this, LoginActivity.class));
            }
        });


        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateActivity.this, terms_and_conditions.class));
            }
        });
    }

    private void register(String username, String email, String password, String confirm) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(CreateActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("username", username);
                            // Add other user data to the hashMap as needed

                            userReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(CreateActivity.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(CreateActivity.this, "Failed to create account. Please try again later.", Toast.LENGTH_SHORT).show();
                                    }
                                    pd.dismiss();
                                }
                            });
                        } else {
                            Toast.makeText(CreateActivity.this, "Failed to create account. Please try again later.", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }
                });
    }

}

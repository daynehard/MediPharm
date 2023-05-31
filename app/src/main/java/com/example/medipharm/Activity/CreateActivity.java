package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medipharm.R;

public class
CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        TextView username =(TextView) findViewById(R.id.username);
        TextView email =(TextView) findViewById(R.id.email);
        TextView password =(TextView) findViewById(R.id.password);
        TextView confirm =(TextView) findViewById(R.id.confirm);
        Button login = (Button) findViewById(R.id.login);
        ConstraintLayout signupbtn = findViewById(R.id.signupbtn);
        ImageView google = findViewById(R.id.google);
        ImageButton backbtn1 = findViewById(R.id.backbtn1);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals("admin")&&confirm.getText().toString().equals("admin")){
                    startActivity(new Intent(CreateActivity.this,MainActivity.class));
                    Toast.makeText(CreateActivity.this, "Login Successful!!", Toast.LENGTH_SHORT).show();}
                else
                    Toast.makeText(CreateActivity.this, "Login Fail. Try again!!", Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this, LoginActivity.class));
            }
        });

        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this,MainActivity.class));
            }
        });

    }
}
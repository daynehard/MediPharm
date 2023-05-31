package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medipharm.R;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView username1 =(TextView) findViewById(R.id.username1);
        TextView password1 =(TextView) findViewById(R.id.password1);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        ImageButton backbtn2 = findViewById(R.id.backbtn2);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username1.getText().toString().equals("admin") && password1.getText().toString().equals("admin")){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    Toast.makeText(LoginActivity.this,"LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(LoginActivity.this,"LOGIN FAILED!!!", Toast.LENGTH_SHORT).show();
            }
        });
        backbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,CreateActivity.class));
            }
        });
    }
}
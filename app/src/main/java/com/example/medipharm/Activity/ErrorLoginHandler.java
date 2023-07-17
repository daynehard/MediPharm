package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.medipharm.R;

public class ErrorLoginHandler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_login_handler);

        TextView create_acc = findViewById(R.id.create_acc);
        TextView back_login = findViewById(R.id.back2login);
        Button send_link = findViewById(R.id.Send_link);
        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_acc = new Intent(ErrorLoginHandler.this, CreateActivity.class);
                startActivity(intent_acc);
            }
        });

    }
}
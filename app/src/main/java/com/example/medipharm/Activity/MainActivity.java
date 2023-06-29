package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import com.example.medipharm.Adapter.CategoryAdapter;
import com.example.medipharm.Domain.CategoryDomain;
import com.example.medipharm.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerViewCategory();

        ImageButton profilebtn = findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreateActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager LinearLayoutManager = null;
        recyclerViewCategoryList.setLayoutManager(LinearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Amoxill","pills1"));
        category.add(new CategoryDomain("Painkillers","pills2"));
        category.add(new CategoryDomain("Vaccines","pills3"));
        category.add(new CategoryDomain("Cetrizine","pills4"));
        category.add(new CategoryDomain("Syrups","pills5"));

        adapter= new CategoryAdapter(category, this);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}
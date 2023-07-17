package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.medipharm.Adapter.CategoryAdapter;
import com.example.medipharm.Adapter.PopularAdapter;
import com.example.medipharm.Domain.CategoryDomain;
import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();

        ImageButton profilebtn = findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        });
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        LinearLayout profilBtn = findViewById(R.id.profilbtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Profile.class));
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Amoxill", "pills1"));
        category.add(new CategoryDomain("Painkillers", "pills2"));
        category.add(new CategoryDomain("Vaccines", "pills3"));
        category.add(new CategoryDomain("Cetrizine", "pills4"));
        category.add(new CategoryDomain("Syrups", "pills5"));

        adapter = new CategoryAdapter(category, this);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<DrugDomain> druglist = new ArrayList<>();
        druglist.add(new DrugDomain("Amoxill", "pills1", "a whole pack of amoxill", 180.00));
        druglist.add(new DrugDomain("Painkillers", "pills7", "a whole pack of painkillers", 120.00));
        druglist.add(new DrugDomain("Vaccines", "pills3", "a syringe of vaccine", 500.00));
        druglist.add(new DrugDomain("Cetrizine", "pills4", "a day's worth of cetrizine", 200.00));
        druglist.add(new DrugDomain("Syrups", "syrup", "two bottles of syrup", 400.00));

        adapter2 = new PopularAdapter(druglist, this);
        recyclerViewPopularList.setAdapter(adapter2);
    }

}

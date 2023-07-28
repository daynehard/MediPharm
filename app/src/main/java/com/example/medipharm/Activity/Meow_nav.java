package com.example.medipharm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.medipharm.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Meow_nav extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;
    RelativeLayout home, profile, cart, support, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meow_nav);


        bottomNavigation = findViewById(R.id.bottomNavigation);
        home = findViewById(R.id.homeb);
        profile = findViewById(R.id.profileb);
        cart = findViewById(R.id.cartb);
        support = findViewById(R.id.supportb);
        settings = findViewById(R.id.settingb);







        bottomNavigation.show(1,true);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_person_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.shopping_cart));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_support));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.settings));


        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()){

                    case 1:

                        home.setVisibility(View.VISIBLE);
                        profile.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        support.setVisibility(View.GONE);
                        settings.setVisibility(View.GONE);


                        break;
                    case 2:

                        home.setVisibility(View.GONE);
                        profile.setVisibility(View.VISIBLE);
                        cart.setVisibility(View.GONE);
                        support.setVisibility(View.GONE);
                        settings.setVisibility(View.GONE);

                        break;
                    case 3:

                        home.setVisibility(View.GONE);
                        profile.setVisibility(View.GONE);
                        cart.setVisibility(View.VISIBLE);
                        support.setVisibility(View.GONE);
                        settings.setVisibility(View.GONE);

                        break;
                    case 4:

                        home.setVisibility(View.GONE);
                        profile.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        support.setVisibility(View.VISIBLE);
                        settings.setVisibility(View.GONE);

                        break;
                    case 5:

                        home.setVisibility(View.GONE);
                        profile.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        support.setVisibility(View.GONE);
                        settings.setVisibility(View.VISIBLE);

                        break;



                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()){

                    case 1:

                        home.setVisibility(View.VISIBLE);
                        profile.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        support.setVisibility(View.GONE);
                        settings.setVisibility(View.GONE);

                        break;
                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()){

                    case 2:

                        home.setVisibility(View.GONE);
                        profile.setVisibility(View.VISIBLE);
                        cart.setVisibility(View.GONE);
                        support.setVisibility(View.GONE);
                        settings.setVisibility(View.GONE);

                        break;
                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()){

                    case 3:

                        home.setVisibility(View.GONE);
                        profile.setVisibility(View.GONE);
                        cart.setVisibility(View.VISIBLE);
                        support.setVisibility(View.GONE);
                        settings.setVisibility(View.GONE);

                        break;
                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()){

                    case 4:

                        home.setVisibility(View.GONE);
                        profile.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        support.setVisibility(View.VISIBLE);
                        settings.setVisibility(View.GONE);

                        break;
                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                switch (model.getId()){

                    case 5:

                        home.setVisibility(View.GONE);
                        profile.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        support.setVisibility(View.GONE);
                        settings.setVisibility(View.VISIBLE);

                        break;
                }
                return null;
            }
        });
    }


}
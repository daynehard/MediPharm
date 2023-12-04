package com.example.medipharm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.medipharm.Adapter.CategoryAdapter;
import com.example.medipharm.Adapter.PopularAdapter;
import com.example.medipharm.Domain.CategoryDomain;
import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.Drug;
import com.example.medipharm.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.example.medipharm.Drug;
import com.example.medipharm.Adapter.PopularAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private RecyclerView recyclerViewSearchResults; // Add this line at the class level
    private FirebaseStorage storage;
    private List<Drug> itemList;  // Define the type for your items
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        search = findViewById(R.id.searchview);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();

        ImageView profilebtn = findViewById(R.id.profilebtn);
        TextView ViewAll = findViewById(R.id.viewall);
        TextView order = findViewById(R.id.ordernow);

        storage = FirebaseStorage.getInstance();

        // Set up a click listener for the search bar
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call a method to fetch items from Firebase Storage and display them
                fetchItemsFromStorage();
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        });
        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowCategory.class));
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowCategory.class));
            }
        });
    }

    private void bottomNavigation(){
        LinearLayout profileBtn = findViewById(R.id.profilbtn);
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout supportBtn = findViewById(R.id.supportbtn);
        LinearLayout settingsBtn = findViewById(R.id.settingsbtn);


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Support.class));
            }
        });
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Settings.class));
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Vitamins & Supplements", "pills2"));
        category.add(new CategoryDomain("Pain Relief", "pills5"));
        category.add(new CategoryDomain("Vaccines", "vaccine"));
        category.add(new CategoryDomain("Medical Devices", "device"));
        category.add(new CategoryDomain("Syrups", "syrup"));

        adapter = new CategoryAdapter(category, this);
        recyclerViewCategoryList.setAdapter(adapter);


    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        ArrayList<DrugDomain> druglist = new ArrayList<>();
        druglist.add(new DrugDomain("ABZ", "abz1", "ABZ Suspension contains Albendazole which is a broad spectrum Anthemintic used as a dewormer for the eradication of Pin-worms, Whip-worms, Ascaris Lumbricoides, Hookworm and Tapeworms.", 180.00,"Pinworm, ascariasis, hookworm and whipworm:\n" +
                "Children over 2 years and adults: a single dose of 400 mg (tablets or suspension).\n" +
                "Children up to 20 kilos: 100 mg single dose, which can be repeated after 7 days. Adults: 400 mg (2 tablets) or 10 ml of suspension. In one dose alone, you can repeat at 7 days.\n"));
        druglist.add(new DrugDomain("Ace Paracetamol Suppository", "ace1", "Ace® Suppository (125 mg): Each suppository contains 125 mg Paracetamol BP. Ace® is the formulation of Paracetamol, which is one of the safest and most widely used analgesic and antipyretic. It produces analgesic action by elevation of the pain threshold and antipyresis through action on the hypothalamic heat regulating centre", 120.00,"Dosage:\n" +
                "Children below 5 years : 125-250 mg, 2-3 times daily.\n" +
                "Children 6-12 years : 250-500 mg, 2-3 times daily.\n" +
                "How to administer Ace Suppositories:\n" +
                "1. Ensure the child's bowels are empty before administering the suppositories.\n" +
                "2. Wash your hands well with clean water and detergent.\n" +
                "3. Place your child on: o On either side with the knees bent toward the chest. o On back with legs raised as if to change a diaper. o On stomach with knees to chest or over your lap.\n" +
                "4. Hold the suppository between your thumb and index finger.\n" +
                "5. With your other hand, open the buttock cheeks until you can see the anal opening.\n" +
                "6. Gently insert the round end into the anal opening using the tip of your index finger. You will know if you have inserted it far enough if it does not come right back out.\n" +
                "7. Gently hold the buttock cheeks closed to keep your child from pushing it out."));
        druglist.add(new DrugDomain("Activated Charcoal Tablets", "charcoal1", "Activated charcoal is a potent natural treatment used to trap toxins and chemicals in the body, allowing them to be flushed out so the body does not reabsorb them. It is made from a variety of sources, but when used for natural healing, it is important to select activated charcoal made from coconut shells or other natural sources.", 500.00,"Take this medication exactly as prescribed by your doctor. Do not take it in larger amounts or for longer than recommended. Take charcoal with a full glass (8 ounces) of water. Do not crush, break, or chew a charcoal tablet or capsule. Swallow the pill whole. Charcoal is usually taken after meals or at the first sign of stomach discomfort. Stop taking charcoal and call your doctor if your diarrhoea lasts longer than 2 days or you also have a fever."));
        druglist.add(new DrugDomain("Actilosa Capsules 10's", "actilosa1", "Actilosa is a prebiotic and probiotic capsule taken as dietary supplement which contains 5 billion of L. acidophilas, B. longum,B.bifidum, B. infantis and Inulin 25 mg. It helps maintain a healthy intestinal floral.", 400.00,"Take one capsule twice daily. Do not exceed the recommended serving size."));
        druglist.add(new DrugDomain("Actilosa Dry Syrup 30ml", "actilosasyrup1", "Actilosa is a prebiotic and probiotic capsule taken as a dietary supplement. Prebiotics are compounds in food that induce the growth or activity of beneficial microorganisms such as bacteria and fungi. Probiotics are used to improve digestion and restore normal flora.", 400.00,"Take this medication exactly as prescribed by your doctor. Do not take it in larger amounts or for longer than recommended"));

        adapter2 = new PopularAdapter(druglist, this);
        recyclerViewPopularList.setAdapter(adapter2);

    }

    private void fetchItemsFromStorage() {
        // Initialize itemList if not already done
        if (itemList == null) {
            itemList = new ArrayList<>();
        } else {
            itemList.clear(); // Clear the existing list
        }

        // Get a reference to the Firebase Storage location where your items are stored
        StorageReference storageRef = storage.getReference().child("medipharm-37239");// Replace with your actual path

        // List items from the storage location
        storageRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference item : listResult.getItems()) {
                            // Get item details and add to itemList
                            String itemName = item.getName(); // This gets the item name (you can use it as the drug's name)
                            String description = "..."; // You need to fetch this from somewhere (e.g., Firebase Firestore or a database).
                            Drug drug = null;
                            double price = drug.getPrice();
                            // You need to fetch this from somewhere.
                            String usage = "..."; // You need to fetch this from somewhere.

                            // Create your Drug object with the retrieved information
                            drug = new Drug(itemName, description, price, usage);

                            itemList.add(drug);
                        }

                        // Update your RecyclerView or UI to display the items
                        updateRecyclerView(itemList);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors here
                        Log.e("FirebaseStorage", "Error fetching items: " + e.getMessage());
                    }
                });

    }

    private void updateRecyclerView(List<Drug> items) {
        // Update your RecyclerView or UI to display the fetched items
        // You can set the items in your RecyclerView adapter and notify the data set changed.

        // Make the search results RecyclerView visible
        recyclerViewSearchResults.setVisibility(View.VISIBLE);

        // You can update the adapter and set the items here
        // For example:
        // YourAdapter searchResultsAdapter = new YourAdapter(items);
        // recyclerViewSearchResults.setAdapter(searchResultsAdapter);
    }


}
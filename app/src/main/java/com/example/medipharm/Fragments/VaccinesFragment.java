package com.example.medipharm.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.medipharm.Activity.ShowCategory;
import com.example.medipharm.Adapter.Cat_VaccinesAdapter;
import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.R;

import java.util.ArrayList;

public class VaccinesFragment extends AppCompatActivity {

    private RecyclerView recyclerViewVaccineList;
    private RecyclerView.Adapter adapter3;
    ImageView btnback;
    private ArrayList<DrugDomain> druglist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_fragment);

        recyclerViewVaccine();
        btnback = findViewById(R.id.back_vaccine);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccinesFragment.this, ShowCategory.class));
            }
        });
    }
    private void recyclerViewVaccine(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewVaccineList = findViewById(R.id.rec_vaccine);
        recyclerViewVaccineList.setLayoutManager(gridLayoutManager);


        ArrayList<DrugDomain> druglist = new ArrayList<>();
        druglist.add(new DrugDomain("Aboretum", "abz", "ABZ Suspension contains Albendazole which is a broad spectrum Anthemintic used as a dewormer for the eradication of Pin-worms, Whip-worms, Ascaris Lumbricoides, Hookworm and Tapeworms.", 180.00,"Pinworm, ascariasis, hookworm and whipworm:\n" +
                "Children over 2 years and adults: a single dose of 400 mg (tablets or suspension).\n" +
                "Children up to 20 kilos: 100 mg single dose, which can be repeated after 7 days. Adults: 400 mg (2 tablets) or 10 ml of suspension. In one dose alone, you can repeat at 7 days.\n"));
        druglist.add(new DrugDomain("Ace Paracetamol Suppository", "ace", "Ace® Suppository (125 mg): Each suppository contains 125 mg Paracetamol BP. Ace® is the formulation of Paracetamol, which is one of the safest and most widely used analgesic and antipyretic. It produces analgesic action by elevation of the pain threshold and antipyresis through action on the hypothalamic heat regulating centre", 120.00,"Dosage:\n" +
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
        druglist.add(new DrugDomain("Activated Charcoal Tablets", "charcoal", "Activated charcoal is a potent natural treatment used to trap toxins and chemicals in the body, allowing them to be flushed out so the body does not reabsorb them. It is made from a variety of sources, but when used for natural healing, it is important to select activated charcoal made from coconut shells or other natural sources.", 500.00,"Take this medication exactly as prescribed by your doctor. Do not take it in larger amounts or for longer than recommended. Take charcoal with a full glass (8 ounces) of water. Do not crush, break, or chew a charcoal tablet or capsule. Swallow the pill whole. Charcoal is usually taken after meals or at the first sign of stomach discomfort. Stop taking charcoal and call your doctor if your diarrhoea lasts longer than 2 days or you also have a fever."));
        druglist.add(new DrugDomain("Actilosa Capsules 10's", "actilosa", "Actilosa is a prebiotic and probiotic capsule taken as dietary supplement which contains 5 billion of L. acidophilas, B. longum,B.bifidum, B. infantis and Inulin 25 mg. It helps maintain a healthy intestinal floral.", 400.00,"Take one capsule twice daily. Do not exceed the recommended serving size."));
        druglist.add(new DrugDomain("Actilosa Dry Syrup 30ml", "actilosasyrup", "Actilosa is a prebiotic and probiotic capsule taken as a dietary supplement. Prebiotics are compounds in food that induce the growth or activity of beneficial microorganisms such as bacteria and fungi. Probiotics are used to improve digestion and restore normal flora.", 400.00,"Take this medication exactly as prescribed by your doctor. Do not take it in larger amounts or for longer than recommended"));
        druglist.add(new DrugDomain("Aboretum", "abz", "ABZ Suspension contains Albendazole which is a broad spectrum Anthemintic used as a dewormer for the eradication of Pin-worms, Whip-worms, Ascaris Lumbricoides, Hookworm and Tapeworms.", 180.00,"Pinworm, ascariasis, hookworm and whipworm:\n" +
                "Children over 2 years and adults: a single dose of 400 mg (tablets or suspension).\n" +
                "Children up to 20 kilos: 100 mg single dose, which can be repeated after 7 days. Adults: 400 mg (2 tablets) or 10 ml of suspension. In one dose alone, you can repeat at 7 days.\n"));
        druglist.add(new DrugDomain("Ace Paracetamol Suppository", "ace", "Ace® Suppository (125 mg): Each suppository contains 125 mg Paracetamol BP. Ace® is the formulation of Paracetamol, which is one of the safest and most widely used analgesic and antipyretic. It produces analgesic action by elevation of the pain threshold and antipyresis through action on the hypothalamic heat regulating centre", 120.00,"Dosage:\n" +
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
        druglist.add(new DrugDomain("Activated Charcoal Tablets", "charcoal", "Activated charcoal is a potent natural treatment used to trap toxins and chemicals in the body, allowing them to be flushed out so the body does not reabsorb them. It is made from a variety of sources, but when used for natural healing, it is important to select activated charcoal made from coconut shells or other natural sources.", 500.00,"Take this medication exactly as prescribed by your doctor. Do not take it in larger amounts or for longer than recommended. Take charcoal with a full glass (8 ounces) of water. Do not crush, break, or chew a charcoal tablet or capsule. Swallow the pill whole. Charcoal is usually taken after meals or at the first sign of stomach discomfort. Stop taking charcoal and call your doctor if your diarrhoea lasts longer than 2 days or you also have a fever."));
        druglist.add(new DrugDomain("Actilosa Capsules 10's", "actilosa", "Actilosa is a prebiotic and probiotic capsule taken as dietary supplement which contains 5 billion of L. acidophilas, B. longum,B.bifidum, B. infantis and Inulin 25 mg. It helps maintain a healthy intestinal floral.", 400.00,"Take one capsule twice daily. Do not exceed the recommended serving size."));
        druglist.add(new DrugDomain("Actilosa Dry Syrup 30ml", "actilosasyrup", "Actilosa is a prebiotic and probiotic capsule taken as a dietary supplement. Prebiotics are compounds in food that induce the growth or activity of beneficial microorganisms such as bacteria and fungi. Probiotics are used to improve digestion and restore normal flora.", 400.00,"Take this medication exactly as prescribed by your doctor. Do not take it in larger amounts or for longer than recommended"));

        adapter3 = new Cat_VaccinesAdapter(druglist, this);
        recyclerViewVaccineList.setAdapter(adapter3);

    }
}
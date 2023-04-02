package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class RulesActivity extends AppCompatActivity {

    ImageView backButton;
    TextView tvRules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        // Get views
        backButton = findViewById(R.id.imageViewRules);
        tvRules = findViewById(R.id.tvRules);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("rules").document("rules").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                tvRules.setText(task.getResult().getString("rules"));
            }
        });

        // Handle back button
        backButton.setOnClickListener(v -> finish());

    }
}
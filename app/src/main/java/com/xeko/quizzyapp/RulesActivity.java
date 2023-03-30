package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class RulesActivity extends AppCompatActivity {

    ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        // Get views
        backButton = findViewById(R.id.imageViewRules);

        // Handle back button
        backButton.setOnClickListener(v -> finish());

    }
}
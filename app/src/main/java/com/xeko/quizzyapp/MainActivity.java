package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    CardView cvStartQuiz, cvRules, cvHistory, cvChangePassword, cvLogout;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if user is logged in
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            // User not logged in - go to login screen
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Get views
        cvStartQuiz = findViewById(R.id.cvStartQuiz);
        cvRules = findViewById(R.id.cvRules);
        cvHistory = findViewById(R.id.cvHistory);
        cvChangePassword = findViewById(R.id.cvChangePassword);
        cvLogout = findViewById(R.id.cvLogout);
        username = findViewById(R.id.username);

        // Set username
        FirebaseUser user = mAuth.getCurrentUser();
        username.setText("Hello, " + Objects.requireNonNull(user).getEmail() + "!");

        // Handle Logout
        cvLogout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // Handle Change Password
        cvChangePassword.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));

        // Handle Rules
        cvRules.setOnClickListener(v -> startActivity(new Intent(this, RulesActivity.class)));

        // Handle History
        cvHistory.setOnClickListener(v -> startActivity(new Intent(this, HistoryActivity.class)));

        // Handle Start Quiz
        cvStartQuiz.setOnClickListener(v -> startActivity(new Intent(this, SubjectActivity.class)));
    }
}
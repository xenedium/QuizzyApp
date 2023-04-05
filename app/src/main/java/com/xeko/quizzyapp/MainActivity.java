package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    CardView cvStartQuiz, cvRules, cvHistory, cvChangePassword, cvLogout;
    TextView username;
    ImageView ivStart, ivRules, ivHistory, ivPassword, ivLogout;
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

        // Get icons
        ivStart = findViewById(R.id.ivStart);
        ivRules = findViewById(R.id.ivRules);
        ivHistory = findViewById(R.id.ivHistory);
        ivPassword = findViewById(R.id.ivPassword);
        ivLogout = findViewById(R.id.ivLogout);

        Picasso.get().load("https://xene.fra1.digitaloceanspaces.com/start-button.png").into(ivStart);
        Picasso.get().load("https://xene.fra1.digitaloceanspaces.com/rules.png").into(ivRules);
        Picasso.get().load("https://xene.fra1.digitaloceanspaces.com/history.png").into(ivHistory);
        Picasso.get().load("https://xene.fra1.digitaloceanspaces.com/key.png").into(ivPassword);
        Picasso.get().load("https://xene.fra1.digitaloceanspaces.com/logout.png").into(ivLogout);

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
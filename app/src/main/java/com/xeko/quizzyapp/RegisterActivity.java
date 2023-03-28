package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    Button mRegisterBtn;
    EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisterBtn = findViewById(R.id.button);
        mEmail = findViewById(R.id.editTextTextEmailAddress);
        mPassword = findViewById(R.id.editTextTextPassword);

        mRegisterBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            if (email.isEmpty()) {
                mEmail.setError("Email is required");
                return;
            }
            if (password.isEmpty()) {
                mPassword.setError("Password is required");
                return;
            }
            if (password.length() < 8) {
                mPassword.setError("Password must be at least 8 characters");
                return;
            }
            // test email regex
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                mEmail.setError("Invalid email");
                return;
            }
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Error: Could not Register, email already exists", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
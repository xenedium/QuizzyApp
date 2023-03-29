package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends Activity {

    Button mRegisterBtn;
    EditText mEmail, mPassword, mPasswordConfirm;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisterBtn = findViewById(R.id.btnRegister);
        mEmail = findViewById(R.id.emailInput);
        mPassword = findViewById(R.id.passwordInput);
        mPasswordConfirm = findViewById(R.id.passwordConfirmInput);
        backBtn = findViewById(R.id.imageView);

        backBtn.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        mRegisterBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String passwordConfirm = mPasswordConfirm.getText().toString().trim();

            if (email.isEmpty()) {
                mEmail.setError("Email is required");
                return;
            }
            if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                mEmail.setError("Please enter a valid email");
                return;
            }
            if (password.isEmpty()) {
                mPassword.setError("Password is required");
                return;
            }
            if (password.length() < 6) {
                mPassword.setError("Password must be at least 6 characters");
                return;
            }
            if (!password.equals(passwordConfirm)) {
                mPasswordConfirm.setError("Passwords do not match");
                return;
            }

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Email already in use... Please login", Toast.LENGTH_LONG).show();
                }
            });
        });

    }
}
package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.emailInput);
        mPassword = findViewById(R.id.passwordInput);
        mLoginBtn = findViewById(R.id.btnLogin);
        mCreateBtn = findViewById(R.id.tvSignUp);

        mCreateBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });

        mLoginBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

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

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    mPassword.setError("Incorrect password");
                }
            });
        });


    }
}
package com.xeko.quizzyapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends Activity {

    EditText oldPasswordInput, newPasswordInput, newPasswordConfirmInput;
    Button changePasswordButton;
    ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) finish();

        // Get views
        oldPasswordInput = findViewById(R.id.oldPasswordInput);
        newPasswordInput = findViewById(R.id.newPasswordInput);
        newPasswordConfirmInput = findViewById(R.id.newPasswordConfirmInput);
        changePasswordButton = findViewById(R.id.changePasswordButton);
        backButton = findViewById(R.id.imageViewEditPassword);

        // Handle back button
        backButton.setOnClickListener(v -> finish());

        // Handle change password button
        changePasswordButton.setOnClickListener(v -> {
            if (!newPasswordInput.getText().toString().equals(newPasswordConfirmInput.getText().toString())) {
                // Passwords don't match
                newPasswordInput.setError("Passwords don't match");
                newPasswordConfirmInput.setError("Passwords don't match");
                return;
            }
            if (newPasswordInput.getText().toString().length() < 6) {
                // Password too short
                newPasswordInput.setError("Password too short");
                newPasswordConfirmInput.setError("Password too short");
                return;
            }
            user.reauthenticate(EmailAuthProvider.getCredential(user.getEmail(), oldPasswordInput.getText().toString()))
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            user.updatePassword(newPasswordInput.getText().toString())
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(this, "Error changing password, Check your network", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            oldPasswordInput.setError("Incorrect password");
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error changing password, Check your network", Toast.LENGTH_SHORT).show());

        });
    }
}
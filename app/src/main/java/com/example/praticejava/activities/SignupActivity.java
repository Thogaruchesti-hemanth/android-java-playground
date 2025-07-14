package com.example.praticejava.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.praticejava.R;
import com.example.praticejava.data.auth.FirebaseAuthManager;
import com.example.praticejava.domain.auth.OnAuthCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText, usernameEditText;
    private FirebaseAuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        authManager = new FirebaseAuthManager();

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        usernameEditText = findViewById(R.id.user_name_edit_text);

        findViewById(R.id.signup_button).setOnClickListener(v -> signupWithEmail());

        findViewById(R.id.signin_text_view).setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });

    }

    private void signupWithEmail() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        authManager.signupWithEmail(email, password, new OnAuthCompleteListener() {
            @Override
            public void onSuccess(FirebaseUser user) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();

                user.updateProfile(profileUpdates).addOnCompleteListener(task -> {
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    finish();
                });
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
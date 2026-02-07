package com.example.praticejava.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;
    private FirebaseAuthManager authManager;

    // Centralized listener to handle navigation after any successful auth method
    private final OnAuthCompleteListener authListener = new OnAuthCompleteListener() {
        @Override
        public void onSuccess(FirebaseUser user) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(LoginActivity.this, "Authentication failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("AUTH_DEBUG", "OAuth failed", e);
            Log.d(LoginActivity.class.getSimpleName(),e.getMessage());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // ✅ FIX: capture initial padding ONCE
        final var root = findViewById(R.id.login_layout);

        final int initialLeft = root.getPaddingLeft();
        final int initialTop = root.getPaddingTop();
        final int initialRight = root.getPaddingRight();
        final int initialBottom = root.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    initialLeft + systemBars.left,
                    initialTop + systemBars.top,
                    initialRight + systemBars.right,
                    initialBottom + systemBars.bottom
            );
            return insets;
        });


        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        authManager = new FirebaseAuthManager();

        // Email Login
        findViewById(R.id.login_button).setOnClickListener(v -> loginWithEmail());

        // Modern Google Login (No request code needed)
        findViewById(R.id.google_login_button).setOnClickListener(v ->
                authManager.signInWithGoogle(this, authListener)
        );

        // GitHub Login (Now uses Firebase OAuthProvider internally)
        findViewById(R.id.github_login_button).setOnClickListener(v -> {
                    Log.d("AUTH_DEBUG", "Starting OAuth with GitHub");
                    authManager.signInWithGitHub(this, authListener);
                }
        );

        // Navigation to Signup
        findViewById(R.id.signup_text_view).setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class))
        );
    }

    private void loginWithEmail() {
        String email = (emailEditText.getText() != null) ? emailEditText.getText().toString().trim() : "";
        String password = (passwordEditText.getText() != null) ? passwordEditText.getText().toString().trim() : "";

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password required", Toast.LENGTH_SHORT).show();
            return;
        }

        authManager.loginWithEmail(email, password, authListener);
    }

    private void setupWindowInsets() {

    }

    /**
     * NOTE: onActivityResult is no longer needed for Google or GitHub
     * with the new implementation. If you add Facebook later, you may
     * need to re-add it specifically for the Facebook SDK.
     */
}
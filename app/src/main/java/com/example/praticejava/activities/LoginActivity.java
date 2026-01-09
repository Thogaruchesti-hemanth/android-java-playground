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

public class LoginActivity extends AppCompatActivity {

    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 100;
    private final OnAuthCompleteListener authListener = new OnAuthCompleteListener() {

        @Override
        public void onSuccess(FirebaseUser user) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
    private TextInputEditText emailEditText, passwordEditText;

//    private CallbackManager facebookCallbackManager;
    private FirebaseAuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        final int paddingLeft = findViewById(R.id.main).getPaddingLeft();
        final int paddingTop = findViewById(R.id.main).getPaddingTop();
        final int paddingRight = findViewById(R.id.main).getPaddingRight();
        final int paddingBottom = findViewById(R.id.main).getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    paddingLeft + systemBars.left,
                    paddingTop + systemBars.top,
                    paddingRight + systemBars.right,
                    paddingBottom + systemBars.bottom
            );
            return insets;
        });

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);

        authManager = new FirebaseAuthManager();

        findViewById(R.id.login_button).setOnClickListener(v -> loginWithEmail());

        findViewById(R.id.google_login_button).setOnClickListener(v -> authManager.signInWithGoogle(this, authListener));

//        findViewById(R.id.facebook_login_button).setOnClickListener(v -> {
//            facebookCallbackManager = CallbackManager.Factory.create();
//            authManager.signInWithFacebook(this, facebookCallbackManager, authListener);
//        });

        findViewById(R.id.github_login_button).setOnClickListener(v -> authManager.signInWithGitHub(this, authListener));

        findViewById(R.id.signup_text_view).setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
        });

    }

    private void loginWithEmail() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password required", Toast.LENGTH_SHORT).show();
            return;
        }

        authManager.loginWithEmail(email, password, authListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Google Sign-In Result
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE && resultCode == RESULT_OK) {
            authManager.handleGoogleResult(data, this, authListener);
        }

        // Facebook Result
//        if (facebookCallbackManager != null) {
//            facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
//        }
    }

}
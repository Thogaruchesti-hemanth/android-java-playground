package com.example.praticejava.data.auth;

import android.app.Activity;
import com.example.praticejava.domain.auth.AuthManager;
import com.example.praticejava.domain.auth.OnAuthCompleteListener;
import com.example.praticejava.utils.SocialLoginUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.OAuthProvider;

public class FirebaseAuthManager implements AuthManager {
    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthManager() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void loginWithEmail(String email, String password, OnAuthCompleteListener listener) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess(firebaseAuth.getCurrentUser());
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
    }

    @Override
    public void signupWithEmail(String email, String password, OnAuthCompleteListener listener) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess(firebaseAuth.getCurrentUser());
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
    }

    /**
     * Modern Google Sign-In using the refactored SocialLoginUtils.
     * This no longer requires onActivityResult in the Activity.
     */
    @Override
    public void signInWithGoogle(Activity activity, OnAuthCompleteListener listener) {
        SocialLoginUtils.signIn(activity, new SocialLoginUtils.GoogleAuthCallback() {
            @Override
            public void onSuccess(String idToken) {
                // Convert Google ID Token to Firebase Credential
                AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

                firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                listener.onSuccess(firebaseAuth.getCurrentUser());
                            } else {
                                listener.onFailure(task.getException());
                            }
                        });
            }

            @Override
            public void onFailure(String error) {
                listener.onFailure(new Exception(error));
            }
        });
    }

    @Override
    public void signInWithFacebook(Activity activity, OnAuthCompleteListener listener) {

    }

    /**
     * Note: For GitHub, the modern way is to use the Firebase SDK's built-in
     * OAuthProvider rather than manual URI parsing.
     */
    @Override
    public void signInWithGitHub(Activity activity, OnAuthCompleteListener listener) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        OAuthProvider.Builder provider =
                OAuthProvider.newBuilder("github.com");

        // Optional but recommended
        provider.addCustomParameter("scope", "user:email");

        Task<AuthResult> pendingResult = auth.getPendingAuthResult();

        if (pendingResult != null) {
            // 🔁 Flow already in progress (rotation / process restore)
            pendingResult
                    .addOnSuccessListener(authResult ->
                            listener.onSuccess(authResult.getUser()))
                    .addOnFailureListener(listener::onFailure);
        } else {
            // 🚀 Start fresh OAuth flow
            auth.startActivityForSignInWithProvider(
                            activity,
                            provider.build())
                    .addOnSuccessListener(authResult ->
                            listener.onSuccess(authResult.getUser()))
                    .addOnFailureListener(listener::onFailure);
        }
    }

}
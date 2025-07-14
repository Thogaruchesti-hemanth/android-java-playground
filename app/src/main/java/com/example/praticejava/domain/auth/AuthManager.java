package com.example.praticejava.domain.auth;

import android.app.Activity;

public interface AuthManager {

    void loginWithEmail(String email, String password, OnAuthCompleteListener listener);

    void signupWithEmail(String email, String password, OnAuthCompleteListener listener);

    void signInWithGoogle(Activity activity, OnAuthCompleteListener listener);

    void signInWithFacebook(Activity activity, OnAuthCompleteListener listener);

    void signInWithGitHub(Activity activity, OnAuthCompleteListener listener);
}

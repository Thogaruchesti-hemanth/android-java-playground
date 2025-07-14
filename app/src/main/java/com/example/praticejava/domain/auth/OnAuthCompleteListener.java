package com.example.praticejava.domain.auth;

import com.google.firebase.auth.FirebaseUser;

public interface OnAuthCompleteListener {
    void onSuccess(FirebaseUser user);

    void onFailure(Exception e);
}
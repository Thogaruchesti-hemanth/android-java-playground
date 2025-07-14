package com.example.praticejava.utils;

import android.content.Context;
import android.content.Intent;

import com.example.praticejava.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SocialLoginUtils {

    public static final int GOOGLE_SIGN_IN_CODE = 9001;
    private static GoogleAuthCallback googleAuthCallback;

    public static void setGoogleAuthCallbackListener(GoogleAuthCallback callback) {
        googleAuthCallback = callback;
    }

    public static GoogleSignInClient getGoogleSignInClient(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(context.getString(R.string.default_web_client_id)) // from strings.xml
                .requestEmail().build();
        return GoogleSignIn.getClient(context, gso);
    }

    public static void handleGoogleSignInResult(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();
            if (googleAuthCallback != null) {
                googleAuthCallback.onResult(idToken, null);
            }
        } catch (ApiException e) {
            if (googleAuthCallback != null) {
                googleAuthCallback.onResult(null, e.getMessage());
            }
        }
    }

    public interface GoogleAuthCallback {
        void onResult(String idToken, String error);
    }
}

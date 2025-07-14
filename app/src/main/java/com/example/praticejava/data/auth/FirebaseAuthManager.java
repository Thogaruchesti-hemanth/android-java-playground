package com.example.praticejava.data.auth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.praticejava.domain.auth.AuthManager;
import com.example.praticejava.domain.auth.OnAuthCompleteListener;
import com.example.praticejava.utils.SocialLoginUtils;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class FirebaseAuthManager implements AuthManager {
    private final FirebaseAuth firebaseAuth;
    private final String TAG = "FirebaseAuthManager";

    public FirebaseAuthManager() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void loginWithEmail(String email, String password, OnAuthCompleteListener listener) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listener.onSuccess(firebaseAuth.getCurrentUser());
            } else {
                listener.onFailure(task.getException());
            }
        });
    }

    @Override
    public void signupWithEmail(String email, String password, OnAuthCompleteListener listener) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listener.onSuccess(firebaseAuth.getCurrentUser());
            } else {
                listener.onFailure(task.getException());
            }
        });
    }

    // GOOGLE LOGIN
    @Override
    public void signInWithGoogle(Activity activity, OnAuthCompleteListener listener) {
        Intent signInIntent = SocialLoginUtils.getGoogleSignInClient(activity).getSignInIntent();
        activity.startActivityForResult(signInIntent, SocialLoginUtils.GOOGLE_SIGN_IN_CODE);
        SocialLoginUtils.setGoogleAuthCallbackListener((idToken, error) -> {
            if (idToken != null) {
                AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
                firebaseAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess(firebaseAuth.getCurrentUser());
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
            } else {
                listener.onFailure(new Exception(error));
            }
        });
    }

    // FACEBOOK LOGIN
//    @Override

    //TODO need to do
    public void signInWithFacebook(Activity activity, OnAuthCompleteListener listener) {
//        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile"));
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                handleFacebookAccessToken(loginResult.getAccessToken(), listener);
//            }
//
//            @Override
//            public void onCancel() {
//                listener.onFailure(new Exception("Facebook login cancelled"));
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                listener.onFailure(error);
//            }
//        });
    }

//    private void handleFacebookAccessToken(AccessToken token, OnAuthCompleteListener listener) {
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        listener.onSuccess(firebaseAuth.getCurrentUser());
//                    } else {
//                        listener.onFailure(task.getException());
//                    }
//                });
//    }

    // GITHUB LOGIN (via OAuth redirect in browser)
    @Override
    public void signInWithGitHub(Activity activity, OnAuthCompleteListener listener) {
        // GitHub uses custom OAuth flow via browser
        String clientId = "YOUR_GITHUB_CLIENT_ID"; // Replace with your own
        String redirectUri = "https://YOUR_FIREBASE_PROJECT.firebaseapp.com/__/auth/handler";
        String githubLoginUrl = "https://github.com/login/oauth/authorize" + "?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&scope=read:user,user:email";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubLoginUrl));
        activity.startActivity(intent);

        // ⚠️ Firebase automatically handles the redirect
        // If GitHub is enabled in Firebase Console,
        // Firebase Auth UI or custom web auth redirect will log the user in
        // You'll typically use a WebView/ChromeTab → redirect to Firebase handler → app resumes
        // Use onResume + FirebaseAuth.getCurrentUser() to check
    }

    public void handleGoogleResult(Intent data, Activity activity, OnAuthCompleteListener listener) {
        try {
            SignInCredential credential = Identity.getSignInClient(activity).getSignInCredentialFromIntent(data);
            String idToken = credential.getGoogleIdToken();

            if (idToken != null) {
                AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                firebaseAuth.signInWithCredential(firebaseCredential).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess(firebaseAuth.getCurrentUser());
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
            } else {
                listener.onFailure(new Exception("Google ID token is null"));
            }
        } catch (ApiException e) {
            listener.onFailure(e);
        }
    }
}

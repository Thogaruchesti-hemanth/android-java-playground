package com.example.praticejava.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.credentials.ClearCredentialStateRequest;
import androidx.credentials.CredentialManager;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.example.praticejava.R;

import java.util.concurrent.Executor;
import androidx.core.content.ContextCompat;

public class SocialLoginUtils {

    public interface GoogleAuthCallback {
        void onSuccess(String idToken);
        void onFailure(String error);
    }

    /**
     * Modern Google Sign-In using Credential Manager
     */
    public static void signIn(Context context, GoogleAuthCallback callback) {
        CredentialManager credentialManager = CredentialManager.create(context);

        // 1. Configure the Google ID request
        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .setAutoSelectEnabled(true)
                .build();

        // 2. Build the credential request
        GetCredentialRequest request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build();

        Executor executor = ContextCompat.getMainExecutor(context);

        // 3. Launch the selector
        credentialManager.getCredentialAsync(
                context,
                request,
                null,
                executor,
                new androidx.credentials.CredentialManagerCallback<>() {
                    @Override
                    public void onResult(GetCredentialResponse result) {
                        handleSignInResult(result, callback);
                    }

                    @Override
                    public void onError(@NonNull androidx.credentials.exceptions.GetCredentialException e) {
                        callback.onFailure(e.getMessage());
                    }
                }
        );
    }

    private static void handleSignInResult(GetCredentialResponse result, GoogleAuthCallback callback) {
        if (result.getCredential() instanceof GoogleIdTokenCredential) {
            GoogleIdTokenCredential credential = (GoogleIdTokenCredential) result.getCredential();
            callback.onSuccess(credential.getIdToken());
        } else {
            callback.onFailure("Unknown credential type received.");
        }
    }

    /**
     * Sign out / Clear sessions
     */
    public static void signOut(Context context) {
        CredentialManager credentialManager = CredentialManager.create(context);

        ClearCredentialStateRequest request =
                new ClearCredentialStateRequest();

        credentialManager.clearCredentialStateAsync(
                request,
                null, // ✅ CancellationSignal (optional)
                ContextCompat.getMainExecutor(context),
                new androidx.credentials.CredentialManagerCallback<>() {

                    @Override
                    public void onResult(Void result) {
                        // Credential state cleared successfully
                    }

                    @Override
                    public void onError(
                            @NonNull androidx.credentials.exceptions.ClearCredentialException e) {
                        // Failed to clear credential state
                    }
                }
        );
    }}
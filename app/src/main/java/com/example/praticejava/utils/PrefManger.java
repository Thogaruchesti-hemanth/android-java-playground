package com.example.praticejava.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManger {
    private static final String PREF_NAME = "onboarding_prefs";
    private static final String ONBOARDING_KEY = "completed";
    private final SharedPreferences sharedPreferences;

    public PrefManger(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isOnboardingCompleted() {
        return sharedPreferences.getBoolean(ONBOARDING_KEY, false);
    }

    public void setOnboardingCompleted(boolean completed) {
        sharedPreferences.edit().putBoolean(ONBOARDING_KEY, completed).apply();
    }

}

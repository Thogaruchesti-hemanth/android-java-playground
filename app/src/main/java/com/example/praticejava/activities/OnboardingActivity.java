package com.example.praticejava.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.praticejava.MainActivity;
import com.example.praticejava.R;
import com.example.praticejava.adapters.OnboardingAdapter;
import com.example.praticejava.models.OnBoardItem;
import com.example.praticejava.utils.PrefManger;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout indicatorsLayout;
    private Button buttonNext;
    private ViewPager2 onboardingViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);

        // Set system window insets (status bar/padding support)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Skip onboarding if already completed
        if (new PrefManger(this).isOnboardingCompleted()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        onboardingViewPager = findViewById(R.id.vp_onboarding);
        indicatorsLayout = findViewById(R.id.indicators_layout);
        buttonNext = findViewById(R.id.buttonNext);

        setupOnBoardingItems();   // Load ViewPager2 data
        setupIndicators();        // Create dot indicators
        setCurrentIndicator(0);   // Highlight first dot

        // Handle page change
        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setCurrentIndicator(position);
            }
        });

        // Next button click
        buttonNext.setOnClickListener(view -> {
            if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
            } else {
                new PrefManger(this).setOnboardingCompleted(true);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    /**
     * Sets up ViewPager2 onboarding screens
     */
    private void setupOnBoardingItems() {
        List<OnBoardItem> items = new ArrayList<>();
        items.add(new OnBoardItem("Welcome", "Your smart study partner", R.drawable.onboarding_image_1));
        items.add(new OnBoardItem("Track Progress", "Monitor your daily improvements", R.drawable.onboarding_image_2));
        items.add(new OnBoardItem("Ace Exams", "Tips, notes & mock tests", R.drawable.onboarding_image3));
        onboardingAdapter = new OnboardingAdapter(items);
        onboardingViewPager.setAdapter(onboardingAdapter);
    }

    /**
     * Sets up dot indicators dynamically based on number of items
     * Note: You can either use one selector drawable OR separate drawables for active/inactive
     */
    private void setupIndicators() {
        int itemCount = onboardingAdapter.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            ImageView dot = new ImageView(getApplicationContext());

            // 👉 Option 1: Use single selector (active + inactive states inside it)
            dot.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_active_material));
            dot.setEnabled(false); // default to inactive state

            // 👉 Option 2: If using separate drawables for active & inactive
            // dot.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_inactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            indicatorsLayout.addView(dot, params);
        }
    }

    /**
     * Highlights current indicator and applies animation
     * Uses selector drawable + View.animate() for scale/alpha
     */
    private void setCurrentIndicator(int index) {
        int count = indicatorsLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            View dot = indicatorsLayout.getChildAt(i);
            dot.setEnabled(i == index); // triggers selector drawable state

            // ✅ Smooth grow/shrink animation
            float scale = (i == index) ? 1.4f : 1.0f;
            float alpha = (i == index) ? 1f : 0.5f;
            dot.animate().scaleX(scale).scaleY(scale).alpha(alpha).setDuration(200).start();

            // 👉 If you're using separate drawables:
            // ((ImageView) dot).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
            //      i == index ? R.drawable.indicator_active : R.drawable.indicator_inactive));
        }

        // Update button text on last page
        buttonNext.setText(index == onboardingAdapter.getItemCount() - 1
                ? R.string.text_get_started : R.string.text_next);
    }

    /*
     ****************************************************************
     * 👇 Bonus: Alternative Animations You Can Try
     ****************************************************************
     *
     * 1️⃣ Bounce animation using ObjectAnimator:
     * ObjectAnimator bounce = ObjectAnimator.ofFloat(dot, "translationY", 0f, -10f, 0f);
     * bounce.setDuration(300).start();
     ****************************************************************
     */

}

# Android Onboarding Screen Implementation

A reusable, production-ready onboarding screen implementation for Android apps using Java, ViewPager2, and Material Design principles.

## 📋 Overview

This implementation provides a smooth, animated onboarding experience with:
- **ViewPager2** for swipeable screens
- **Animated dot indicators** with scale and alpha effects
- **SharedPreferences** to show onboarding only once
- **Easy customization** - just update content and images

## 🎯 Features

- ✅ Swipeable onboarding screens
- ✅ Animated page indicators (dots)
- ✅ "Next" button transitions to "Get Started" on final screen
- ✅ Skip onboarding on subsequent app launches
- ✅ Edge-to-edge display support
- ✅ Smooth animations (scale, alpha)
- ✅ Material Design compliant

## 📁 Project Structure

```
com.example.praticejava/
├── activities/
│   └── OnboardingActivity.java          # Main onboarding activity
├── adapters/
│   └── OnboardingAdapter.java           # ViewPager2 adapter
├── models/
│   └── OnBoardItem.java                 # Data model for each screen
└── utils/
    └── PrefManger.java                  # SharedPreferences helper
```

## 🚀 Quick Start

### 1. Copy Required Files

Copy these files to your project:
- `OnboardingActivity.java` → `activities/`
- `OnBoardItem.java` → `models/`
- `OnboardingAdapter.java` → `adapters/` (you'll need to create this)
- `PrefManger.java` → `utils/` (you'll need to create this)

### 2. Add Dependencies

In your `build.gradle (Module: app)`:

```gradle
dependencies {
    // ViewPager2 for swipeable screens
    implementation 'androidx.viewpager2:viewpager2:1.0.0'
    
    // AppCompat & Material Design
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
}
```

### 3. Add Images to Resources

Place your onboarding images in `res/drawable/`:
- `onboarding_image_1.png`
- `onboarding_image_2.png`
- `onboarding_image3.png`

### 4. Create Layout File

Create `res/layout/activity_onboarding.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp_onboarding"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/indicators_layout" />

    <LinearLayout
        android:id="@+id/indicators_layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_marginBottom="24dp"
        app:layout_constraintBottom_toTopOf="@id/buttonNext"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <Button
        android:id="@+id/buttonNext"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="@string/text_next"
        android:layout_margin="16dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 5. Add String Resources

In `res/values/strings.xml`:

```xml
<resources>
    <string name="text_next">Next</string>
    <string name="text_get_started">Get Started</string>
</resources>
```

### 6. Create Indicator Drawable

Create `res/drawable/indicator_active_material.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Active state (enabled) -->
    <item android:state_enabled="true">
        <shape android:shape="oval">
            <solid android:color="#6200EE" />
            <size android:width="8dp" android:height="8dp" />
        </shape>
    </item>
    
    <!-- Inactive state -->
    <item>
        <shape android:shape="oval">
            <solid android:color="#BDBDBD" />
            <size android:width="8dp" android:height="8dp" />
        </shape>
    </item>
</selector>
```

### 7. Register Activity in Manifest

In `AndroidManifest.xml`:

```xml
<activity
    android:name=".activities.OnboardingActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

<activity
    android:name=".MainActivity"
    android:exported="false" />
```

## 📝 Required Supporting Files

### OnboardingAdapter.java

```java
package com.example.praticejava.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.praticejava.R;
import com.example.praticejava.models.OnBoardItem;
import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<OnBoardItem> onBoardItems;

    public OnboardingAdapter(List<OnBoardItem> onBoardItems) {
        this.onBoardItems = onBoardItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_onboarding, parent, false);
        return new OnboardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onBoardItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardItems.size();
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textDescription;
        private ImageView imageOnboarding;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            textDescription = itemView.findViewById(R.id.text_description);
            imageOnboarding = itemView.findViewById(R.id.image_onboarding);
        }

        void setOnboardingData(OnBoardItem item) {
            textTitle.setText(item.getTitle());
            textDescription.setText(item.getDescription());
            imageOnboarding.setImageResource(item.getImageResourceId());
        }
    }
}
```

### PrefManger.java

```java
package com.example.praticejava.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManger {
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "onboarding_pref";
    private static final String KEY_ONBOARDING_COMPLETED = "onboarding_completed";

    public PrefManger(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setOnboardingCompleted(boolean completed) {
        sharedPreferences.edit().putBoolean(KEY_ONBOARDING_COMPLETED, completed).apply();
    }

    public boolean isOnboardingCompleted() {
        return sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETED, false);
    }
}
```

### item_onboarding.xml

Create `res/layout/item_onboarding.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="32dp">

    <ImageView
        android:id="@+id/image_onboarding"
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:layout_marginBottom="32dp"
        android:contentDescription="@string/app_name" />

    <TextView
        android:id="@+id/text_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="24sp"
        android:textStyle="bold"
        android:textColor="@android:color/black"
        android:layout_marginBottom="16dp" />

    <TextView
        android:id="@+id/text_description"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="16sp"
        android:textColor="@android:color/darker_gray"
        android:gravity="center" />

</LinearLayout>
```

## 🎨 Customization Guide

### Change Onboarding Content

In `OnboardingActivity.setupOnBoardingItems()`:

```java
private void setupOnBoardingItems() {
    List<OnBoardItem> items = new ArrayList<>();
    items.add(new OnBoardItem(
        "Your Title",           // Title
        "Your Description",     // Description
        R.drawable.your_image   // Image resource
    ));
    // Add more items...
    onboardingAdapter = new OnboardingAdapter(items);
    onboardingViewPager.setAdapter(onboardingAdapter);
}
```

### Customize Indicator Colors

Edit `indicator_active_material.xml`:

```xml
<!-- Active dot color -->
<solid android:color="#YOUR_COLOR" />

<!-- Inactive dot color -->
<solid android:color="#YOUR_COLOR" />
```

### Adjust Animation Duration

In `setCurrentIndicator()` method:

```java
dot.animate()
   .scaleX(scale)
   .scaleY(scale)
   .alpha(alpha)
   .setDuration(200)  // Change this value (milliseconds)
   .start();
```

### Change Indicator Size & Spacing

In `setupIndicators()`:

```java
// Size (in indicator_active_material.xml)
<size android:width="10dp" android:height="10dp" />

// Spacing
params.setMargins(12, 0, 12, 0);  // left, top, right, bottom
```

## 🎯 How It Works

### Flow Diagram

```
App Launch
    ↓
Check SharedPreferences
    ↓
┌─────────────────┬─────────────────┐
│   First Time    │   Returning     │
│   (not set)     │   (set = true)  │
└────────┬────────┴────────┬────────┘
         ↓                 ↓
   OnboardingActivity   MainActivity
         ↓
   User swipes through
   3 screens
         ↓
   "Get Started" button
         ↓
   Save preference
   (completed = true)
         ↓
   Navigate to MainActivity
```

### Key Components

1. **OnBoardItem** - Simple data model containing title, description, and image
2. **OnboardingAdapter** - RecyclerView adapter for ViewPager2
3. **PrefManger** - Handles SharedPreferences for tracking completion
4. **OnboardingActivity** - Main activity coordinating everything

### Animation Logic

```java
// Active indicator
scale = 1.4f, alpha = 1.0f

// Inactive indicator
scale = 1.0f, alpha = 0.5f
```

## 🔧 Advanced Features

### Alternative Animations

The code includes a bonus section with alternative animation ideas:

```java
// Bounce animation
ObjectAnimator bounce = ObjectAnimator.ofFloat(dot, "translationY", 0f, -10f, 0f);
bounce.setDuration(300).start();
```

### Separate Drawables for Indicators

If you prefer separate drawable files instead of a selector:

```java
// In setupIndicators()
dot.setImageDrawable(ContextCompat.getDrawable(
    getApplicationContext(), R.drawable.indicator_inactive));

// In setCurrentIndicator()
((ImageView) dot).setImageDrawable(ContextCompat.getDrawable(
    getApplicationContext(),
    i == index ? R.drawable.indicator_active : R.drawable.indicator_inactive));
```

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Onboarding shows every time | Check `PrefManger` implementation and SharedPreferences key |
| Images not showing | Verify image resources are in `res/drawable/` |
| Indicators not animating | Check if `indicator_active_material.xml` exists in `res/drawable/` |
| Button text not changing | Add `text_next` and `text_get_started` to `strings.xml` |
| Crash on launch | Verify all layout IDs match between XML and Java code |

## 📱 Testing Checklist

- [ ] Onboarding shows on first app launch
- [ ] Can swipe between all screens
- [ ] "Next" button advances to next screen
- [ ] Last screen shows "Get Started" button
- [ ] Indicators animate smoothly
- [ ] Active indicator is highlighted
- [ ] Onboarding skipped on subsequent launches
- [ ] Edge-to-edge display works correctly

## 🔄 Reset Onboarding (for Testing)

To see the onboarding again during development:

**Method 1: Clear app data**
- Settings → Apps → Your App → Storage → Clear Data

**Method 2: Add a debug button**
```java
// In MainActivity or settings
new PrefManger(this).setOnboardingCompleted(false);
```

## 📦 Production Checklist

Before deploying:

- [ ] Replace placeholder images with final designs
- [ ] Update all text content in `setupOnBoardingItems()`
- [ ] Test on different screen sizes
- [ ] Verify ProGuard rules (if using code obfuscation)
- [ ] Test on API 21+ (minimum supported version)
- [ ] Review and update colors to match brand

## 🎓 Learning Resources

- [ViewPager2 Documentation](https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2)
- [SharedPreferences Guide](https://developer.android.com/training/data-storage/shared-preferences)
- [Material Design - Onboarding](https://material.io/design/communication/onboarding.html)

## 📄 License

This implementation is provided as-is for educational and commercial use. Feel free to modify and use in your projects.

## 🤝 Contributing

Found a bug or have a suggestion? This is a template implementation - customize it for your needs!

---

**Created for easy reuse in Android Java projects** 🚀

**Version:** 1.0  
**Last Updated:** February 2026  
**Minimum SDK:** API 21 (Android 5.0)  
**Target SDK:** API 34 (Android 14)

# Modern Android Splash Screen Implementation

A production-ready splash screen implementation using Android 12+ SplashScreen API with backward compatibility, following Google's latest best practices and Material Design 3 guidelines.

## 📋 Overview

This implementation provides a modern, system-integrated splash screen that:
- **Uses the SplashScreen API** (Android 12+) with backward compatibility
- **Follows Material Design 3** guidelines
- **Provides smooth transitions** to your main activity
- **Supports both static and animated icons**
- **Handles edge-to-edge display** properly
- **Easy to customize and reuse**

## 🎯 Key Features

- ✅ Modern SplashScreen API (recommended by Google)
- ✅ Backward compatible with older Android versions
- ✅ Customizable icon, background, and branding
- ✅ Animated or static icon support
- ✅ Configurable display duration
- ✅ Proper activity lifecycle management
- ✅ Edge-to-edge display support
- ✅ Zero dependencies beyond AndroidX

## 📁 Project Structure

```
com.example.praticejava/
├── SplashActivity.java              # Splash screen activity
└── MainActivity.java                # Main app activity

res/
├── layout/
│   └── activity_splash_screen.xml   # Splash screen layout
├── drawable/
│   └── ic_logo.xml                  # Your app logo/icon
└── values/
    └── themes.xml                   # Splash screen theme configuration
```

## 🚀 Quick Start

### 1. Add Dependencies

In your `build.gradle (Module: app)`:

```gradle
dependencies {
    // SplashScreen API - REQUIRED
    implementation 'androidx.core:core-splashscreen:1.0.1'
    
    // Material Design 3
    implementation 'com.google.android.material:material:1.11.0'
    
    // AppCompat
    implementation 'androidx.appcompat:appcompat:1.6.1'
}
```

### 2. Copy the SplashActivity

Copy `SplashActivity.java` to your project:

```java
package com.example.praticejava;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
```

### 3. Create Splash Screen Layout

Create `res/layout/activity_splash_screen.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/white">

    <!-- Optional: Add any additional UI elements here if needed -->
    <!-- The SplashScreen API will handle displaying your icon automatically -->

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 4. Configure Splash Screen Theme

Create or update `res/values/themes.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Main app theme, applied after splash -->
    <style name="Theme.PraticeJava" parent="Theme.Material3.DayNight.NoActionBar">
        <!-- Your app's theme customizations go here -->
    </style>

    <!-- Splash Screen Theme -->
    <style name="Theme.App.Starting" parent="Theme.SplashScreen">
        
        <!-- REQUIRED: Background color of splash screen -->
        <item name="windowSplashScreenBackground">#FFFFFF</item>
        
        <!-- REQUIRED: Icon displayed on splash screen -->
        <!-- Use windowSplashScreenAnimatedIcon for animated icons (AVD) -->
        <item name="windowSplashScreenAnimatedIcon">@drawable/ic_logo</item>
        
        <!-- OPTIONAL: Static icon (alternative to animated) -->
        <!-- <item name="windowSplashScreenIcon">@drawable/your_static_icon</item> -->
        
        <!-- OPTIONAL: Branding image at bottom of splash screen -->
        <!-- <item name="windowSplashScreenBrandingImage">@drawable/your_brand</item> -->
        
        <!-- OPTIONAL: Animation duration (200ms - 1000ms) -->
        <item name="windowSplashScreenAnimationDuration">500</item>
        
        <!-- REQUIRED: Theme applied after splash screen -->
        <item name="postSplashScreenTheme">@style/Theme.PraticeJava</item>
        
    </style>
</resources>
```

### 5. Add Your App Icon

Create `res/drawable/ic_logo.xml` (example vector drawable):

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    
    <!-- Your app icon paths here -->
    <path
        android:fillColor="#6200EE"
        android:pathData="M54,54m-40,0a40,40 0,1 1,80 0a40,40 0,1 1,-80 0"/>
        
</vector>
```

Or use a PNG image in `res/drawable/ic_logo.png`

### 6. Update AndroidManifest.xml

Set `SplashActivity` as your launcher activity:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    
    <application
        android:icon="@drawable/ic_logo"
        android:label="@string/app_name"
        android:theme="@style/Theme.PraticeJava">
        
        <!-- Splash Screen Activity - LAUNCHER -->
        <activity
            android:name=".SplashActivity"
            android:exported="true"
            android:theme="@style/Theme.App.Starting">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        
        <!-- Main Activity -->
        <activity
            android:name=".MainActivity"
            android:exported="false" />
            
    </application>
    
</manifest>
```

## 🎨 Customization Guide

### Change Splash Duration

In `SplashActivity.java`, modify the delay value:

```java
new Handler().postDelayed(() -> {
    // Your navigation code
}, 2000);  // 2000 = 2 seconds, change to your preferred duration
```

**Recommended durations:**
- **1000ms (1 sec)** - Quick, modern feel
- **2000ms (2 sec)** - Standard duration (current)
- **3000ms (3 sec)** - Longer, allows logo appreciation

### Change Background Color

In `themes.xml`:

```xml
<item name="windowSplashScreenBackground">#YOUR_HEX_COLOR</item>
```

Examples:
- `#FFFFFF` - White
- `#000000` - Black
- `#6200EE` - Purple (Material)
- `#1976D2` - Blue

### Add Branding Image

In `themes.xml`, uncomment:

```xml
<item name="windowSplashScreenBrandingImage">@drawable/your_brand_logo</item>
```

Create `res/drawable/your_brand_logo.png` (recommended: 200x80dp)

### Use Animated Icon

Create an Animated Vector Drawable (AVD):

**res/drawable/ic_logo_animated.xml:**

```xml
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:aapt="http://schemas.android.com/aapt">
    
    <aapt:attr name="android:drawable">
        <vector
            android:width="108dp"
            android:height="108dp"
            android:viewportWidth="108"
            android:viewportHeight="108">
            <path
                android:name="logo_path"
                android:fillColor="#6200EE"
                android:pathData="M54,54m-40,0a40,40 0,1 1,80 0a40,40 0,1 1,-80 0"/>
        </vector>
    </aapt:attr>
    
    <target android:name="logo_path">
        <aapt:attr name="android:animation">
            <objectAnimator
                android:propertyName="pathData"
                android:duration="500"
                android:valueFrom="M54,54m-0,0a0,0 0,1 1,0 0a0,0 0,1 1,0 0"
                android:valueTo="M54,54m-40,0a40,40 0,1 1,80 0a40,40 0,1 1,-80 0"
                android:valueType="pathType"/>
        </aapt:attr>
    </target>
    
</animated-vector>
```

Then in `themes.xml`:

```xml
<item name="windowSplashScreenAnimatedIcon">@drawable/ic_logo_animated</item>
```

### Customize Status Bar During Splash

In `themes.xml`:

```xml
<item name="android:statusBarColor">#FFFFFF</item>
<item name="android:navigationBarColor">#FFFFFF</item>
<item name="android:windowLightStatusBar">true</item>
```

## 🔧 How It Works

### Flow Diagram

```
App Launch
    ↓
System displays SplashScreen
(using Theme.App.Starting)
    ↓
SplashActivity.onCreate()
    ↓
Wait 2 seconds
(Handler.postDelayed)
    ↓
Launch MainActivity
with flags:
- FLAG_ACTIVITY_NEW_TASK
- FLAG_ACTIVITY_CLEAR_TASK
    ↓
finish() SplashActivity
    ↓
MainActivity displayed
(using Theme.PraticeJava)
```

### Activity Launch Flags Explained

```java
intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
```

- **FLAG_ACTIVITY_NEW_TASK** - Creates MainActivity in a new task
- **FLAG_ACTIVITY_CLEAR_TASK** - Removes SplashActivity from back stack

**Result:** User can't navigate back to splash screen with back button ✅

### SplashScreen API Lifecycle

1. **System handles splash display** - No manual UI needed
2. **Icon fade-in animation** - Automatic (if using animated icon)
3. **Activity loads** - While splash is visible
4. **Smooth transition** - To your main activity

## 📱 Android Version Compatibility

| Android Version | API Level | Support |
|----------------|-----------|---------|
| Android 12+ | API 31+ | ✅ Native SplashScreen API |
| Android 11 | API 30 | ✅ Backward compatible (library) |
| Android 10 | API 29 | ✅ Backward compatible (library) |
| Android 9 | API 28 | ✅ Backward compatible (library) |
| Android 5.0+ | API 21+ | ✅ Fully supported |

The `core-splashscreen` library provides backward compatibility for pre-Android 12 devices.

## 🎯 Best Practices

### ✅ DO

- **Use vector drawables** for icons (scalable, smaller file size)
- **Keep splash duration short** (1-2 seconds max)
- **Match brand colors** for consistency
- **Test on multiple devices** and Android versions
- **Use FLAG_ACTIVITY_CLEAR_TASK** to prevent back navigation
- **Keep splash screen simple** - just logo and background

### ❌ DON'T

- **Don't show splash every time** - Only on cold start
- **Don't add complex animations** - Keep it simple
- **Don't use large image files** - Optimize for size
- **Don't perform heavy operations** - Initialize in MainActivity
- **Don't skip SplashScreen API** - Use the modern approach
- **Don't allow back navigation** to splash screen

## 🚫 Common Mistakes to Avoid

### 1. Wrong Theme Assignment

**❌ Wrong:**
```xml
<application android:theme="@style/Theme.App.Starting">
```

**✅ Correct:**
```xml
<activity
    android:name=".SplashActivity"
    android:theme="@style/Theme.App.Starting">
```

### 2. Missing postSplashScreenTheme

Always define the theme to use after splash:

```xml
<item name="postSplashScreenTheme">@style/Theme.PraticeJava</item>
```

### 3. Forgetting Activity Flags

Always clear the task to prevent back navigation:

```java
intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
```

### 4. Using finish() Before Navigation

**❌ Wrong:**
```java
finish();
startActivity(intent);
```

**✅ Correct:**
```java
startActivity(intent);
finish();
```

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Splash screen not showing | Check if `android:theme="@style/Theme.App.Starting"` is set on SplashActivity |
| Icon not displaying | Verify `ic_logo` exists in `res/drawable/` |
| Can navigate back to splash | Ensure `FLAG_ACTIVITY_CLEAR_TASK` is added to intent |
| Splash shows too long/short | Adjust `postDelayed` duration in milliseconds |
| Crash on older Android | Add `core-splashscreen` dependency |
| White flash after splash | Check `postSplashScreenTheme` matches main theme background |
| Icon appears pixelated | Use vector drawable instead of PNG |

## 🔄 Migration from Old Splash Screen

If you have an old splash screen implementation:

### Old Way (Deprecated)
```java
// Using a timed Activity with custom layout
Thread.sleep(2000);
```

### New Way (Recommended)
```java
// Using SplashScreen API + Handler
new Handler().postDelayed(() -> { ... }, 2000);
```

**Benefits of new approach:**
- System-integrated
- Better performance
- Follows Material Design
- Automatic animations
- Backward compatible

## 🧪 Testing Checklist

- [ ] Splash screen displays on app launch
- [ ] Icon/logo appears centered
- [ ] Background color matches design
- [ ] Transition to MainActivity is smooth
- [ ] Cannot navigate back to splash screen
- [ ] Works on Android 12+
- [ ] Works on Android 11 and below
- [ ] No memory leaks (check with LeakCanary)
- [ ] Edge-to-edge display works correctly
- [ ] Status bar color is correct

## 📦 Production Checklist

Before deploying:

- [ ] Replace `ic_logo` with final app icon
- [ ] Verify background color matches brand
- [ ] Test on real devices (not just emulators)
- [ ] Check on different screen sizes (phone, tablet)
- [ ] Optimize icon file size
- [ ] Review splash duration (2 seconds max recommended)
- [ ] Test cold start performance
- [ ] Remove any debug logging
- [ ] Enable ProGuard rules if using code obfuscation

## 🎓 Advanced Customization

### 1. Keep Splash Screen Visible Longer Programmatically

In `SplashActivity.java`:

```java
import androidx.core.splashscreen.SplashScreen;

@Override
protected void onCreate(Bundle savedInstanceState) {
    // Install splash screen
    SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
    
    // Keep splash screen on-screen indefinitely
    splashScreen.setKeepOnScreenCondition(() -> true);
    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);
    
    // Your delayed navigation code...
}
```

### 2. Custom Exit Animation

```java
splashScreen.setOnExitAnimationListener(splashScreenView -> {
    ObjectAnimator slideUp = ObjectAnimator.ofFloat(
        splashScreenView,
        View.TRANSLATION_Y,
        0f,
        -splashScreenView.getHeight()
    );
    slideUp.setInterpolator(new AnticipateInterpolator());
    slideUp.setDuration(500L);
    slideUp.addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            splashScreenView.remove();
        }
    });
    slideUp.start();
});
```

### 3. Load Data During Splash

```java
private void loadAppData() {
    new Handler().postDelayed(() -> {
        // Simulate data loading
        // Initialize database, fetch config, etc.
        
        navigateToMainActivity();
    }, 2000);
}

private void navigateToMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    finish();
}
```

## 📚 Resources

### Official Documentation
- [SplashScreen API Guide](https://developer.android.com/develop/ui/views/launch/splash-screen)
- [Material Design - Launch Screen](https://m3.material.io/styles/motion/transitions/applying-transitions#d64f8224-96e3-44ac-a69e-e09e5f067e8f)
- [AndroidX SplashScreen Library](https://developer.android.com/jetpack/androidx/releases/core#core-splashscreen-1.0.0)

### Additional Resources
- [Vector Asset Studio](https://developer.android.com/studio/write/vector-asset-studio)
- [Animated Vector Drawables](https://developer.android.com/develop/ui/views/graphics/drawable-animation)
- [Edge-to-Edge Display](https://developer.android.com/develop/ui/views/layout/edge-to-edge)

## 📄 License

This implementation is provided as-is for educational and commercial use. Feel free to modify and use in your projects.

## 💡 Tips for Success

1. **Keep it simple** - Splash screens should be minimal
2. **Make it fast** - Users want to get into your app quickly
3. **Match your brand** - Use consistent colors and logo
4. **Test thoroughly** - Check on multiple devices and OS versions
5. **Follow Material Design** - Use Google's guidelines
6. **Optimize assets** - Small file sizes = faster loading

---

**Ready to implement in your Android Java project** 🚀

**Recommended by:** Google Android Team  
**Minimum SDK:** API 21 (Android 5.0)  
**Target SDK:** API 34 (Android 14)  
**Last Updated:** February 2026

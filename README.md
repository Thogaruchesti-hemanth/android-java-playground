# Android Home Screen Widget - Simple Implementation

A minimal, reusable Android home screen widget that displays the current time. Perfect as a starting template for creating custom widgets.

## 📋 Overview

This is a basic Android widget implementation that:
- Displays current time (HH:mm format)
- Updates when manually refreshed
- Uses `AppWidgetProvider` (standard Android widget API)
- Minimal code - easy to understand and extend

## 📁 Files Included

```
com.example.praticejava/
└── ExampleWidget.java          # Widget provider class

res/
├── layout/
│   └── widget_layout.xml       # Widget UI layout
└── xml/
    └── widget_info.xml          # Widget configuration
```

## 🚀 Quick Setup

### 1. Copy the Widget Class

```java
package com.example.praticejava;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExampleWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int widgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
            views.setTextViewText(R.id.tvTime, time);
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }
}
```

### 2. Create Widget Layout

Create `res/layout/widget_layout.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:background="@drawable/widget_background"
    android:gravity="center">

    <TextView
        android:id="@+id/tvTime"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="00:00"
        android:textSize="32sp"
        android:textStyle="bold"
        android:textColor="#FFFFFF" />

</LinearLayout>
```

### 3. Create Widget Background (Optional)

Create `res/drawable/widget_background.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="#80000000" />
    <corners android:radius="16dp" />
</shape>
```

### 4. Create Widget Configuration

Create `res/xml/widget_info.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
    android:minWidth="110dp"
    android:minHeight="40dp"
    android:updatePeriodMillis="0"
    android:initialLayout="@layout/widget_layout"
    android:description="@string/widget_description"
    android:resizeMode="horizontal|vertical"
    android:widgetCategory="home_screen" />
```

### 5. Add String Resource

In `res/values/strings.xml`:

```xml
<string name="widget_description">Displays current time</string>
```

### 6. Register Widget in Manifest

In `AndroidManifest.xml`:

```xml
<receiver
    android:name=".ExampleWidget"
    android:exported="true">
    <intent-filter>
        <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
    </intent-filter>
    <meta-data
        android:name="android.appwidget.provider"
        android:resource="@xml/widget_info" />
</receiver>
```

## 🎨 Customization

### Change Time Format

```java
// 24-hour format
String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

// 12-hour format with AM/PM
String time = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

// Include seconds
String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
```

### Display Different Content

```java
// Show date instead
String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
views.setTextViewText(R.id.tvTime, date);

// Show custom text
views.setTextViewText(R.id.tvTime, "Hello Widget!");
```

### Change Widget Size

In `widget_info.xml`:

```xml
<!-- Small widget -->
android:minWidth="110dp"
android:minHeight="40dp"

<!-- Medium widget -->
android:minWidth="180dp"
android:minHeight="110dp"

<!-- Large widget -->
android:minWidth="250dp"
android:minHeight="180dp"
```

## 📱 How to Add Widget

1. Long press on home screen
2. Tap "Widgets"
3. Find "ExampleWidget" or your app name
4. Drag to home screen

## 🔄 Auto-Update (Optional)

To make the widget update automatically:

### Option 1: Update Period (Limited)

In `widget_info.xml`:

```xml
<!-- Update every 30 minutes (minimum allowed) -->
android:updatePeriodMillis="1800000"
```

**Note:** Android limits minimum update period to 30 minutes to save battery.

### Option 2: AlarmManager (For Frequent Updates)

```java
@Override
public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    // Update widget immediately
    updateWidget(context, appWidgetManager, appWidgetIds);
    
    // Schedule periodic updates using AlarmManager
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, ExampleWidget.class);
    intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 
        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    
    // Update every minute
    alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 
        60000, pendingIntent);
}
```

## 🎯 Common Use Cases

### 1. Counter Widget

```java
SharedPreferences prefs = context.getSharedPreferences("widget_prefs", Context.MODE_PRIVATE);
int count = prefs.getInt("count", 0);
views.setTextViewText(R.id.tvTime, String.valueOf(count));
```

### 2. Random Quote Widget

```java
String[] quotes = {"Be yourself", "Stay positive", "Keep learning"};
String randomQuote = quotes[new Random().nextInt(quotes.length)];
views.setTextViewText(R.id.tvTime, randomQuote);
```

### 3. Battery Level Widget

```java
IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
Intent batteryStatus = context.registerReceiver(null, ifilter);
int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
views.setTextViewText(R.id.tvTime, level + "%");
```

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Widget not appearing | Check if receiver is registered in manifest |
| Widget shows "00:00" | Verify `R.id.tvTime` matches layout |
| Widget not updating | Check `updatePeriodMillis` or implement AlarmManager |
| Layout looks wrong | Test on actual device, not just emulator |

## 📚 What You Can Build

This template is perfect for:
- ✅ Clock widgets
- ✅ Quote of the day widgets
- ✅ Counter/tracker widgets
- ✅ Weather widgets
- ✅ Simple info display widgets
- ✅ App shortcuts

## 💡 Next Steps

To extend this widget:

1. **Add click actions** - Make widget interactive
2. **Add configuration activity** - Let users customize
3. **Fetch data from API** - Show live information
4. **Add multiple sizes** - Support different widget dimensions
5. **Improve design** - Add icons, colors, animations

## 📖 Resources

- [Android Widgets Guide](https://developer.android.com/develop/ui/views/appwidgets/overview)
- [RemoteViews Documentation](https://developer.android.com/reference/android/widget/RemoteViews)

---

**Simple widget template ready to customize** 🚀

**Minimum SDK:** API 21 (Android 5.0)  
**Last Updated:** February 2026

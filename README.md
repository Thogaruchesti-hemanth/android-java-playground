# Android Home Screen Widget - Click to Open Activity

A minimal Android home screen widget built in Java that launches an Activity dialog on tap. An extension of the simple widget template, adding click interaction via `PendingIntent`.

## What's New vs Simple Widget

The only change from the base template — the widget is now clickable. Tapping it fires a `PendingIntent` that opens an `Activity` styled as a dialog, without leaving the home screen.

## 📁 Files Included

```
com.example.praticejava/
├── ExampleWidget.java          # Widget provider with click handling
└── WidgetDialogActivity.java   # Activity with dialog theme
res/
├── layout/
│   └── widget_layout.xml       # Widget UI layout
└── xml/
    └── widget_info.xml          # Widget configuration
```

## 🚀 Key Change — Adding Click to Open Activity

### 1. In your Widget Provider class

```java
@Override
public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    for (int widgetId : appWidgetIds) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        // Create intent pointing to your dialog activity
        Intent intent = new Intent(context, WidgetDialogActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Attach click to the root view
        views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

        appWidgetManager.updateAppWidget(widgetId, views);
    }
}
```

### 2. Create your Dialog Activity

```java
public class WidgetDialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_dialog);
    }
}
```

### 3. Style it as a Dialog in AndroidManifest.xml

```xml
<activity
    android:name=".WidgetDialogActivity"
    android:theme="@style/Theme.AppCompat.Dialog"
    android:exported="false" />
```

### 4. Register the Widget Receiver

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

## 💡 What You Can Learn From This

- **`PendingIntent.getActivity()`** — how widgets communicate with the rest of your app through intents
- **`RemoteViews.setOnClickPendingIntent()`** — the only way to attach click listeners to widget views since lambdas and `setOnClickListener` don't work in widgets
- **Activity as a Dialog** — applying `Theme.AppCompat.Dialog` to an Activity so it floats over the home screen instead of opening full screen
- **`FLAG_IMMUTABLE`** — required on API 31+ when creating PendingIntents

## 📱 How to Add Widget

1. Long press on home screen
2. Tap "Widgets"
3. Find your app name
4. Drag to home screen
5. Tap the widget — dialog opens over the home screen

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Click does nothing | Check `R.id.widget_root` matches your layout's root view id |
| App crashes on click | Verify `WidgetDialogActivity` is registered in manifest |
| Dialog opens fullscreen | Confirm `Theme.AppCompat.Dialog` is set in manifest |
| PendingIntent warning | Add `FLAG_IMMUTABLE` flag on API 31+ |

## 📖 Resources

- [Android Widgets Guide](https://developer.android.com/develop/ui/views/appwidgets/overview)
- [RemoteViews Documentation](https://developer.android.com/reference/android/widget/RemoteViews)
- [PendingIntent Documentation](https://developer.android.com/reference/android/app/PendingIntent)

---

**Minimum SDK:** API 21 (Android 5.0)  
**Language:** Java  
**Last Updated:** February 2026

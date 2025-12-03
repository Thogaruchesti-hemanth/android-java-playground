package com.example.praticejava;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ClickableWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int widgetId : appWidgetIds) {

            // RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_clickable);

            // Intent to open your activity
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Wrap in PendingIntent
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            // Assign click action to whole widget
            views.setOnClickPendingIntent(R.id.txtWidgetTitle, pendingIntent);

            // Update widget
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }
}

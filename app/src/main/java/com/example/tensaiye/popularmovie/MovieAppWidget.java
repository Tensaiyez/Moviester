package com.example.tensaiye.popularmovie;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.example.tensaiye.popularmovie.Activity.DetailActivity;
import com.example.tensaiye.popularmovie.Activity.MainActivity;
import com.example.tensaiye.popularmovie.Models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class MovieAppWidget extends AppWidgetProvider {
    List<String> movies = new ArrayList<>();
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object

        Intent intent=new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =   PendingIntent.getActivity(context,0,intent,0);


        Intent serviceIntent=new Intent(context,MovieService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.movie_app_widget);


        views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);
        views.setRemoteAdapter(R.id.widgetStackView,serviceIntent);

        views.setEmptyView(R.id.widgetStackView,R.id.EmptyStack);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Intent intent=new Intent(context, MainActivity.class);
            PendingIntent pendingIntent =   PendingIntent.getActivity(context,0,intent,0);


//        String name = context.getSharedPreferences("MovieName",MODE_PRIVATE).getString("name","no movies");

            Intent serviceIntent=new Intent(context,MovieService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.movie_app_widget);
            views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);
//        views.setTextViewText(R.id.appwidget_text, name);
//            SharedPreferences sharedPreferences = context.getSharedPreferences("MovieName", MODE_PRIVATE);
//
//            for (int i = 0; i <1 ; i++) {
//                movies.add(sharedPreferences.getString("Movie" + i, null));
//                Log.d("images", " " + movies.get(i));

//                Picasso.with(context)
//                        .load(movies.get(i))
//                        .into(views, R.id.widget_image, new int[]{appWidgetId});
//            }

            views.setRemoteAdapter(R.id.widgetStackView,serviceIntent);
            views.setEmptyView(R.id.widgetStackView,R.id.EmptyStack);

        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


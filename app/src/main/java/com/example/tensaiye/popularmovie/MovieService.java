package com.example.tensaiye.popularmovie;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.example.tensaiye.popularmovie.Activity.MainActivity;
import com.example.tensaiye.popularmovie.Database.FavoriteEntry;
import com.example.tensaiye.popularmovie.Database.MovieDatabase;
import com.example.tensaiye.popularmovie.Models.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MovieService extends RemoteViewsService {
    private MovieDatabase mDb;
    private List<String> movie=new ArrayList<>();
    private List<String> movieRating=new ArrayList<>();
    private List<String> movies=new ArrayList<>();
    List<FavoriteEntry> favorites;
    SharedPreferences shared;
    int widget_item_count=19;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return  new MovieWidgetFactory(getApplicationContext(),intent);
    }

  class MovieWidgetFactory implements RemoteViewsFactory {
      private Context context;
      private int appWidgetId;



        public MovieWidgetFactory(Context applicationContext, Intent intent) {
            this.context=applicationContext;
            appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);

        }

      @Override
      public void onCreate() {
          shared = getSharedPreferences("MovieName", MODE_PRIVATE);

          for(int i=0;i<widget_item_count;i++) {
              movie.add(shared.getString("Movie" + i, null));
              Log.d("titlee", "  " + movie.get(i));
          }
          for(int i=0;i<widget_item_count;i++) {
              movieRating.add(shared.getString("Rating" + i, null));
              Log.d("Rating", "  " + movieRating.get(i));
          }

//          for (int i = 0; i <19 ; i++) {
//              movies.add(shared.getString("Image"+i,null));
//              Log.d("images", " " + movies.get(i));
//              Log.d("imageSize"," "+movies.size());
//
//          }


        }

      @Override
      public void onDataSetChanged() {

      }

      @Override
      public void onDestroy() {

      }

      @Override
      public int getCount() {
          return movie.size();
      }

      @Override
      public RemoteViews getViewAt(int position) {
          RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_movie_item);
          views.setTextViewText(R.id.widget_item_text,movie.get(position));
          views.setTextViewText(R.id.widget_rating,movieRating.get(position));
//          try {
//              Bitmap bitmap = Glide.with(context)
//                      .asBitmap()
//                      .load(movie.get(0))
//                      .submit(512, 512)
//                      .get();
//
//              views.setImageViewBitmap(R.id.widget_image, bitmap);
//          } catch (Exception e) {
//              e.printStackTrace();
//          }
//                          Picasso.with(context)
//                        .load(movie.get(position))
//                        .into(views,R.id.widget_image, new int[] { appWidgetId });

            return views;
      }

      @Override
      public RemoteViews getLoadingView() {
          return null;
      }

      @Override
      public int getViewTypeCount() {
          return 1;
      }

      @Override
      public long getItemId(int position) {
          return position;
      }

      @Override
      public boolean hasStableIds() {
          return true;
      }
  }
}

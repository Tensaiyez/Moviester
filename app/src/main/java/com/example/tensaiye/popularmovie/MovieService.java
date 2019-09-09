package com.example.tensaiye.popularmovie;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.example.tensaiye.popularmovie.Database.MovieDatabase;
import java.util.ArrayList;
import java.util.List;


public class MovieService extends RemoteViewsService {
    private MovieDatabase mDb;
    private List<String> movie=new ArrayList<>();
    private List<String> movieRating=new ArrayList<>();
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
          shared = getSharedPreferences(Constants.SP_Name, MODE_PRIVATE);
          for(int i=0;i<widget_item_count;i++) {
              movie.add(shared.getString(Constants.MovieTitle_SP + i, null));
              Log.d("Title", "  " + movie.get(i));
          }
          for(int i=0;i<widget_item_count;i++) {
              movieRating.add(shared.getString(Constants.MovieRating_SP + i, null));
              Log.d("Rating", "  " + movieRating.get(i));
          }
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

package com.example.tensaiye.popularmovie;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class MovieService extends RemoteViewsService {
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

      }

      @Override
      public void onDataSetChanged() {

      }

      @Override
      public void onDestroy() {

      }

      @Override
      public int getCount() {
          return 0;
      }

      @Override
      public RemoteViews getViewAt(int position) {
          return null;
      }

      @Override
      public RemoteViews getLoadingView() {
          return null;
      }

      @Override
      public int getViewTypeCount() {
          return 0;
      }

      @Override
      public long getItemId(int position) {
          return 0;
      }

      @Override
      public boolean hasStableIds() {
          return false;
      }
  }
}

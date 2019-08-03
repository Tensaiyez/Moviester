package com.example.tensaiye.popularmovie.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {FavoriteEntry.class},version = 2,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private static final String LOG_TAG=MovieDatabase.class.getSimpleName();
    private static final Object LOCK=new Object();
    private static final String DATABASE_NAME="FavoriteList";
    private static MovieDatabase sInstance;

    public static MovieDatabase getInstance(Context context){
        if(sInstance==null){
            synchronized (LOCK){
                Log.d(LOG_TAG,"Creating a new database instance");
                sInstance= Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class,MovieDatabase.DATABASE_NAME).fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG,"Getting the database instance");
        return sInstance;
    }
    public abstract FavoriteDao favoriteDao();
}

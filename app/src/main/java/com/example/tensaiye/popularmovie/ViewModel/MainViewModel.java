package com.example.tensaiye.popularmovie.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tensaiye.popularmovie.Database.FavoriteEntry;
import com.example.tensaiye.popularmovie.Database.MovieDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG=MainViewModel.class.getSimpleName();
    private LiveData<List<FavoriteEntry>> favorites;

    public MainViewModel(@NonNull Application application) {
        super(application);
        MovieDatabase database=MovieDatabase.getInstance(this.getApplication());

        Log.d(TAG,"Actively retreiving favorites");
        favorites=database.favoriteDao().loadAllFavorite();

    }

    public LiveData<List<FavoriteEntry>> getFavorites(){
        return favorites;
    }
}

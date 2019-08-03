package com.example.tensaiye.popularmovie.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tensaiye.popularmovie.Database.FavoriteEntry;
import com.example.tensaiye.popularmovie.Database.MovieDatabase;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {
    private static final String TAG ="detailViewModel" ;
    private LiveData<List<String>> favoritesById;
    private String id;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        MovieDatabase database2=MovieDatabase.getInstance(this.getApplication());
        Log.d(TAG,"Actively retreiving string");
        favoritesById=database2.favoriteDao().loadFavoriteById(id);
    }
    public LiveData<List<String>> getFavorites(){
        return favoritesById;
    }
}

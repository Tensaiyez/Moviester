package com.example.tensaiye.popularmovie.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    LiveData<List<FavoriteEntry>>loadAllFavorite();
    @Query("SELECT * FROM favorite")
    List<FavoriteEntry>getAllFavorite();

    @Query("DELETE FROM favorite WHERE movie_id=:Favid")
    public void deleteFavorite(String Favid);

    @Insert
    void insertFavorite(FavoriteEntry favoriteEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavorite(FavoriteEntry favoriteEntry);

    @Delete
    void deleteFavorite(FavoriteEntry favoriteEntry);

    @Query("SELECT movie_id FROM favorite WHERE movie_id=:id")
    LiveData<List<String>>loadFavoriteById(String id);

}

package com.example.tensaiye.popularmovie.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Favorite")
public class FavoriteEntry {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "movie_id")
    private String movie_id;
    @ColumnInfo(name = "poster")
    private String poster;
    @ColumnInfo(name ="rating")
    private String rating;

    @Ignore
    public FavoriteEntry(String title, String movie_id, String poster,String rating) {
        this.title = title;
        this.movie_id = movie_id;
        this.poster = poster;
        this.rating=rating;
    }

    public FavoriteEntry(int id, String title, String movie_id, String poster,String rating) {
        this.id = id;
        this.title = title;
        this.movie_id = movie_id;
        this.poster = poster;
        this.rating=rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getRating(){
        return rating;
    }
    public void setRating(String rating){
        this.rating=rating;
    }
}


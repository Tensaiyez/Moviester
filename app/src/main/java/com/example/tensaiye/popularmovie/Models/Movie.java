package com.example.tensaiye.popularmovie.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Movie implements Parcelable {
    public static final String movieImagepath = "http://image.tmdb.org/t/p/original/";

    @SerializedName("original_title")
    private String OriginalName;
    @SerializedName("poster_path")
    private String PosterImage;
    @SerializedName("overview")
    private String OverView;
    @SerializedName("vote_average")
    private String UserRating;
    @SerializedName("release_date")
    private String ReleaseDate;
    @SerializedName("backdrop_path")
    private String Backdrop;
    @SerializedName("id")
    private String Id;
    @SerializedName("vote_count")
    private String Vote;



    public Movie(Parcel in) {
        this.ReleaseDate = in.readString();
        this.UserRating = in.readString();
        this.OverView = in.readString();
        this.OriginalName = in.readString();
        this.PosterImage = in.readString();
        this.Backdrop = in.readString();
        this.Id = in.readString();
        this.Vote = in.readString();
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOriginalName() {
        return OriginalName;
    }

    public void setOriginalName(String OriginalName) {
        this.OriginalName = OriginalName;
    }

    public String getPosterImage() {
        return movieImagepath + PosterImage;
    }

    public void setPosterImage(String PosterImage) {
        this.PosterImage = PosterImage;
    }

    public String getOverView() {
        return OverView;
    }

    public void setOverView(String OverView) {
        this.OverView = OverView;
    }

    public String getUserRating() {
        return UserRating;
    }

    public void setUserRating(String UserRating) {
        this.UserRating = UserRating;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }

    public String getBackdrop() {
        return movieImagepath + Backdrop;
    }

    public void setBackdrop(String Backdrop) {
        this.Backdrop = Backdrop;
    }

    public String getVote() {
        return Vote;
    }

    public void setVote(String Vote) {
        this.Vote = Vote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(OriginalName);
        parcel.writeString(UserRating);
        parcel.writeString(PosterImage);
        parcel.writeString(OverView);
        parcel.writeString(ReleaseDate);
        parcel.writeString(Backdrop);
        parcel.writeString(Id);
        parcel.writeString(Vote);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
}
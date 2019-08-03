package com.example.tensaiye.popularmovie.API;

import com.example.tensaiye.popularmovie.Models.BasicCredit;
import com.example.tensaiye.popularmovie.Models.BasicReview;
import com.example.tensaiye.popularmovie.Models.BasicTrailer;
import com.example.tensaiye.popularmovie.Models.Basicmovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceInterface {


    @GET("movie/{sort}")
    Call<Basicmovie> getMovies(@Path("sort")String sort,@Query("api_key")String apikey);

    @GET("movie/{movie_id}/reviews")
    Call<BasicReview> getReviews(@Path("movie_id") String movie_id, @Query("api_key")String apikey);

    @GET("movie/{movie_id}/videos")
    Call<BasicTrailer> getTrailer(@Path("movie_id") String movie_id, @Query("api_key")String apikey);
    @GET("movie/{movie_id}/credits")
    Call<BasicCredit>getCredit(@Path("movie_id") String movie_id, @Query("api_key")String apikey);
}

package com.example.tensaiye.popularmovie.API;

import com.example.tensaiye.popularmovie.Basicmovie;
import com.example.tensaiye.popularmovie.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceInterface {


    @GET("movie/{sort}")
    Call<Basicmovie> getMovies(@Path("sort")String sort,@Query("api_key")String apikey);
}

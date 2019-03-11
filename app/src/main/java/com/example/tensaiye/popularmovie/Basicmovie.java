package com.example.tensaiye.popularmovie;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Basicmovie {

    @SerializedName("results")
    private List<Movie> results;
    @SerializedName("total_results")
    private int totalResults;


    public List<Movie> getResults() {
        return results;
    }
    public void setResults(List<Movie> results) {
        this.results = results;
    }

}

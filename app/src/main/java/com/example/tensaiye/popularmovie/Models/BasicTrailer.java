package com.example.tensaiye.popularmovie.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasicTrailer {
    @SerializedName("results")
    private List<Trailer> results;

    public List<Trailer> getResults() {
        return results;
    }

    public void setResult(List<Trailer> results) {
        this.results = results;
    }
}

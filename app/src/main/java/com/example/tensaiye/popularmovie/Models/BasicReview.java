package com.example.tensaiye.popularmovie.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasicReview {
    @SerializedName("results")
    private List<Review> results;

    public List<Review> getResults() {
        return results;
    }

    public void setResult(List<Review> results) {
        this.results = results;
    }
}

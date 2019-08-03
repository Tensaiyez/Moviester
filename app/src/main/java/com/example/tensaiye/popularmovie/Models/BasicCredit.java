package com.example.tensaiye.popularmovie.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasicCredit {
    @SerializedName("cast")
    private List<Credit> cast;

    public List<Credit> getCast() {
        return cast;
    }
    public void setCast(List<Credit>cast) {
        this.cast = cast;
    }


}

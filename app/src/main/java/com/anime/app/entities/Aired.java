package com.anime.app.entities;

import com.google.gson.annotations.SerializedName;

public class Aired {
    @SerializedName("from")
    private String from;

    public String getFrom() {
        return from;
    }
}
package com.anime.app.data;

import com.anime.app.entities.Episode;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EpisodeResponse {
    @SerializedName("data")
    private List<Episode> episodes;

    public List<Episode> getEpisodes() {
        return episodes;
    }
}

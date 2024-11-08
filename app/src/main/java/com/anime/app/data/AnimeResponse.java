package com.anime.app.data;



import com.anime.app.entities.Anime;

import java.util.List;

public class AnimeResponse {
    private List<Anime> data; // Los datos están en un array llamado "data".

    public List<Anime> getData() {
        return data;
    }
}

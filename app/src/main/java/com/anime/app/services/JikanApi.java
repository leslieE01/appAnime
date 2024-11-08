package com.anime.app.services;

import com.anime.app.data.AnimeResponse;
import com.anime.app.data.EpisodeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JikanApi {
    @GET("anime")
    Call<AnimeResponse> getAnimes();

    // Método para obtener la lista de animes mediante búsqueda
    @GET("anime")
    Call<AnimeResponse> getAnimeSearch(@Query("q") String query); // Puedes cambiar `query` por el término de búsqueda específico si es necesario


    // Método para obtener la lista de episodios de un anime específico
    @GET("anime/{id}/episodes")
    Call<EpisodeResponse> getAnimeEpisodes(@Path("id") int animeId);
}

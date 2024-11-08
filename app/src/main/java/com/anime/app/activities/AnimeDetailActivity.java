package com.anime.app.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anime.app.adapters.EpisodeAdapter;
import com.anime.app.data.ApiService;
import com.anime.app.data.EpisodeResponse;
import com.anime.app.R;
import com.anime.app.entities.Episode;
import com.anime.app.services.JikanApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EpisodeAdapter episodeAdapter;
    private List<Episode> episodeList;
    private TextView title, airedDate, episodes;
    private int animeId;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detail);


        title = findViewById(R.id.detail_title);
        airedDate = findViewById(R.id.detail_aired_date);


        recyclerView = findViewById(R.id.recycler_view_episodes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener datos pasados desde MainActivity
        animeId = getIntent().getIntExtra("animeId", 0);
        title.setText(getIntent().getStringExtra("title"));
        airedDate.setText("Fecha de Publicación: " + getIntent().getStringExtra("airedDate"));


        // Llamada para obtener episodios
        JikanApi apiService = ApiService.getRetrofitInstance().create(JikanApi.class);
        Call<EpisodeResponse> call = apiService.getAnimeEpisodes(animeId);

        call.enqueue(new Callback<EpisodeResponse>() {
            @Override
            public void onResponse(Call<EpisodeResponse> call, Response<EpisodeResponse> response) {
                if (response.body() != null) {
                    episodeList = response.body().getEpisodes();

                    if (episodeList == null || episodeList.isEmpty()) {
                        // Si la lista de episodios está vacía
                        episodeList = null;
                    }
                    // Configurar el RecyclerView con la lista de episodios
                    episodeAdapter = new EpisodeAdapter(episodeList);
                    recyclerView.setAdapter(episodeAdapter);
                } else {
                    Log.e("AnimeDetailActivity", "No se obtuvieron episodios");
                }
            }

            @Override
            public void onFailure(Call<EpisodeResponse> call, Throwable t) {
                Log.e("AnimeDetailActivity", "Error: " + t.getMessage());
            }
        });
    }
}
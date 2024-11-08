package com.anime.app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anime.app.adapters.AnimeAdapter;
import com.anime.app.data.AnimeResponse;
import com.anime.app.data.ApiService;
import com.anime.app.entities.Anime;
import com.anime.app.services.JikanApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AnimeAdapter animeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Llamada a getAnimeSearch para obtener la lista de animes
        JikanApi apiService = ApiService.getRetrofitInstance().create(JikanApi.class);
        //Call<AnimeResponse> call = apiService.getAnimeSearch("naruto"); // Cambia "naruto" por el término de búsqueda deseado
        Call<AnimeResponse> call = apiService.getAnimes();
        call.enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                if (response.body() != null) {
                    List<Anime> animeList = response.body().getData();
                    animeAdapter = new AnimeAdapter(animeList, MainActivity.this);
                    recyclerView.setAdapter(animeAdapter);
                }
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }

}
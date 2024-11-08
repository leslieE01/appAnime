package com.anime.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.anime.app.R;
import com.anime.app.entities.Episode;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private List<Episode> episodeList;

    // Constructor
    public EpisodeAdapter(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_episode, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        holder.title.setText(episode.getTitle());
        holder.airedDate.setText(episode.getAired());
    }

    @Override
    public int getItemCount() {
        return episodeList != null ? episodeList.size() : 0;
    }

    // ViewHolder para cada elemento de episodio
    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {

        public TextView title, airedDate;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.episode_title);
            airedDate = itemView.findViewById(R.id.episode_aired_date);
        }
    }
}

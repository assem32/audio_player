package com.example.song.home.presentation.Home.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.song.R;
import com.example.song.home.domain.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class HomeSongRecycler extends RecyclerView.Adapter<HomeSongRecycler.Holder> {

    private List<SongModel> songModelArrayList = new ArrayList<>();

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList (List<SongModel> songModelArrayList){
        this.songModelArrayList=songModelArrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(songModelArrayList.get(position).getTitle());
        holder.artist.setText(songModelArrayList.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return songModelArrayList != null ? songModelArrayList.size():0;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView name;
        TextView artist;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.song_name);
            artist = itemView.findViewById(R.id.song_artist);
            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClick.onClick(songModelArrayList.get(getLayoutPosition()));
                        }
                    }
            );
        }
    }

    public interface OnItemClick{
         void onClick(SongModel id);
    }
}

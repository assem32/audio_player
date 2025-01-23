package com.example.song.home.domain.repo;

import com.example.song.home.domain.model.SongModel;

import java.util.List;

public interface SongRepo {
    public List<SongModel> getMusic();
}

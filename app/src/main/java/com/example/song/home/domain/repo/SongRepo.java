package com.example.song.home.domain.repo;

import android.content.Context;

import com.example.song.home.domain.model.SongModel;

import java.util.List;

public interface SongRepo {
    public List<SongModel> getMusic();

    public void storeMode(boolean mode);

    public boolean getMode();
}

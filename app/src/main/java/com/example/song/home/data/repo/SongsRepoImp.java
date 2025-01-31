package com.example.song.home.data.repo;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.song.home.data.localSource.LocalDataSource;
import com.example.song.home.data.localSource.SharedPref;
import com.example.song.home.domain.model.SongModel;
import com.example.song.home.domain.repo.SongRepo;

import java.util.List;

import javax.inject.Inject;

public class SongsRepoImp implements SongRepo {
    final private LocalDataSource dataSource;
    final private SharedPref sharedPref;

    @Inject
    public SongsRepoImp(LocalDataSource dataSource,SharedPref sharedPref){
        this.dataSource=dataSource;
        this.sharedPref=sharedPref;
    }



    @Override
    public List<SongModel> getMusic() {
        return dataSource.getSongs();
    }

    @Override
    public void storeMode(boolean mode) {
        sharedPref.saveMode(mode);
    }

    @Override
    public boolean getMode() {
        return sharedPref.getMode();
    }
}

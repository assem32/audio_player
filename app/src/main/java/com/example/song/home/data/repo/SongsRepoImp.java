package com.example.song.home.data.repo;

import com.example.song.home.data.localSource.LocalDataSource;
import com.example.song.home.domain.model.SongModel;
import com.example.song.home.domain.repo.SongRepo;

import java.util.List;

public class SongsRepoImp implements SongRepo {
    final private LocalDataSource dataSource;

    SongsRepoImp(LocalDataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    public List<SongModel> getMusic() {
        return dataSource.getSongs();
    }
}

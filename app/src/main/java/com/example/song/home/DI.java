package com.example.song.home;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.song.home.data.localSource.LocalDataSource;
import com.example.song.home.data.localSource.SharedPref;
import com.example.song.home.data.repo.SongsRepoImp;
import com.example.song.home.domain.repo.SongRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DI {


    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public LocalDataSource proviveLocalDataSource(@ApplicationContext Context context){
        return new LocalDataSource(context);
    }

    @Provides
    @Singleton
    public SongRepo provideSongRepo(LocalDataSource dataSource, SharedPref sharedPref){
        return new SongsRepoImp(dataSource,sharedPref);
    }


}

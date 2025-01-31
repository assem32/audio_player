package com.example.song.home.presentation.Home.mvvm;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.song.home.data.localSource.LocalDataSource;
import com.example.song.home.data.localSource.SharedPref;
import com.example.song.home.data.repo.SongsRepoImp;
import com.example.song.home.domain.model.SongModel;
import com.example.song.home.domain.repo.SongRepo;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class HomeMvvm extends ViewModel {

    final private SongRepo songRepo;


    @Inject
    HomeMvvm(SongRepo songRepo){
        this.songRepo=songRepo;
    }

    public MutableLiveData<List<SongModel>> songListLiveData=new MutableLiveData<>();

    public MutableLiveData<Boolean> modeLiveData=new MutableLiveData<>();

    public MutableLiveData<List<SongModel>> getSongListLiveData(){
        songListLiveData.setValue(songRepo.getMusic());
        return  songListLiveData;
    }


    public MutableLiveData<Boolean>getModeLiveData(){
        modeLiveData.setValue(songRepo.getMode());
        Log.d("mmmoooddd",modeLiveData.toString());
        return modeLiveData;
    }

    public void setModeLiveData(){
        boolean current = modeLiveData.getValue() ==null ?false:modeLiveData.getValue();
        songRepo.storeMode(!current);
        modeLiveData.setValue(!current);
    }

}

package com.example.song.home.presentation.splash.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.song.home.domain.repo.SongRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SplashMvvm extends ViewModel {
    final private SongRepo songRepo;

    public MutableLiveData<Boolean> modeLiveData=new MutableLiveData<>();


    @Inject
    SplashMvvm(SongRepo songRepo){
        this.songRepo=songRepo;
    }

    public MutableLiveData<Boolean>getModeLiveData(){
        modeLiveData.setValue(songRepo.getMode());
        return modeLiveData;
    }
}

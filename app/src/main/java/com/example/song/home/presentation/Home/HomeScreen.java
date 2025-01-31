package com.example.song.home.presentation.Home;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.song.databinding.FragmentHomeBinding;
import com.example.song.home.data.localSource.LocalDataSource;

import com.example.song.home.data.localSource.MediaPlayerIns;
import com.example.song.home.domain.model.SongModel;
import com.example.song.home.presentation.Home.Adapter.HomeSongRecycler;
import com.example.song.home.presentation.Home.mvvm.HomeMvvm;
import com.example.song.home.service.Notification;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeScreen extends Fragment {

    private FragmentHomeBinding binding;

    HomeSongRecycler homeSongRecycler = new HomeSongRecycler();

    HomeMvvm homeMvvm;


    MediaPlayerIns mediaPlayerIns = new MediaPlayerIns();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout and return the View.
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        homeMvvm = new ViewModelProvider(this).get(HomeMvvm.class);

        homeMvvm.getSongListLiveData();
        homeMvvm.songListLiveData.observe(
                getViewLifecycleOwner(), new Observer<List<SongModel>>() {
                    @Override
                    public void onChanged(List<SongModel> songModels) {
                        homeSongRecycler.setList(songModels);
                        binding.songRecycler.setAdapter(homeSongRecycler);
                    }
                }
        );

        homeMvvm.getModeLiveData().observe(
                getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {


                        if(aBoolean == false){
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//
                        }
                        else{
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        }
                    }
                }
        );

        binding.pressss.setOnClickListener(
                v -> homeMvvm.setModeLiveData()
        );



        homeSongRecycler.setOnItemClick(
                new HomeSongRecycler.OnItemClick() {
                    @Override
                    public void onClick(SongModel song) {
                        Navigation.findNavController(view).navigate(HomeScreenDirections.actionHomeScreenToDetailFragment(song.getTitle(),song.getDuration(),song.getArtist()));
                        mediaPlayerIns.playSing(song);
                        Notification notification = new Notification();
                        notification.notificationBuild(view.getContext(),song.getTitle(),"audio app");



                    }
                }
        );
    }




}
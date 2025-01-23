package com.example.song.home.presentation.Home;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.song.databinding.FragmentHomeBinding;
import com.example.song.home.data.localSource.LocalDataSource;

import com.example.song.home.data.localSource.MediaPlayerIns;
import com.example.song.home.domain.model.SongModel;
import com.example.song.home.presentation.Home.Adapter.HomeSongRecycler;

import java.util.List;

public class HomeScreen extends Fragment {

    private FragmentHomeBinding binding;

    private LocalDataSource localDataSource;

    private  List<SongModel> songs;

    HomeSongRecycler homeSongRecycler = new HomeSongRecycler();



    MediaPlayerIns mediaPlayerIns = new MediaPlayerIns();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout and return the View.
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // onViewCreated is called after the view has been created, just before interacting with it.


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        localDataSource = new LocalDataSource(view.getContext());
        songs = localDataSource.getSongs();


        homeSongRecycler.setList(songs);

        binding.songRecycler.setAdapter(homeSongRecycler);

        homeSongRecycler.setOnItemClick(
                new HomeSongRecycler.OnItemClick() {
                    @Override
                    public void onClick(SongModel song) {
                        Toast.makeText(view.getContext(),song.getPath(),Toast.LENGTH_LONG).show();


                        Navigation.findNavController(view).navigate(HomeScreenDirections.actionHomeScreenToDetailFragment(song.getTitle(),song.getDuration(),song.getArtist()));
                        mediaPlayerIns.playSing(song);



                    }
                }
        );
    }




}
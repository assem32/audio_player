package com.example.song.home.presentation.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.song.R;
import com.example.song.databinding.FragmentDetailsBinding;
import com.example.song.home.data.localSource.MediaPlayerIns;
import com.example.song.home.presentation.details.viewModel.DetailsViewModel;
//import com.example.song.databinding.FragmentHomeBinding;

public class DetailFragment extends Fragment {

    private FragmentDetailsBinding fragmentDetailsBinding;

    DetailsViewModel detailsViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        return fragmentDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        String songTitle = DetailFragmentArgs.fromBundle(getArguments()).getSongName();
        String duration = DetailFragmentArgs.fromBundle(getArguments()).getDuration();
        String artist = DetailFragmentArgs.fromBundle(getArguments()).getArtist();




        fragmentDetailsBinding.songName.setText(songTitle);
        fragmentDetailsBinding.artist.setText(artist);
        fragmentDetailsBinding.endTime.setText(detailsViewModel.formatTime(Integer.parseInt(duration)));


        fragmentDetailsBinding.seekbar.setMax(Integer.parseInt(duration));

        detailsViewModel.getTimeUser().observe(
                getViewLifecycleOwner(),s -> {
                    fragmentDetailsBinding.startTime.setText(s);
                }
        );
        detailsViewModel.update();
        detailsViewModel.getMills().observe(
                getViewLifecycleOwner(),integer -> fragmentDetailsBinding.seekbar.setProgress(integer)
        );



        detailsViewModel.getIsPlay().observe(
                getViewLifecycleOwner(),aBoolean -> {
                    if(!aBoolean){
                        fragmentDetailsBinding.play.setImageResource(R.drawable.play_332);
                    }
                    else {
                        fragmentDetailsBinding.play.setImageResource(R.drawable.pause_f_svgrepo_com);
                    }
                }
        );



        fragmentDetailsBinding.backIcn.setOnClickListener(
                v -> {
                    Navigation.findNavController(view).navigate(DetailFragmentDirections.actionDetailFragmentToHomeScreen());
                }
        );

        fragmentDetailsBinding.play.setOnClickListener(
                v -> {
                    detailsViewModel.checkPlay();
                }
        );



        fragmentDetailsBinding.seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser)
                            MediaPlayerIns.getInstance().seekTo(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        int newPosition =seekBar.getProgress();
                        MediaPlayerIns.getInstance().seekTo(newPosition);
//                        MediaPlayerIns.getInstance().start();
                    }
                }
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentDetailsBinding=null;
    }
}

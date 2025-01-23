package com.example.song.home.presentation.details.viewModel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.song.home.data.localSource.MediaPlayerIns;

public class DetailsViewModel extends ViewModel {
    MutableLiveData<Integer> timeMili = new MutableLiveData<>();

    MutableLiveData<String> timeUser = new MutableLiveData<>();

    MutableLiveData<Boolean> isPlaying = new MutableLiveData<>();

    private Handler handler = new Handler();


    public LiveData<Integer> getMills() {
        return timeMili;
    }

    public LiveData<String> getTimeUser() {
        return timeUser;
    }

    public LiveData<Boolean> getIsPlay(){
        return isPlaying;
    }

    public void update() {
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {
                        if (MediaPlayerIns.isPlay()) {
                            int postion = MediaPlayerIns.getInstance().getCurrentPosition();
                            timeMili.postValue(postion);
                            timeUser.postValue(formatTime(postion));
                        }
                        handler.postDelayed(this, 100);
                    }

                }

        );

    }


    public void checkPlay(){
        if(MediaPlayerIns.isPlay()) {
            MediaPlayerIns.pauseOrPlay();
            isPlaying.postValue(false);
        }
        else {
            MediaPlayerIns.pauseOrPlay();
            isPlaying.postValue(true);
        }
    }

    public String formatTime(int durationMills) {
//        Log.d("mill", "" + durationMills);
        int minutes = (durationMills / 1000) / 60;
        int seconds = (durationMills / 1000) % 60;


        return String.format("%02d:%02d", minutes, seconds);
    }

}

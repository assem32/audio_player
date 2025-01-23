package com.example.song.home.data.localSource;

import android.media.MediaPlayer;
import android.net.Uri;

import com.example.song.home.domain.model.SongModel;

import java.io.IOException;

public class MediaPlayerIns {
    static MediaPlayer instance;

    public static MediaPlayer getInstance() {
        if (instance == null) {
            instance = new MediaPlayer();
        }
        return instance;


    }

    public void playSing(SongModel songModel){
        MediaPlayer mediaPlayer = getInstance();
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(songModel.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void pauseOrPlay() {
        MediaPlayer mediaPlayer = getInstance();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        else if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public static boolean isPlay(){
        MediaPlayer mediaPlayer = getInstance();
        return mediaPlayer.isPlaying();
    }

//    public static void resume() {
//        MediaPlayer mediaPlayer = getInstance();
//        if (!mediaPlayer.isPlaying()) {
//            mediaPlayer.start();
//        }
//    }
}

package com.example.song.home.data.localSource;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.example.song.home.domain.model.SongModel;

import java.io.IOException;
import java.io.PrintStream;

public class MediaPlayerIns {
    static MediaPlayer instance;

    static String songUrl;

    public static MediaPlayer getInstance() {
        if (instance == null) {
            instance = new MediaPlayer();
        }
        return instance;


    }

    public void playSing(SongModel songModel){
        MediaPlayer mediaPlayer = getInstance();
        if(songUrl!=null && songUrl.equals(songModel.getPath()) &&mediaPlayer.isPlaying()){
            Log.d("done",songModel.getPath());
            Log.d("done",songModel.getPath());

        }
        else {
            try {
//                songUrl=songModel.getPath()
                mediaPlayer.reset();
                mediaPlayer.setDataSource(songModel.getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
                songUrl=songModel.getPath();

                Log.d("oooooo",songModel.getPath());
                Log.d("oooooo",songUrl);
//                System.out.println(songModel+"fgggdfdffd");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
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

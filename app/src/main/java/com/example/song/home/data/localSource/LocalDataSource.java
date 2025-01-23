package com.example.song.home.data.localSource;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.song.home.domain.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource {

    private Context context;

    public LocalDataSource(Context context) {
        this.context = context;
    }

    private boolean hasReadPermission() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public List<SongModel> getSongs() {

        if (hasReadPermission()) {
            List<SongModel> songList = new ArrayList<>();
            Uri songsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] songData = {
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.DURATION

            };


            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(songsUri, songData, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                String duration = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                SongModel song = new SongModel(id, title, artist, path, duration);

                songList.add(song);

            }
            cursor.close();
            return songList;

        } else {
            requestPermission();
            return new ArrayList<>();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
    }


}

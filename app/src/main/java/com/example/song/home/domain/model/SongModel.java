package com.example.song.home.domain.model;

public class SongModel {
    private final int id;
    private final String title;
    private final String artist;

    private final String duration;


    private final String path;

    public SongModel(int id, String title, String artist, String filePath , String duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.path = filePath;
        this.duration=duration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }

    public String getDuration(){
        return duration;
    }


}

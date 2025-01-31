package com.example.song.home.data.localSource;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPref {
    private final SharedPreferences sharedPreferences;


    @Inject
    public SharedPref(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    public void saveMode(boolean mode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("mode", mode);
        editor.apply();
    }

    public boolean getMode() {
        return sharedPreferences.getBoolean("mode", false);
    }
}

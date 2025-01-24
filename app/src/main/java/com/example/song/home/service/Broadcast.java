package com.example.song.home.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.song.home.data.localSource.MediaPlayerIns;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("ACTION_PLAY")) {
            MediaPlayerIns.pauseOrPlay();
        }
    }
}

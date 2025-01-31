package com.example.song.home.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.song.MainActivity;
import com.example.song.R;
import com.example.song.home.data.localSource.MediaPlayerIns;

public class Notification {



    public void createNotificationChannel(Context context){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel= new NotificationChannel("sd","sd", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notificationBuild(Context context,String appName,String title){

        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat( context, "tag");

        Intent playIntent=new Intent(context, Broadcast.class);
        playIntent.setAction("ACTION_PLAY");
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                playIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_img);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,"sd")
                .setContentText(title)
                .setContentTitle(appName)
                .setSmallIcon(R.drawable.audio_essential_volume_svgrepo_com)
                .setLargeIcon(icon)
                .addAction(R.drawable.step_backward_icon,"", playPendingIntent)
                .addAction(R.drawable.play_icon,"play pause", playPendingIntent)
                .addAction(R.drawable.step_backward_icon,"", playPendingIntent)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0,1,2)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .setShowWhen(false)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1,builder.build());
    }


}

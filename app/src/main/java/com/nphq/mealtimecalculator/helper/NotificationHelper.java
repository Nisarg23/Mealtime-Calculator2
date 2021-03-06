package com.nphq.mealtimecalculator.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.nphq.mealtimecalculator.R;

public class NotificationHelper extends ContextWrapper {

    public static final String CHANNEL_ID = "Main_Channel";
    public static final String CHANNEL_NAME = "Notify_APP";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);

        channel.setDescription("test");
        channel.enableLights(false);
        channel.enableVibration(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    // API 26

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotification(String title,
                                                String message,
                                                Uri sound,
                                                PendingIntent pendingIntent,
                                                boolean isClicked){
        return new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setSound(sound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(isClicked);

    }
}

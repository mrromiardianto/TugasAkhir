package com.example.screening_time.Anak.BackEnd;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.screening_time.Anak.Features.Menu_SplashScreen;


public class MyService extends Service {
    private static final int NOTIF_ID = 2;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        // do your jobs here

        startForeground();

        return super.onStartCommand(intent, flags, startId);
    }

    private void startForeground() {
        Intent notificationIntent = new Intent(this, Menu_SplashScreen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        Toast.makeText(this, "Selalu", Toast.LENGTH_SHORT).show();

//        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
//                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
//                .setOngoing(true)
//                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
//                .setContentTitle(getString(R.string.app_name))
//                .setContentText("Service is running background")
//                .setContentIntent(pendingIntent)
//                .build());
    }
}
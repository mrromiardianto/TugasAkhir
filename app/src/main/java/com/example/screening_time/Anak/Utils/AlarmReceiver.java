package com.example.screening_time.Anak.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, AppCheckServices.class));
        context.startService(new Intent(context, UninstallIntentReceiver.class));

    }
}

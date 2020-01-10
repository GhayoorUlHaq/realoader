package com.example.reloader;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    webview reloader;
    Integer num;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        reloader = webview.reloader;
        num = 0;
        String interval = intent.getStringExtra("interval");
        String duration = intent.getStringExtra("duration");

        new CountDownTimer(Integer.parseInt(duration)*1000, Integer.parseInt(interval)*1000) {

            public void onTick(long millisUntilFinished) {
                reloader.showNotification("Reloader Started", "Your Page Reloaded for "+num.toString()+" times", true);
                reloader.reload();
                num += 1;
                Log.w("timer", "time");
                Log.w("num", num.toString());
            }

            public void onFinish() {
                reloader.showNotification("Reloader Stopped", "Total Reloads are "+num.toString()+" times", false);
                onDestroy();
                Log.e("ended","----------ended-------------");
            }

        }.start();

//        push notification



        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void note(){

    }
}

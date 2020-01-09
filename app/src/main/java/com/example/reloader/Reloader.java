package com.example.reloader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler;

import androidx.core.app.NotificationCompat;


public class Reloader extends Activity {

    static Reloader reloader;
    private WebView webView;
    public Button btn_back;
    public Button btn_stop;
    private ProgressDialog progDailog;
    public Integer times;

    Intent intent;
    String current_url;
    String time_interval;
    String time_duration;


    @SuppressLint({"NewApi"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        times = 0;

        reloader = this;
        intent = getIntent();

        current_url = intent.getStringExtra("current_url");
        time_interval = intent.getStringExtra("time_interval");
        time_duration = intent.getStringExtra("time_duration");

        Log.e("time_interval", time_interval);
        Log.e("time_duration", time_duration);
        Log.e("current_url", current_url);

        btn_back = findViewById(R.id.btnbackreloader);
        btn_stop = findViewById(R.id.stop);

        setContentView(R.layout.activity_reloader);

        progDailog = ProgressDialog.show(this, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);


        webView = (WebView) findViewById(R.id.webView2);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
        });

        webView.loadUrl(current_url);

        Intent i = new Intent(getBaseContext(), MyService.class);
        i.putExtra("interval" , time_interval);
        i.putExtra("duration" , time_duration);
        this.startService(i);

    }

    public void reload() {
        webView.reload();
    }

    public void showNotification(String title, String message){

        Log.d("Hay8","DCM8");
        Intent intent = new Intent(this, webview.class);
        Log.d("Hay9","DCM9");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d("Hay10","DCM10");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default")
                .setSmallIcon(R.drawable.reloader)
                .setContentTitle(title)
                .setContentText(message)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        Log.d("Hay11","DCM11");



        NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        Log.d("Hay12","DCM12");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("com.myApp");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "com.myApp",
                    "My App",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        notificationManager.notify(2,builder.build());
    }

    public void stopbtn(View view){
        stopService(new Intent(this, MyService.class));
        onBackPressed();
    }

    public void backbtn(View view){
        onBackPressed();
    }


}
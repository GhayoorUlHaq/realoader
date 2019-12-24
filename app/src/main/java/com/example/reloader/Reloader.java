package com.example.reloader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class Reloader extends Activity {


    private WebView webView;
    public Button btn_back;
    public Button btn_stop;
    private ProgressDialog progDailog;
    Intent intent;
    String current_url;
    String time_interval;
    String time_duration;


    @SuppressLint({"NewApi"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();

        current_url = intent.getStringExtra("current_url");
        time_interval = intent.getStringExtra("time_interval");
        time_duration = intent.getStringExtra("time_duration");

        Log.e("time_interval", time_interval);
        Log.e("time_duration", time_duration);
        Log.e("current_url", current_url);

        btn_back = (Button) findViewById(R.id.btnbackreloader);
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


    }
}
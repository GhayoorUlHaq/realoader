package com.example.reloader;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class webview extends Activity {


    private WebView webView;
    public Button btn_next;
    public Button btn_back;
    private ProgressDialog progDailog;


    @SuppressLint({"NewApi"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn_next = (Button) findViewById(R.id.next);
        btn_back = findViewById(R.id.back);


        setContentView(R.layout.activity_webview);

        progDailog = ProgressDialog.show(this, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);


        webView = (WebView) findViewById(R.id.webView);


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

        webView.loadUrl("http://careermidway.com");


    }

    public void next_btn(View view) {

        Log.e("pass intent","here i'll pass intent");
//        Intent i = new Intent(webview.this, config.class);
//        i.putExtra("url", webView.getUrl());
//        startActivity(i);
    }
}
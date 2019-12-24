package com.example.reloader;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class webview extends Activity {


    private WebView webView;
    public Button btn_next;
    public Button btn_back;
    private ProgressDialog progDailog;
    public AlertDialog.Builder alert;
    public String time_interval;
    public String time_duration;



    @SuppressLint({"NewApi"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alert = new AlertDialog.Builder(this);

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

    public void dialog_box(String title, int flag){
        Context context = this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Add a TextView here for the "Title" label, as noted in the comments
        final EditText interval = new EditText(context);
        interval.setHint("Interval");
        layout.addView(interval); // Notice this is an add method

        // Add another TextView here for the "Description" label
        final EditText duration = new EditText(context);
        duration.setHint("Duration");
        layout.addView(duration); // Another add method

        alert.setTitle("Set Values");
        alert.setMessage("Enter Time Interval and Duration (sec)");

        alert.setView(layout);


        alert.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                time_interval = interval.getText().toString();
                time_duration = duration.getText().toString();

                if(time_interval.equals("") || time_duration.equals("")){
                    dialog.dismiss();

                    Toast toast=Toast. makeText(getApplicationContext(),"Please enter both values to get started",Toast. LENGTH_SHORT);
                    toast.show();
                }else{

                }

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    public void next_btn(View view) {

        dialog_box("Select Time Duration", 0);
        Log.w("000000000000000", "e check kro ny ");
    }
}
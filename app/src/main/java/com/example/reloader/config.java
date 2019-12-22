package com.example.reloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class config extends AppCompatActivity {

    Button start;
    Button back;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

//        url = getIntent().getStringExtra("url");
//
//        System.out.println("-77777777777777");
//        System.out.println(url);


        start = findViewById(R.id.go);
        back = findViewById(R.id.backConfig);
        logout = findViewById(R.id.configsignout);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(config.this, Reloader.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(config.this, webview.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(config.this, login.class);
                startActivity(i);
            }
        });


    }
}

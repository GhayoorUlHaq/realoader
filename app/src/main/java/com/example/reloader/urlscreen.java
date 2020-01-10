package com.example.reloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class urlscreen extends AppCompatActivity {

    Button go;
    EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlscreen);

        url = findViewById(R.id.url);
        url.setText("http://.com");
        url.setSelection(7);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        go = findViewById(R.id.go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(url.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Please Enter Url to Proceed",Toast. LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent i = new Intent(urlscreen.this, webview.class);
                    i.putExtra("url", url.getText().toString());
                    startActivity(i);
                }

            }
        });
    }


    public void logout(View v)
    {
        startActivity(new Intent(getApplicationContext(), login.class));

    }
}

package com.example.reloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

   public  Button loginbtn;
   public EditText editEmail;
   public EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editEmail = findViewById(R.id.email);
        editPassword =  findViewById(R.id.password);
        

        loginbtn = findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editEmail.getText().toString().equals("") || editPassword.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"All fields are required..",Toast. LENGTH_SHORT);
                    toast.show();
                }else if(editEmail.getText().toString().equals("ghayoor@gmail.com") && editPassword.getText().toString().equals("aaa")){
                    Intent i = new Intent(login.this, urlscreen.class);
                    startActivity(i);
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Please provide valid email",Toast. LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}

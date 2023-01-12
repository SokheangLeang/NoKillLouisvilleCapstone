package com.example.capstoneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = findViewById(R.id.username);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //admin
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();

                }
                else{
                    //incorrect
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
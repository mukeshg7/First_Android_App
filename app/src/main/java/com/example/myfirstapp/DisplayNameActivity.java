package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_name);
        setTitle("Display Page");

        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        ((TextView)findViewById(R.id.displayOutput)).setText("Hello " + name);
    }
}
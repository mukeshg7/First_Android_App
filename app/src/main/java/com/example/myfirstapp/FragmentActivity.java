package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        setTitle("Fragments");
    }

    public void FragmentChange(View v) {
        Fragment f = null;
        if(v == findViewById(R.id.button1)) {
            f = new Fragment1();
        } else if(v == findViewById(R.id.button2)) {
            f = new Fragment2();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, f);
        ft.addToBackStack(null);
        ft.commit();
    }
}
package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");
    }

    public void submit(View v) {
        EditText t = findViewById(R.id.input);
        String s = t.getText().toString();

        ((TextView)findViewById(R.id.output)).setText(s);

        Intent i = new Intent(this, DisplayNameActivity.class);
        i.putExtra("Name", s);
        startActivity(i);
    }

    public void openWebView(View v) {
        EditText t = findViewById(R.id.webViewInput);
        String s = t.getText().toString();

        Intent i = new Intent(this, WebViewActivity.class);
        i.putExtra("websiteUrl", s);
        startActivity(i);
    }

    public void moveToFragment(View v) {
        Intent i = new Intent(this, FragmentActivity.class);
        startActivity(i);
    }

    public void JSfun(View v) {
        Intent i = new Intent(this, JSWebViewActivity.class);
        startActivity(i);
    }

    public void moreFunction(View v) {
        Intent i = new Intent(this, WebViewFunctionActivity.class);
        startActivity(i);
    }
}
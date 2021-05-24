package com.example.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewFunctionActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_function);

        myWebView = (WebView) findViewById(R.id.webView);

        myWebView.setWebViewClient(new WebViewActivity.notRedirectToBrowser());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new WebViewFunctionActivity.JavaScriptInterface(this), "AndroidFunction");

        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.getSettings().setDatabaseEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);

        String url = "react web-app https link";

        myWebView.loadUrl(url);
    }


    //Android to WebApp
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void sendMsgToWeb(View v) {
        TextView t = (TextView) findViewById(R.id.reactEditText);
        String s = t.getText().toString();
        t.setText("");
        myWebView.evaluateJavascript("handleTxt(\\\"this\\\");", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Log.d("LogName", s); // Prints 'this'
            }
        });
    }

    public void sendAlertToWeb(View v) {

        myWebView.loadUrl("javascript:increment()");
    }


    public class JavaScriptInterface { //WebApp to Android
        Context C;
        JavaScriptInterface(Context c) {
            C = c;
        }
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(C, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void getMessage(String msg) {
            TextView t = (TextView) findViewById(R.id.ReacttextFromWeb);
            t.setText(msg);
        }
    }

    private class notRedirectToBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    }

}
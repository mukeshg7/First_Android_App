package com.example.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class JSWebViewActivity extends AppCompatActivity {

    private WebView myJSWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsweb_view);

        myJSWebView = (WebView)findViewById(R.id.webView2);
        myJSWebView.setWebViewClient(new notRedirectToBrowser());
        myJSWebView.getSettings().setJavaScriptEnabled(true);
        myJSWebView.addJavascriptInterface(new JavaScriptInterface(this), "AndroidFunction");

        String url = "file:///android_asset/test.html";
        //String url1 = " https://a63c5a26efd0.ngrok.io/test.html";

        myJSWebView.loadUrl(url);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void sendMsgToWeb(View v) {
        TextView t = (TextView) findViewById(R.id.EditText);
        String s = t.getText().toString();
        t.setText("");
        myJSWebView.loadUrl("javascript:editText('" + s + "')");
    }

    public void sendAlertToWeb(View v) {

        myJSWebView.loadUrl("javascript:increment()");
    }


    public class JavaScriptInterface {
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
            TextView t = (TextView) findViewById(R.id.textFromWeb);
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
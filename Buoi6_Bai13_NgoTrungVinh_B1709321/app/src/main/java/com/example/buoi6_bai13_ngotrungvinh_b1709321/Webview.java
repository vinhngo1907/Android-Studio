package com.example.buoi6_bai13_ngotrungvinh_b1709321;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {
    WebView brower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        brower = (WebView) findViewById(R.id.webview);
        brower.setWebViewClient(new WebViewClient());

        brower.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        brower.loadUrl(url);
    }
}

package com.demon.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.demon.js_pdf.WebViewUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);
        WebViewUtil.WebViewSetting(webView);
        WebViewUtil.WebViewLoadPDF(webView, "http://p1956v3nv.bkt.clouddn.com/2014.pdf");
    }
}

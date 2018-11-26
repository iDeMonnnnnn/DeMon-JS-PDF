package com.demon.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import com.demon.js_pdf.WebViewHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);
        WebViewHelper.WebViewSetting(webView, new WebViewHelper.PDFListener() {
            @Override
            public void LoadProgress(int progress) {
                Log.i(TAG, "LoadProgress: "+progress);
            }
        });
        WebViewHelper.WebViewLoadPDF(webView, "http://pis1o18dw.bkt.clouddn.com/Android%E9%98%BF%E9%87%8C%E6%89%8B%E5%86%8C.pdf");
    }
}

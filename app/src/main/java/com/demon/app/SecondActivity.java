package com.demon.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demon.js_pdf.view.DWebView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        DWebView jsWebView = findViewById(R.id.jsWebView);
        jsWebView.LoadPDF("http://pis1o18dw.bkt.clouddn.com/Android%E9%98%BF%E9%87%8C%E6%89%8B%E5%86%8C.pdf");
    }
}

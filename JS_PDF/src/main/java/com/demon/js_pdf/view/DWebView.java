package com.demon.js_pdf.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.demon.js_pdf.R;
import com.demon.js_pdf.WebViewHelper;
import com.demon.js_pdf.code.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @author DeMon
 * @date 2018/12/8
 * @email 757454343@qq.com
 * @description
 */
public class DWebView extends FrameLayout {
    private WebView webView;
    private NumberProgressBar progressBar;
    boolean isSetting = true;
    boolean isProgress = true;
    private ProgressListener listener;

    public DWebView(Context context) {
        this(context, null);
    }

    public DWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_webview, this);
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.DWebView);
        isSetting = mTypedArray.getBoolean(R.styleable.DWebView_dwv_isSetting, true);
        isProgress = mTypedArray.getBoolean(R.styleable.DWebView_dwv_isProgress, true);
        if (isSetting) {
            WebViewSetting();
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (listener != null) {
                    listener.LoadProgress(newProgress);
                }
                if (isProgress) {
                    progressBar.setProgress(newProgress);
                    if (newProgress > 0 && progressBar.getVisibility() == View.GONE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    if (newProgress == 100 && progressBar.getVisibility() == View.VISIBLE) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        }, 2000);
                    }
                }
            }
        });
        mTypedArray.recycle();
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void WebViewSetting() {
        WebSettings settings = webView.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
    }


    public void setListener(ProgressListener listener) {
        this.listener = listener;
    }




    public void LoadPDF(String docPath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//api >= 19
            webView.loadUrl("file:///android_asset/pdf/pdf.html?" + docPath);
        } else {
            if (!TextUtils.isEmpty(docPath)) {
                byte[] bytes = null;
                try {// 获取以字符编码为utf-8的字符
                    bytes = docPath.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (bytes != null) {
                    docPath = new BASE64Encoder().encode(bytes);// BASE64转码
                }
            }
            webView.loadUrl("file:///android_asset/pdf/pdf.html?" + docPath);
        }
    }
}

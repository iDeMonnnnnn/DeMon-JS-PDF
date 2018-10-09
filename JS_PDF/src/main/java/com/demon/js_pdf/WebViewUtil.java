package com.demon.js_pdf;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;

/**
 * @author D&LL
 * @date 2018/7/22
 * @description
 */
public class WebViewUtil {
    @SuppressLint("SetJavaScriptEnabled")
    public static void WebViewSetting(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public static void WebViewLoadPDF(WebView webView, String docPath) {
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

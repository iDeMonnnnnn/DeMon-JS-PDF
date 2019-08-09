[![](https://jitpack.io/v/DeMonLiu623/DeMon-JS-PDF.svg)](https://jitpack.io/#DeMonLiu623/DeMon-JS-PDF)

# DeMon-JS-PDF
**一个基于[PDF.js](http://mozilla.github.io/pdf.js/)，使用cdn的方式导入,自定义界面的PDF预览框架。**

原文链接：[Android 实现PDF预览的全面解析](https://blog.csdn.net/DeMonliuhui/article/details/81185611)  

![Effect](https://raw.githubusercontent.com/DeMonLiu623/DeMon-JS-PDF/master/img/demo.png)

### 使用

1.在工程的build.gradle添加:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.在项目的build.gradle添加：
```
dependencies {
	        implementation 'com.github.DeMonLiu623:DeMon-JS-PDF:v1.2'
	}
```

3.在WebView中加载PDF

```java
   WebView webView = findViewById(R.id.webView);
   //框架内部对WebView的默认设置
   WebViewHelper.WebViewSetting(webView，, new ProgressListener() {
                  @Override
                  public void LoadProgress(int progress) {
                  //对加载进度的监听
                  Log.i(TAG, "LoadProgress: "+progress);
                  }
   });
   //框架内部对url进行了编码，防止因为中文无法加载的情况
   //yourpdf可以是链接，也可以是本地pdf文件路径
   WebViewHelper.WebViewLoadPDF(webView, "yourpdf");
```

or

```java
   WebView webView = findViewById(R.id.webView);
   //你对webView的设置
   ...
   //yourpdf可以是链接，也可以是本地pdf文件路径
   webView.loadUrl("file:///android_asset/pdf/pdf.html?" + yourpdf);
```


4.使用DWebView加在pdf
```xml
<com.demon.js_pdf.view.DWebView
        android:id="@+id/jsWebView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:dwv_isProgress="true"
        app:dwv_isSetting="true" />
```
```java
        DWebView jsWebView = findViewById(R.id.jsWebView);
         jsWebView.LoadPDF("yourpdf.pdf");
```

5.更多请看示例程序。

### 注意

1.如果是网络链接的PDF，需要使用网络的权限。

```
<uses-permission android:name="android.permission.INTERNET" />
```
2.如果是本地文件的PDF，需要读取设备内容的权限。

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

### 版本
#### 1.1
1. 新增对pdf加载进度的监听。
2. 修改WebViewUtil为WebViewHelper。

#### 1.2
1. 增加自定义的DWebView（一个自带加载进度条的WebView）

### BUG or 问题
请在issues留言，定期回复。

### LICENSE

```
MIT License

Copyright (c) 2018 DeMon

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

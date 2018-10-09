[![](https://jitpack.io/v/DeMonLiu623/DeMon-JS-PDF.svg)](https://jitpack.io/#DeMonLiu623/DeMon-JS-PDF)

# DeMon-JS-PDF
一个基于[PDF.js](http://mozilla.github.io/pdf.js/)，使用cdn的方式导入,自定义预览界面的PDF预览框架。

原文链接：[Android 实现PDF预览的全面解析](https://blog.csdn.net/DeMonliuhui/article/details/81185611)  

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
	        implementation 'com.github.DeMonLiu623:DeMon-JS-PDF:v1.0'
	}
```

3.在WebView中加载PDF
```java
   WebView webView = findViewById(R.id.webView);
   WebViewUtil.WebViewSetting(webView);//WebView的基本设置
   WebViewUtil.WebViewLoadPDF(webView, "your pdf");//可以是链接，也可以是本地pdf文件路径
```

### BUG or 问题
请E-mail：757454343@qq.com 联系我。

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
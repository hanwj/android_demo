package com.xiaoxiao.testrxjava;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoxiao.utils.LogUtils;

/**
 * 文件名: WebActivity
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/3/12
 */
public class WebActivity extends FragmentActivity{

    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.webview);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
//        settings.setPluginState(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }

//        WebChromeClient webChromeClient = new WebChromeClient();
//        WebViewClient webViewClient = new WebViewClient();
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        String url = "http://oss.suning.com/ppas/pptv_h5_prd/item/20190301_1/index.html?channel=pptv";
//        String url = "file:///android_asset/video.html";
        String url = "http://dtm.cnsuning.com";
//        String url = "https://sso.cnsuning.com/ids/login?service=http%3A%2F%2Fdtm.cnsuning.com%2Fauth%3FtargetUrl%3Dhttp%253A%252F%252Fdtm.cnsuning.com%252FloginSuccess.do&loginTheme=dtmTheme";
//        String url = "https://blog.csdn.net/wangqiubo2010/article/details/77646461";
        webView.loadUrl(url);
    }

    private WebChromeClient webChromeClient = new WebChromeClient(){

    };

    private WebViewClient webViewClient = new WebViewClient(){
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            LogUtils.e("WebView","url:" + request.getUrl().toString());
//            return false;
//        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.e("WebView","url:" + url);
//            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view,url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            LogUtils.e("WebView","onReceivedError");
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            LogUtils.e("WebView","onReceivedSslError");
//            super.onReceivedSslError(view, handler, error);
            handler.proceed();
        }
    };
}

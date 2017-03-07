package com.qf.meilihui.avtivity;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 肖 on 2017/3/7.
 */
//必须设置
public class MyWebClient extends WebViewClient{

private   WebViewCallBack  callBack;

    public MyWebClient(WebViewCallBack callBack) {
        this.callBack = callBack;
    }


//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        return super.shouldOverrideUrlLoading(view, url);
//    }
    /**
     * 网页开始加载, 调用该方法
     * 每次加载新的网页时, 也会调用该方法
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        callBack.pageStart(url);

    }
    /**
     * 网页加载完成的回调方法
     *
     */

    @Override
    public void onPageFinished(WebView view, String url) {


        callBack.pageFinish(url);
    }

    public   interface   WebViewCallBack{

        public  void  pageStart(String url);
        public  void  pageFinish(String url);
    }
}

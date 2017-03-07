package com.qf.meilihui.avtivity;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by 肖 on 2017/3/7.
 */
//页面处理
public class MyWebChromeClient  extends WebChromeClient {

    private   ChromeCallBack  callBack;

    public MyWebChromeClient(ChromeCallBack callBack) {
        this.callBack = callBack;
    }

    //加载进度发生变化  调用的方法
    @Override
    public void onProgressChanged(WebView view, int newProgress) {

        callBack.updateProgress(newProgress);
    }

    public interface   ChromeCallBack{
        public  void  updateProgress(int progress);
    }
}

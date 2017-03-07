package com.qf.meilihui.avtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.meilihui.R;

public class HeadViewDetailsActivity extends AppCompatActivity {

    private WebView webView;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_head_view_details);
        webView= (WebView) findViewById(R.id.webview);
        layout= (LinearLayout) findViewById(R.id.details_linear);

        String path=getIntent().getStringExtra("path");
        String text=getIntent().getStringExtra("title");
        TextView tv= (TextView) findViewById(R.id.deatails_tv);
        tv.setText(text);
        Log.i("path",path);

        //设置支持js
        WebSettings settings=webView.getSettings();//得到设置对象
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new MyWebClient(new MyWebClient.WebViewCallBack() {

            @Override
            public void pageStart(String url) {

            }
            @Override
            public void pageFinish(String url) {
                layout.setVisibility(View.GONE);
            }
        }));
        webView.loadUrl(path);

               }
        }

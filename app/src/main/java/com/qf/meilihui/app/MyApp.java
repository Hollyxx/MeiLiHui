package com.qf.meilihui.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MyApp extends Application {

    //1, 创建消息队列, 可以设置为全局的
    private  static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化消息队列
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    //3, 把请求对象添加到队列中, 需要MyApp 向外提供一个方法
    public  static  RequestQueue getHttpQueue()
    {
        return  queue;
    }
}

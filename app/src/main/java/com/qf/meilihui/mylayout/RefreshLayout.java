package com.qf.meilihui.mylayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.qf.meilihui.R;
public class RefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener{

    private OnLoadingListener listener;//自定义的监听器 , 加载更多数据

    private View moreView;//底部视图

    private ListView lv;//RefreshLayout 包含的子视图

    private boolean isLast = false;//判断是否加载到底部, 如果为true, 则触发调用者onLoad()方法的执行


    public RefreshLayout(Context context) {
        super(context);

        moreView = LayoutInflater.from(context).inflate(R.layout.lv_more,null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        moreView = LayoutInflater.from(context).inflate(R.layout.lv_more,null);
    }

    /**
     * 在此方法中, 可以获取容器中包含的控件
     *
     * 作用: 排列布局中的子视图, 在此方法可以拿到子视图
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int count = getChildCount();

        if (count>0)
        {
            for (int i=0;i<count;i++)
            {
                View childView = getChildAt(i);

                if (childView instanceof ListView)//判断当前的子视图是不是ListView
                {
                    lv = (ListView) childView;
                    lv.setOnScrollListener(this);//设置滑动事件的监听
                }
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (isLast && scrollState==SCROLL_STATE_IDLE)
        {
            setLoading(true);//控制"加载更多"页面的显示
            listener.onLoad();//触发调用者的执行方法
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //判断是否加载到底部
        isLast = (firstVisibleItem+visibleItemCount==totalItemCount);

    }

    //定义一个加载数据的回调接口
    public  interface  OnLoadingListener
    {
        void onLoad();
    }

    //设置加载更多的监听器, 传入回调接口
    public void setOnLoadingListener(OnLoadingListener listener)
    {
        this.listener = listener;

    }

    //设置加载更多的方法, 控制ListView底部视图的显示
    public void setLoading(boolean isLoading)
    {
        if (isLoading)
        {
            lv.addFooterView(moreView);
        }
        else
        {
            lv.removeFooterView(moreView);
        }
    }
}

package com.qf.meilihui.customview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qf.meilihui.callback.LoadMoreCallBack;

/**
 * Created by invoker on 2017/3/9.
 */

public class LoadMoreRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    int firstVisibleItem, visibleItemCount, totalItemCount;
    private GridLayoutManager manager;
    private boolean loading = true;
    private int previousTotal = 0;
    private LoadMoreCallBack callBack;

    public LoadMoreRecyclerOnScrollListener(GridLayoutManager manager) {
        this.manager = manager;
    }

    public void setCallBack(LoadMoreCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = manager.getItemCount();
        firstVisibleItem = manager.findFirstVisibleItemPosition();
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            callBack.update();
            loading = true;
        }

    }

}

package com.qf.meilihui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qf.meilihui.bean.TodaySecondDetails;

import java.util.List;

/**
 * Created by 肖 on 2017/3/7.
 */

public class SecondDetailsAdapter  extends BaseAdapter {

    private Context context;
    private List<TodaySecondDetails> data;

    public SecondDetailsAdapter(Context context, List<TodaySecondDetails> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}

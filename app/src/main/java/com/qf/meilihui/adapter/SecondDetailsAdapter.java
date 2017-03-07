package com.qf.meilihui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.bean.TodaySecondDetails;

import java.util.List;

/**
 * Created by 肖 on 2017/3/7.
 */

public class SecondDetailsAdapter  extends BaseAdapter {

    private Context context;
    private List<TodaySecondDetails> data;
    private List<String> name;

    public SecondDetailsAdapter(Context context, List<TodaySecondDetails> data,List<String> name) {
        this.context = context;
        this.data = data;
        this.name=name;
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

        ViewHolder viewHolder;
        if (convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_seconddetails,parent,false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(data.get(position).getImageUrl()).into(viewHolder.pic);

        viewHolder.tv1.setText(data.get(position).getBrandName());
        viewHolder.tv2.setText(data.get(position).getProductName());
        Log.i("second",data.get(position).getProductName());
        viewHolder.tv3.setText("￥"+data.get(position).getPrice());
        viewHolder.tv4.setText("￥"+data.get(position).getMarketPrice());
        viewHolder.tv5.setText(data.get(position).getDiscount()+"折");

        return convertView;
    }
    class ViewHolder
    {
        ImageView pic;
        TextView tv1,tv2,tv3,tv4,tv5;

        public ViewHolder(View convertView)
        {
            this.pic = (ImageView) convertView.findViewById(R.id.scroll_iv);
            this.tv1 = (TextView) convertView.findViewById(R.id.scroll_tv1);
            this.tv2 = (TextView) convertView.findViewById(R.id.scroll_tv2);
            this.tv3= (TextView) convertView.findViewById(R.id.scroll_tv3);
            this.tv4 = (TextView) convertView.findViewById(R.id.scroll_tv4);
            this.tv5= (TextView) convertView.findViewById(R.id.scroll_tv5);
        }
    }
}

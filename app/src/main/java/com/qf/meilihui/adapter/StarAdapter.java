package com.qf.meilihui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.bean.StarRecommendBean;

import java.util.List;

/**
 * Created by 肖 on 2017/3/11.
 */

public class StarAdapter  extends BaseAdapter {

    private Context  context;
    private List<StarRecommendBean>  data;

    public StarAdapter(Context context, List<StarRecommendBean> data) {
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
        ViewHolder  viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_recommend,parent,false);
            viewHolder=new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv1.setText(data.get(position).getProductImgUrl());
        viewHolder.tv2.setText(data.get(position).getPrice());
        viewHolder.tv3.setText(data.get(position).getEventId());
        viewHolder.tv4.setText("￥"+data.get(position).getProductName());
        viewHolder.tv5.setText("￥"+data.get(position).getMarketPrice());

        Glide.with(context).load(data.get(position).getBrand()).into(viewHolder.iv);
        return convertView;
    }

    class ViewHolder{

        ImageView  iv;
        TextView tv1,tv2,tv3,tv4,tv5;
        public   ViewHolder(View view){
            iv= (ImageView) view.findViewById(R.id.recommend_iv);
            tv1= (TextView) view.findViewById(R.id.recommend_reason);
            tv2= (TextView) view.findViewById(R.id.recommend_brand);
            tv3= (TextView) view.findViewById(R.id.recommend_productName);
            tv4= (TextView) view.findViewById(R.id.recommend_price);
            tv5= (TextView) view.findViewById(R.id.recommend_marketPrice);


        }

    }
}

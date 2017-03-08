package com.qf.meilihui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.bean.HotBean;

import java.util.List;

/**
 * Created by 肖 on 2017/3/8.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context context;
    private List<HotBean> data;

    public RecycleAdapter(Context context, List<HotBean> data) {
        this.context = context;
        this.data = data;
    }

    /**
     * 返回列表中的条目数量(数据源的长度)
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_hot,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //赋值
        holder.tv1.setText(data.get(position).getBrand_name());
        holder.tv2.setText("￥"+data.get(position).getPrice());
        holder.tv3.setText(data.get(position).getMarket_price());
        Log.i("price",data.get(position).getMarket_price());

        Glide.with(context).load(data.get(position).getImgUrl()).into(holder.iv);

        //设置监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(holder.itemView,position);
            }
        });
    }



    class  ViewHolder extends  RecyclerView.ViewHolder
    {
        ImageView iv;
        TextView tv1,tv2,tv3;

         public ViewHolder(View itemView) {
            super(itemView);

             tv1 = (TextView) itemView.findViewById(R.id.hot_name);
             tv2 = (TextView) itemView.findViewById(R.id.hot_price);
             tv3 = (TextView) itemView.findViewById(R.id.hot_market);
             iv= (ImageView) itemView.findViewById(R.id.hot_iv);

         }
    }


    //定义监听器
    private OnItemClickListener listener;

    public  interface  OnItemClickListener
    {
        public void onItemClick(View itemView, int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }
}

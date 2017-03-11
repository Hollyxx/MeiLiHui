package com.qf.meilihui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.bean.OverSeaProductList;

import java.util.List;

/**
 * Created by 肖 on 2017/3/10.
 */

public class OverSeaRecycleAdapter extends RecyclerView.Adapter<OverSeaRecycleAdapter.ViewHolder> {

    private Context context;
    private List<OverSeaProductList> data;

    public OverSeaRecycleAdapter(Context context, List<OverSeaProductList> data) {
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

        View view = LayoutInflater.from(context).inflate(R.layout.item_oversea_recycle,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //赋值
        holder.tv1.setText(data.get(position).getBrand());
        holder.tv2.setText(data.get(position).getProductName());
        holder.tv3.setText("￥"+data.get(position).getPrice());
        holder.tv4.setText("￥"+data.get(position).getMarketPrice());
        //Log.i("price",data.get(position).getMarket_price());
        Glide.with(context).load(data.get(position).getProductImgUrl()).into(holder.iv);

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
        TextView tv1,tv2,tv3,tv4;

         public ViewHolder(View itemView) {
            super(itemView);

             tv1 = (TextView) itemView.findViewById(R.id.oversea_name);
             tv2 = (TextView) itemView.findViewById(R.id.oversea_chinesename);
             tv3 = (TextView) itemView.findViewById(R.id.oversea_price);
             tv4 = (TextView) itemView.findViewById(R.id.oversea_marketprice);
             iv= (ImageView) itemView.findViewById(R.id.oversea_iv);

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

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
import com.qf.meilihui.bean.OverSeaProductList;

import java.util.List;

/**
 * Created by 肖 on 2017/3/13.
 */

public class BeautifulBrandsAdapter extends BaseAdapter {

    private Context  context;
    private List<OverSeaProductList>  data;

    public BeautifulBrandsAdapter(Context context, List<OverSeaProductList> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_beautiful_brands,parent,false);

            viewHolder=new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.t1.setText(data.get(position).getBrand());
        viewHolder.t2.setText(data.get(position).getProductName());
        viewHolder.t3.setText("￥"+data.get(position).getPrice());
        viewHolder.t4.setText("￥"+data.get(position).getMarketPrice());
        viewHolder.t5.setText(data.get(position).getDiscount());
        Glide.with(context).load(data.get(position).getProductImgUrl()).into(viewHolder.iv);

        return convertView;
    }
    class ViewHolder{

        ImageView  iv;
        TextView  t1,t2,t3,t4,t5;

        public  ViewHolder(View  view)
        {
            iv= (ImageView) view.findViewById(R.id.brands_iv);
            t1= (TextView) view.findViewById(R.id.brand_tv1);
            t2= (TextView) view.findViewById(R.id.brand_tv2);
            t3= (TextView) view.findViewById(R.id.brand_tv3);
            t4= (TextView) view.findViewById(R.id.brand_tv4);
            t5= (TextView) view.findViewById(R.id.brand_tv5);

        }
    }
}

package com.qf.meilihui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.avtivity.SecondDetailsActivity;
import com.qf.meilihui.avtivity.ThirdDetailsActivity;
import com.qf.meilihui.bean.OverSeaEvent;
import com.qf.meilihui.bean.OverSeaProductList;
import com.qf.meilihui.uri.Config;

import java.util.List;

/**
 * Created by 肖 on 2017/3/11.
 */

public class OverSeaBoutiqueAdapter extends BaseAdapter {
    OverSeaRecycleAdapter adapter;
    private Context  context;
    private List<OverSeaEvent>  data;
    int m=0;
    int n=4;

    public OverSeaBoutiqueAdapter(Context context, List<OverSeaEvent> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_oversea,parent,false);

            viewHolder=new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
          viewHolder.brand.setText(data.get(position).getEnglishName());
          viewHolder.productname.setText(data.get(position).getName());
          String  dis=data.get(position).getDiscount().substring(0,3);
          viewHolder.discount.setText(dis);
          Glide.with(context).load(data.get(position).getImgUrl()).into(viewHolder.iv);
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(context,SecondDetailsActivity.class);
                String secondUrl=Config.TODAY_SECOND_CONTENT+data.get(position).getId()+"&pageIndex=1";
                intent.putExtra("web",secondUrl);
                intent.putExtra("id",data.get(position).getId());
                //Log.i("id",data.get(position-1).getEventId());
                intent.putExtra("englishName", data.get(position).getEnglishName());
                context.startActivity(intent);
            }
        });
        if(n<=data.get(position).getData().size()){
            List<OverSeaProductList> sub = this.data.get(position).getData().subList(m,n);
             adapter=new OverSeaRecycleAdapter(context, sub);
            viewHolder.recyclerView.setAdapter(adapter);
        }
            m=m+4;
            n=n+4;
       // Log.i("leng",data.get(position).getData().size()+"????");
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);


        //设置布局管理器
        viewHolder.recyclerView.setLayoutManager(manager);

        //设置增删数据的动画
        viewHolder.recyclerView.setItemAnimator(new DefaultItemAnimator());

       adapter.setOnItemClickListener(new OverSeaRecycleAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(View itemView, int position) {
               Intent intent=new Intent(context,ThirdDetailsActivity.class);
               String  thirdAddress= Config.TODAY_THIRD_CONTENT+data.get(position).getData().get(position).getId();
               String  Hot_recommendation= Config.Hot_recommendation+data.get(position).getId()+"&categoryId="+data.get(position).getData().get(position).getEventId();

               intent.putExtra("Hot_recommendation",Hot_recommendation);
               intent.putExtra("thirdAddress",thirdAddress);
               intent.putExtra("price",data.get(position).getData().get(position).getPrice());
               intent.putExtra("marketPrice",data.get(position).getData().get(position).getMarketPrice());
               intent.putExtra("name",data.get(position).getData().get(position).getBrand());
               intent.putExtra("productName",data.get(position).getData().get(position).getProductName());
               //intent.putExtra("discount",name);
               context.startActivity(intent);
           }
       });

        StaggeredGridLayoutManager sManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);

        viewHolder.recyclerView.setLayoutManager(sManager);

        return convertView;

    }
    class  ViewHolder{
        ImageView  iv;
        TextView  brand,discount,productname;
        RecyclerView  recyclerView;

        public  ViewHolder(View view){
            iv= (ImageView) view.findViewById(R.id.sea_image);
            brand= (TextView) view.findViewById(R.id.sea_brand);
            discount= (TextView) view.findViewById(R.id.sea_discount);
            productname= (TextView) view.findViewById(R.id.sea_productname);
            recyclerView= (RecyclerView) view.findViewById(R.id.oversea_recyclerview);
        }

    }
}

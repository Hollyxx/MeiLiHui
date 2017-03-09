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
import com.qf.meilihui.bean.BrandDetailBean;

import java.util.List;

/**
 * Created by invoker on 2017/3/8.
 */

public class BrandDetailRecyclerFirstAdapter extends RecyclerView.Adapter<BrandDetailRecyclerFirstAdapter.HViewHolder> {

    private Context context;
    private List<BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean.ProductBrandDetailBean> data;

    public BrandDetailRecyclerFirstAdapter(Context context, List<BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean.ProductBrandDetailBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public HViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type2, parent, false);
        HViewHolder holder = new HViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HViewHolder holder, int position) {
        BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean.ProductBrandDetailBean item = data.get(position);
        holder.price.setText("¥" + item.getPrice());
        holder.price_origin.setText("¥" + item.getMarketPrice());
        Glide.with(context).load(item.getFileUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class HViewHolder extends RecyclerView.ViewHolder {

        TextView price, price_origin;
        ImageView image;

        public HViewHolder(View itemView) {
            super(itemView);

            price = (TextView) itemView.findViewById(R.id.price_item_type2);
            price_origin = (TextView) itemView.findViewById(R.id.price_origin_item_type2);
            image = (ImageView) itemView.findViewById(R.id.image_item_type2);

        }
    }
}

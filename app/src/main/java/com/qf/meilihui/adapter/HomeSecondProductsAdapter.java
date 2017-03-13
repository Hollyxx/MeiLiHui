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
import com.qf.meilihui.bean.HomeSecondProductBean;

import java.util.List;

/**
 * Created by invoker on 2017/3/13.
 */

public class HomeSecondProductsAdapter extends BaseAdapter{

    private Context context;
    private List<HomeSecondProductBean.ProductsBrandDetailBean> data;

    public HomeSecondProductsAdapter(Context context, List<HomeSecondProductBean.ProductsBrandDetailBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
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
        ProductGridViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_products_categroy, parent, false);
            holder = new ProductGridViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image_item_grid_products_category);
            holder.brand = (TextView) convertView.findViewById(R.id.brand_item_grid_products_category);
            holder.summary = (TextView) convertView.findViewById(R.id.summary_item_grid_products_category);
            holder.price = (TextView) convertView.findViewById(R.id.price_item_grid_products_category);
            holder.recommend = (ImageView) convertView.findViewById(R.id.buyer_recommend_item_grid_products_category);
            holder.price_origin = (TextView) convertView.findViewById(R.id.price_origin_item_grid_products_category);
            holder.onlyOne = (TextView) convertView.findViewById(R.id.only_one_item_grid_products_category);

            convertView.setTag(holder);
        } else {
            holder = (ProductGridViewHolder) convertView.getTag();
        }
        HomeSecondProductBean.ProductsBrandDetailBean product = data.get(position);
        holder.brand.setText(product.getBrandName());
        holder.summary.setText(product.getProductName());
        holder.price.setText("¥"+product.getPrice());
        holder.price_origin.setText("¥"+product.getMarketPrice());
        Glide.with(context).load(product.getImageUrl()).into(holder.image);
        if ("1".equals(product.getIsRecommend())){
            holder.recommend.setVisibility(View.VISIBLE);
        }else{
            holder.recommend.setVisibility(View.INVISIBLE);
        }

        if ("1".equals(product.getSaleableQty())){
            holder.onlyOne.setVisibility(View.VISIBLE);
        }else {
            holder.onlyOne.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    class ProductGridViewHolder {
        ImageView image,recommend;
        TextView brand, summary, price, price_origin,onlyOne;
    }

}

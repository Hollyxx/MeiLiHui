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
import com.qf.meilihui.bean.HotProductsOfBrandDetail;
import com.qf.meilihui.callback.OnRecyclerItemClickListener;

import java.util.List;

/**
 * Created by invoker on 2017/3/8.
 */

public class BrandDetailRecyclerSecondAdapter extends RecyclerView.Adapter<BrandDetailRecyclerSecondAdapter.GViewHolder> {

    private Context context;
    private List<HotProductsOfBrandDetail> data;
    private OnRecyclerItemClickListener listener;

    public BrandDetailRecyclerSecondAdapter(Context context, List<HotProductsOfBrandDetail> data) {
        this.context = context;
        this.data = data;
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public GViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_second_adapter, parent, false);
        GViewHolder holder = new GViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final GViewHolder holder, final int position) {
        HotProductsOfBrandDetail product = data.get(position);
        holder.brand.setText(product.getBrandName());
        holder.summary.setText(product.getProductName());
        holder.price.setText("¥" + product.getPrice());
        holder.price_origin.setText("¥" + product.getMarketPrice());
        Glide.with(context).load(product.getFileUrl()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(position);
            }
        });
        if ("1".equals(product.getIsRecommended())) {
            holder.recommend.setVisibility(View.VISIBLE);
        } else {
            holder.recommend.setVisibility(View.INVISIBLE);
        }

        if ("1".equals(product.getStockQty())) {
            holder.onlyOne.setVisibility(View.VISIBLE);
        } else {
            holder.onlyOne.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class GViewHolder extends RecyclerView.ViewHolder {
        ImageView image, recommend;
        TextView brand, summary, price, price_origin, onlyOne;


        public GViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_item_grid_products_category);
            brand = (TextView) itemView.findViewById(R.id.brand_item_grid_products_category);
            summary = (TextView) itemView.findViewById(R.id.summary_item_grid_products_category);
            price = (TextView) itemView.findViewById(R.id.price_item_grid_products_category);
            recommend = (ImageView) itemView.findViewById(R.id.buyer_recommend_item_grid_products_category);
            price_origin = (TextView) itemView.findViewById(R.id.price_origin_item_grid_products_category);
            onlyOne = (TextView) itemView.findViewById(R.id.only_one_item_grid_products_category);

        }

    }
}

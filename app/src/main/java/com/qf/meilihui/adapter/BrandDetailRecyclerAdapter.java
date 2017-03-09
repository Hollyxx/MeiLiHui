package com.qf.meilihui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.bean.BrandDetailBean;
import com.qf.meilihui.bean.HotProductsOfBrandDetailBean;

import java.util.List;

/**
 * Created by invoker on 2017/3/8.
 */

public class BrandDetailRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Object> data;

    public BrandDetailRecyclerAdapter(Context context, List<Object> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.type1_recycler_brand_detail, parent, false);
                holder = new FirstViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.type2_recycler_brand_detail, parent, false);
                holder = new SecondViewHolder(view);
                break;

            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.type2_recycler_brand_detail, parent, false);
                holder = new SecondViewHolder(view);
                break;


        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = data.get(position);
        switch (getItemViewType(position)) {

            case 0:
                BrandDetailBean.BodyBrandDetailBean.BrandDetailBrandDetailBean detailBean = (BrandDetailBean.BodyBrandDetailBean.BrandDetailBrandDetailBean) o;
                FirstViewHolder holder1 = (FirstViewHolder) holder;
                holder1.logo_title.setText(detailBean.getBrandName());
                if (detailBean.getBrandStoryText().length() < 1) {
                    holder1.layout.setVisibility(View.GONE);
                } else {
                    holder1.summary.setText(detailBean.getBrandStoryText());
                }
                Glide.with(context).load(detailBean.getLogoUrl()).into(holder1.image_logo);
                break;


            case 1:
                SecondViewHolder holder2 = (SecondViewHolder) holder;

                BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean items = (BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean) data.get(position);

                holder2.title_product.setText(items.getEventName());
                List<BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean.ProductBrandDetailBean> product = items.getProduct();
                RecyclerView.LayoutManager manage = new LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false);
                BrandDetailRecyclerFirstAdapter adapter = new BrandDetailRecyclerFirstAdapter(context, product);

                holder2.recyclerView.setAdapter(adapter);
                holder2.recyclerView.setLayoutManager(manage);
                holder2.next_page.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO  点这里小箭头跳转到另一个页面
                    }
                });

                break;
            case 2:
                SecondViewHolder holder3 = (SecondViewHolder) holder;
                HotProductsOfBrandDetailBean products = (HotProductsOfBrandDetailBean) o;
                holder3.title_product.setText(products.getTitle());
                holder3.next_page.setVisibility(View.INVISIBLE);
                BrandDetailRecyclerSecondAdapter adapter1 = new BrandDetailRecyclerSecondAdapter(context, products.getItems());
                holder3.recyclerView.setAdapter(adapter1);
                RecyclerView.LayoutManager manager = new GridLayoutManager(context, 2, OrientationHelper.VERTICAL, false);
                holder3.recyclerView.setLayoutManager(manager);
                
                break;

        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = data.get(position);
        if (o instanceof BrandDetailBean.BodyBrandDetailBean.BrandDetailBrandDetailBean) {
            return 0;
        } else if (o instanceof HotProductsOfBrandDetailBean) {
            return 2;
        } else {
            return 1;
        }
    }


    class FirstViewHolder extends RecyclerView.ViewHolder {
        TextView logo_title, summary;
        ImageView image_logo;
        LinearLayout layout;

        public FirstViewHolder(View itemView) {
            super(itemView);
            logo_title = (TextView) itemView.findViewById(R.id.logo_title_type1);
            summary = (TextView) itemView.findViewById(R.id.summary_brand_type1);
            image_logo = (ImageView) itemView.findViewById(R.id.logo_brand_type1);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_summary_brand_type1);
        }
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {

        TextView title_product;
        ImageView next_page;
        RecyclerView recyclerView;

        public SecondViewHolder(View itemView) {
            super(itemView);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_type2_brand_detail);
            next_page = (ImageView) itemView.findViewById(R.id.go_type2_brand_detail);
            title_product = (TextView) itemView.findViewById(R.id.title_type2_brand_detail);

        }
    }


}

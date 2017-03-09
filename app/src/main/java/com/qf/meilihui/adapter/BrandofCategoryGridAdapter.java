package com.qf.meilihui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.avtivity.AllBrandActivity;
import com.qf.meilihui.avtivity.BrandDetailActivity;
import com.qf.meilihui.bean.Brand;

import java.util.List;

/**
 * Created by invoker on 2017/3/6.
 */

public class BrandofCategoryGridAdapter extends BaseAdapter {

    private Context context;
    private List<Brand> data;
    private String name;
    private String id;

    public BrandofCategoryGridAdapter(Context context, List<Brand> data) {
        this.context = context;
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return data != null ? data.size()+1 : 0;
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
        BrandofCategoryGridViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_item_list_brand_category, parent, false);
            holder = new BrandofCategoryGridViewHolder();

            holder.image = (ImageView) convertView.findViewById(R.id.image_item_grid_list_brand_category);

            convertView.setTag(holder);
        } else {
            holder = (BrandofCategoryGridViewHolder) convertView.getTag();
        }
        if (position==data.size()){

            holder.image.setImageResource(R.mipmap.find_more);
            holder.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            //TODO 跳转到所有品牌的页面
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AllBrandActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("siloId",id);
                    context.startActivity(intent);
                }
            });
        }else {
            Brand brand = data.get(position);
            Glide.with(context).load(brand.getLogoUrl()).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Brand brand = data.get(position);
                    String logoId = brand.getLogoId();
                    String name = brand.getBrandName();
                    Intent intent = new Intent(context, BrandDetailActivity.class);
                    intent.putExtra("logoId",logoId);
                    intent.putExtra("name",name);
                    context.startActivity(intent);
                }
            });
        }


        return convertView;
    }

    class BrandofCategoryGridViewHolder {
        ImageView image;
    }

}

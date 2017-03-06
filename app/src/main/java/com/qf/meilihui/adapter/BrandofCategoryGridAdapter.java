package com.qf.meilihui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.bean.Brand;
import com.qf.meilihui.bean.Category;

import java.util.List;

/**
 * Created by invoker on 2017/3/6.
 */

public class BrandofCategoryGridAdapter extends BaseAdapter {

    private Context context;
    private List<Brand> data;


    public BrandofCategoryGridAdapter(Context context, List<Brand> data) {
        this.context = context;
        this.data = data;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        }else {
            Brand brand = data.get(position);
            Glide.with(context).load(brand.getLogoUrl()).into(holder.image);
        }


        return convertView;
    }

    class BrandofCategoryGridViewHolder {
        ImageView image;
    }

}

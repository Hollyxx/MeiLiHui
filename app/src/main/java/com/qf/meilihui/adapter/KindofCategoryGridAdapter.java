package com.qf.meilihui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.bean.Category;

import java.util.List;

/**
 * Created by invoker on 2017/3/6.
 */

public class KindofCategoryGridAdapter extends BaseAdapter {

    private Context context;
    private List<Category> data;

    public KindofCategoryGridAdapter(Context context, List<Category> data) {
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
        KindofCategoryGridViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_list_kind_category, parent, false);
            holder = new KindofCategoryGridViewHolder();

            holder.image = (ImageView) convertView.findViewById(R.id.image_item_grid_list_kind_category);
            holder.title = (TextView) convertView.findViewById(R.id.title_item_grid_list_kind_category);



            convertView.setTag(holder);

        } else
            holder = (KindofCategoryGridViewHolder) convertView.getTag();

        Category category = data.get(position);
        Glide.with(context).load(category.getCategoryLogoUrl()).into(holder.image);
        holder.title.setText(category.getCategoryName());

        return convertView;
    }

    class KindofCategoryGridViewHolder {
        TextView title;
        ImageView image;

    }
}

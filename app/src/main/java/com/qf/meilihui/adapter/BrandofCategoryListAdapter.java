package com.qf.meilihui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.qf.meilihui.R;
import com.qf.meilihui.bean.Brand;
import com.qf.meilihui.bean.BrandBean;
import com.qf.meilihui.customview.CustomGridView;

import java.util.List;

/**
 * Created by invoker on 2017/3/6.
 */

public class BrandofCategoryListAdapter extends BaseAdapter {

    private Context context;
    private List<BrandBean> data;
    private List<Integer> images;

    public BrandofCategoryListAdapter(Context context, List<BrandBean> data, List<Integer> images) {
        this.context = context;
        this.data = data;
        this.images = images;
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
        BrandofCategoryListViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_brand_category, parent, false);
            holder = new BrandofCategoryListViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image_item_list_brand_category);
            holder.gridView = (CustomGridView) convertView.findViewById(R.id.grid_item_list_brand_category);

            convertView.setTag(holder);
        } else
            holder = (BrandofCategoryListViewHolder) convertView.getTag();

        holder.image.setImageResource(images.get(position));

        BrandBean bean = data.get(position);
        List<Brand> items = bean.getItems();

        BrandofCategoryGridAdapter adapter = new BrandofCategoryGridAdapter(context, items);
        holder.gridView.setAdapter(adapter);
        return convertView;
    }

    class BrandofCategoryListViewHolder {
        ImageView image;
        CustomGridView gridView;
    }

}

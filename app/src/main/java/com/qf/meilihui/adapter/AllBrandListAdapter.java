package com.qf.meilihui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.meilihui.R;
import com.qf.meilihui.avtivity.BrandDetailActivity;
import com.qf.meilihui.bean.AllBrandBean;
import com.qf.meilihui.bean.Brand;

import java.util.List;

/**
 * Created by invoker on 2017/3/9.
 */

public class AllBrandListAdapter extends BaseAdapter {

    private Context context;
    private List<Brand> data;

    public AllBrandListAdapter(Context context, List<Brand> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_all_brand, parent, false);
            holder = new SimpleViewHolder();

            holder.brand = (TextView) convertView.findViewById(R.id.brand_item_list_all_brand);

            convertView.setTag(holder);
        } else {
            holder = (SimpleViewHolder) convertView.getTag();
        }

        final Brand brand = data.get(position);

        holder.brand.setText(brand.getBrandName());
        holder.brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, BrandDetailActivity.class);
                intent.putExtra("name", brand.getBrandName());
                intent.putExtra("logoId", brand.getLogoId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class SimpleViewHolder {
        TextView brand;
    }
}

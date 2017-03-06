package com.qf.meilihui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.meilihui.R;
import com.qf.meilihui.bean.Category;
import com.qf.meilihui.bean.CategoryBean;
import com.qf.meilihui.customview.CustomGridView;

import java.util.List;

/**
 * Created by invoker on 2017/3/6.
 */

public class KindofCategoryListAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryBean> data;

    public KindofCategoryListAdapter(Context context, List<CategoryBean> data) {
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
        KindofCategoryListViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_kind_category, parent, false);
            holder = new KindofCategoryListViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name_item_list_kind_category);
            holder.title = (TextView) convertView.findViewById(R.id.title_item_list_kind_category);
            holder.gridView = (CustomGridView) convertView.findViewById(R.id.grid_item_list_kind_category);

            convertView.setTag(holder);
        } else
            holder = (KindofCategoryListViewHolder) convertView.getTag();

        CategoryBean categoryBean = data.get(position);

        holder.name.setText(categoryBean.getSiloNameEn());
        holder.title.setText(categoryBean.getDisplayName());
        List<Category> items = categoryBean.getItems();

        KindofCategoryGridAdapter adapter = new KindofCategoryGridAdapter(context, items);
        holder.gridView.setAdapter(adapter);

        return convertView;
    }

    class KindofCategoryListViewHolder {
        TextView name, title;
        CustomGridView gridView;
    }
}
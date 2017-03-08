package com.qf.meilihui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;

import java.util.List;

/**
 * Created by 肖 on 2017/3/8.
 */

public class PictureListViewAdapter  extends BaseAdapter {

    private Context context;
    private List<String>  data;

    public PictureListViewAdapter(Context context, List<String> data) {
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
        ViewHolder  viewHolder;

        if(convertView==null){

            convertView= LayoutInflater.from(context).inflate(R.layout.item_third_picture,parent,false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else{
             viewHolder= (ViewHolder) convertView.getTag();
        }
          Glide.with(context).load(data.get(position)).into(viewHolder.iv);

       // Log.i("images",data.size()+"!!!!!!!");
          return convertView;
    }

    class ViewHolder{

        ImageView iv;

        public   ViewHolder(View view){

            iv= (ImageView) view.findViewById(R.id.imageView);
        }
    }
}

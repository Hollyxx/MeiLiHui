package com.qf.meilihui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.meilihui.R;
import com.qf.meilihui.bean.InfoBean;

import java.util.List;

/**
 * Created by 肖 on 2017/3/8.
 */

public class ListInfoAdapter  extends BaseAdapter {

    private Context context;
    private List<InfoBean>  data;

    public ListInfoAdapter(Context context, List<InfoBean> data) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_info,parent,false);

            viewHolder=new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.tv1.setText(data.get(position).getTheme());
        viewHolder.tv2.setText(data.get(position).getAnswer());
       //Log.i("name",data.get(position).getTheme()+",,,,,,,,,,,"+data.get(position).getAnswer());
        return convertView;
    }

    class ViewHolder{
        TextView  tv1,tv2;

        public   ViewHolder(View view){
            tv1= (TextView) view.findViewById(R.id.theme);
            tv2= (TextView) view.findViewById(R.id.answer);
        }
    }
}

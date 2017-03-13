package com.qf.meilihui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.bean.HomeContent;

import java.util.List;

/**
 * Created by 肖 on 2017/3/2.
 */

public class HomeBaseAdapter extends BaseAdapter {

    private Context context;
    private List<HomeContent> data;
    private String math;

    public HomeBaseAdapter(Context context, List<HomeContent> data)
    {
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
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view==null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_hometoday,viewGroup,false);

            viewHolder = new ViewHolder(view);

            view.setTag(viewHolder);

        }else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        math=data.get(position).getEnglishName();
        if(data.get(position).getEnglishName().length()>3){
            math=data.get(position).getEnglishName().substring(0,3);
            Log.i("math",math);
            //判断是否全为数字  不是就重新截取
            if(math.charAt(2)<48||math.charAt(2)>57){
                // Log.i("math",math+"...."+2);
                math=data.get(position).getEnglishName().substring(0,1);
                //Log.i("math",math+"...."+2);
            }
        }else{
            viewHolder.tv.setText("");
        }

        viewHolder.englishName.setText(math);
        viewHolder.chineseName.setText(data.get(position).getChineseName());
        viewHolder.discountText.setText(data.get(position).getDiscountText());

        Glide.with(context).load(data.get(position).getImageUrl()).into(viewHolder.imageUrl);

        return view;
    }

    class ViewHolder
    {
        TextView englishName,chineseName,discountText,tv;
        ImageView imageUrl;

        public  ViewHolder(View view)
        {
            this.imageUrl=(ImageView)view.findViewById(R.id.today_image);
            this.englishName = (TextView) view.findViewById(R.id.today_text4);
            this.tv= (TextView) view.findViewById(R.id.today_text3);
            this.chineseName = (TextView) view.findViewById(R.id.today_text2);
            this.discountText =(TextView) view.findViewById(R.id.today_text1);
        }
    }


}

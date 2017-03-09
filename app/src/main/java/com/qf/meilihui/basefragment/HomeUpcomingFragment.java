package com.qf.meilihui.basefragment;


import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.avtivity.SecondDetailsActivity;
import com.qf.meilihui.bean.HomeContent;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeUpcomingFragment extends Fragment {

    private ListView listView;
    private List<HomeContent> data;
    private MyAdapter adapter;
    public HomeUpcomingFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view=inflater.inflate(R.layout.fragment_home_upcoming, container, false);

        listView= (ListView) view.findViewById(R.id.home_upcoming_listview);

        View v= LayoutInflater.from(getContext()).inflate(R.layout.item_update,null,false);

        volleyGet(Config.TODAY_NEW);

        listView.addHeaderView(v);
        inittime(v);
        return view;
    }

@RequiresApi(api = Build.VERSION_CODES.N)
public  void inittime(View v){
    final   Calendar c = Calendar.getInstance();
    int mHour = c.get(Calendar.HOUR_OF_DAY);//获取当前的小时数

    int mMinute =  c.get(Calendar.MINUTE);//获取当前的分钟数

    TextView tv= (TextView) v.findViewById(R.id.uptime);
    TextView text=(TextView) v.findViewById(R.id.up_text);

    int hour=(mHour)-8;
    if(hour<0){
        hour=hour+24;
    }
    if(mHour<23) {
        if(mMinute<=30){
            tv.setText(hour+"");
            text.setText("小时");
        }else{
            tv.setText((hour-1)+"");
            text.setText("小时");
        }

    }else{
        tv.setText(mMinute+"");
        text.setText("分钟");
    }

}
    public  void volleyGet(String url){
        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               // Log.i("objectf",response.toString());
                List<HomeContent> data = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("lists");

                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        JSONArray array = jsonObject.getJSONArray("events");

                        for(int j=0;j<array.length();j++){
                            JSONObject jsonObject2 = array.getJSONObject(j);
                            Log.i("length",jsonObject2.toString());
                            String englishName = jsonObject2.getString("englishName");
                            String chineseName = jsonObject2.getString("chineseName");
                            String discountText = jsonObject2.getString("discount");
                            String  imageUrl=jsonObject2.getString("imgUrl");
                            String  categoryId=jsonObject2.getString("categoryId");

                            data.add(new HomeContent(englishName,imageUrl,chineseName,discountText,categoryId));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new MyAdapter(getContext(),data);
                listView.setAdapter(adapter);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        //3, 将请求对象添加到请求队列中
        MyApp.getHttpQueue().add(objectRequest);
    }
    class MyAdapter extends BaseAdapter{
        private Context context;
        private List<HomeContent> data;
        private String math;

        public MyAdapter(Context context, List<HomeContent> data)
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
        public View getView(final int position, View view, ViewGroup viewGroup) {

           ViewHolder viewHolder;

            if (view==null)
            {
                view = LayoutInflater.from(context).inflate(R.layout.item_home_new,viewGroup,false);

                viewHolder = new ViewHolder(view);

                view.setTag(viewHolder);

            }else
            {
                viewHolder = (ViewHolder) view.getTag();
            }
            math=data.get(position).getEnglishName();
            if(data.get(position).getEnglishName().length()>0){
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

            viewHolder.imageUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(),SecondDetailsActivity.class);
//                    http://www.mei.com/appapi/event/product/v3?sort=&categoryId=
//                     String path= http://www.mei.com/appapi/event/product/v3?summary=fe1b2dc51d27f447976a70a00fdcfe06&sort=&timeStamp=20170309140854&pageIndex=1&categoryId=2040204090000002399
                    String secondUrl="http://www.mei.com/appapi/event/product/v3?&pageIndex=1"+"&categoryId="+data.get(position).getEventId();
                    intent.putExtra("web",secondUrl);
                    intent.putExtra("id",data.get(position).getEventId());
                    Log.i("secondUrl",secondUrl);
                    intent.putExtra("englishName", data.get(position).getDiscountText());

                    startActivity(intent);
                }
            });
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
}

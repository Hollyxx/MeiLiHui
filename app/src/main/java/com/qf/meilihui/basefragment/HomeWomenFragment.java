package com.qf.meilihui.basefragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class HomeWomenFragment extends Fragment {
    private ListView  listView;
    private TextView tv;
    private  List<HomeContent>  data;
    public HomeWomenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_women, container, false);
        listView= (ListView) view.findViewById(R.id.home_women_listview);

        tv=(TextView) view.findViewById(R.id.my_tv);

        listView.setEmptyView(tv);
        volleyGet(Config.TODAY_WOMEN);

        return view;
    }
    private void volleyGet(String url)
    {
        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("object",response.toString());
                data = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("eventList");
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        String englishName = jsonObject.getString("englishName");
                        String chineseName = jsonObject.getString("chineseName");
                        String discountText = jsonObject.getString("discountText");
                        String  imageUrl=jsonObject.getString("imageUrl");
                        String  categoryId=jsonObject.getString("categoryId");

                        data.add(new HomeContent(englishName,imageUrl,chineseName,discountText,categoryId));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MyAdapter adapter = new MyAdapter(getContext(),data);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getContext(),SecondDetailsActivity.class);

                        String secondUrl=Config.TODAY_SECOND_CONTENT+data.get(position).getEventId()+"&pageIndex=1";
                        intent.putExtra("web",secondUrl);
                        intent.putExtra("id",data.get(position).getEventId());
                        //Log.i("id",data.get(position-1).getEventId());
                        intent.putExtra("englishName", data.get(position).getDiscountText());
                        //Log.i("englishName",data.get(position-1).getEventId());
                        startActivity(intent);
                    }
                });
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("网络请求失败");
            }
        });
        //3, 将请求对象添加到请求队列中
        MyApp.getHttpQueue().add(objectRequest);
    }
    class MyAdapter extends BaseAdapter {

    private Context context;
    private List<HomeContent> data;

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
    public View getView(int position, View view, ViewGroup viewGroup) {

        MyAdapter.ViewHolder viewHolder;

        if (view==null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_hometoday,viewGroup,false);

            viewHolder = new MyAdapter.ViewHolder(view);

            view.setTag(viewHolder);

        }else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

//        String  math=data.get(position).getEnglishName().substring(0,3);
//        Log.i("math",math);
//        //判断是否全为数字  不是就重新截取
//        if(math.charAt(2)<48||math.charAt(2)>57){
//            // Log.i("math",math+"...."+2);
//            math=data.get(position).getEnglishName().substring(0,1);
//            //Log.i("math",math+"...."+2);
//        }

        viewHolder.englishName.setText(data.get(position).getEnglishName());
        viewHolder.chineseName.setText(data.get(position).getChineseName());
        viewHolder.discountText.setText(data.get(position).getDiscountText());

        Glide.with(HomeWomenFragment.this).load(data.get(position).getImageUrl()).into(viewHolder.imageUrl);

        return view;
    }

    class ViewHolder
    {
        TextView englishName,chineseName,discountText;
        ImageView imageUrl;

        public  ViewHolder(View view)
        {
            this.imageUrl=(ImageView)view.findViewById(R.id.today_image);
            this.englishName = (TextView) view.findViewById(R.id.today_text4);
            this.chineseName = (TextView) view.findViewById(R.id.today_text2);
            this.discountText =(TextView) view.findViewById(R.id.today_text1);
        }
    }
}
}

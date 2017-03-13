package com.qf.meilihui.basefragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
public class HomeMenFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<HomeContent> data,dataAll;
    private ListView listView;
    private TextView tv;
    private int total;
    private  String path;
    private int page;
    private   MyAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private Handler handler = new Handler();
    private boolean isBottom = false;

    public HomeMenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_men, container, false);

        listView= (ListView) view.findViewById(R.id.home_men_listview);
        dataAll=new ArrayList<>();
        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.men_layout);
        tv=(TextView) view.findViewById(R.id.men_tv);

        listView.setEmptyView(tv);
        path=Config.TODAY_MEN;

        volleyGet(path+page+"&categoryId=2013000100000000002");

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);//设置下拉刷新的源泉背景
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);
        refreshLayout.setColorSchemeColors(Color.BLUE,Color.YELLOW,Color.RED,Color.DKGRAY);
        return view;
    }

    private void volleyGet(String url)
    {

        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               //Log.i("object",response.toString());

                data = new ArrayList<>();
                try {
                    String num= response.getString("totalPages");
                    total=Integer.parseInt(num);
                    Log.i("total",total+".....");
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
                      dataAll.addAll(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new MyAdapter(getContext(),dataAll);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        if (isBottom && scrollState == SCROLL_STATE_IDLE) {
                            //dataAll.clear();
                            adapter.notifyDataSetChanged();
                            if (page<total){
                                page++;
                                Log.i("page",page+"///");
                                //adapter.notifyDataSetChanged();
                                volleyGet(path+page+"&categoryId=2013000100000000002");
                            }else{
                                Toast.makeText(getContext(),"加载到底部了", Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        isBottom = firstVisibleItem + visibleItemCount == totalItemCount;
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getContext(),SecondDetailsActivity.class);

                        String secondUrl=Config.TODAY_SECOND_CONTENT+dataAll.get(position).getEventId()+"&pageIndex=";
                        //Log.i("secondUrl",secondUrl);
                        intent.putExtra("web",secondUrl);
                        intent.putExtra("id",dataAll.get(position).getEventId());
                        //Log.i("id",data.get(position-1).getEventId());
                        intent.putExtra("englishName", dataAll.get(position).getDiscountText());
                        //Log.i("englishName",data.get(position-1).getEventId());
                        startActivity(intent);
                    }
                });
                page++;
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

    @Override
    public void onRefresh() {
        dataAll.clear();
        volleyGet(path+1+"&categoryId=2013000100000000002");
         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
                 refreshLayout.setRefreshing(false);
             }
         },2000);
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

            String  math=data.get(position).getEnglishName().substring(0,3);
            Log.i("math",math);
            //判断是否全为数字  不是就重新截取
            if(math.charAt(2)<48||math.charAt(2)>57){
                // Log.i("math",math+"...."+2);
                math=data.get(position).getEnglishName().substring(0,1);
                //Log.i("math",math+"...."+2);
            }

            viewHolder.englishName.setText(math);
            viewHolder.chineseName.setText(data.get(position).getChineseName());
            viewHolder.discountText.setText(data.get(position).getDiscountText());

            Glide.with(HomeMenFragment.this).load(data.get(position).getImageUrl()).into(viewHolder.imageUrl);

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

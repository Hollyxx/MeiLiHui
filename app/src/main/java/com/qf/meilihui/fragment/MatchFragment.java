package com.qf.meilihui.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.qf.meilihui.avtivity.BagActivity;
import com.qf.meilihui.avtivity.HeadViewDetailsActivity;
import com.qf.meilihui.bean.MatchContent;
import com.qf.meilihui.mylayout.RefreshLayout;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.qf.meilihui.R.id.match_iv;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,RefreshLayout.OnLoadingListener{

    private ImageView imageView;
    private String path;
    private int page=1;
    private RefreshLayout refreshLayout;
    private Handler handler = new Handler();
    private ListView listView;
    private List<MatchContent> data,dataAll;
    public static final String TAG = "MatchFragment";
    public MatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_match, container, false);

        listView= (ListView) view.findViewById(R.id.match_listview);
        imageView= (ImageView) view.findViewById(R.id.match_imageview);
        dataAll=new ArrayList<>();
        refreshLayout= (RefreshLayout) view.findViewById(R.id.match_layout);
        path=Config.MATCH+page;
        volleyGet(Config.MATCH);
        replaceFragment();

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadingListener(this);
        refreshLayout.setColorSchemeColors(Color.BLUE,Color.YELLOW,Color.RED,Color.DKGRAY);
        return  view;
    }
    public void replaceFragment(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), BagActivity.class);

                startActivity(intent);

            }
        });
    }

    private void volleyGet(String url)
    {
        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                 data = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("eventdaysList");
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String titleNew1 = jsonObject.getString("titleNew1");
                        String titleNew2 = jsonObject.getString("titleNew2");
                        String imgUrl = jsonObject.getString("imgUrl");
                        String linkUrl=jsonObject.getString("linkUrl");
                        //Log.i("match",titleNew1);
                        data.add(new MatchContent(id,titleNew1,titleNew2,imgUrl,linkUrl));
                    }
                     dataAll.addAll(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MyAdapter adapter = new MyAdapter(getContext(),dataAll);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getActivity(), HeadViewDetailsActivity.class);
                        intent.putExtra("path",data.get(position).getLinkUrl());
                        intent.putExtra("title",data.get(position).getTitleNew1());
                        startActivity(intent);
                    }
                });
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("网络请求失败");
            }
        });

        //3, 将请求对象添加到请求队列中
        MyApp.getHttpQueue().add(objectRequest);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //下拉刷新
                dataAll.clear();
                volleyGet(path);
                refreshLayout.setRefreshing(false);
            }
        },5000);
    }

    @Override
    public void onLoad() {
        //listView.setEnabled(false);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                volleyGet(path);
                refreshLayout.setRefreshing(false);
                //listView.setEnabled(true);
            }
        },5000);
    }

    class MyAdapter extends BaseAdapter{

        private Context context;
        private List<MatchContent> data;

        public MyAdapter(Context context, List<MatchContent> data)
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
                view = LayoutInflater.from(context).inflate(R.layout.item_journal,viewGroup,false);

                viewHolder = new ViewHolder(view);

                view.setTag(viewHolder);

            }else
            {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.titleNew1.setText(data.get(position).getTitleNew1());
            viewHolder.titleNew2.setText(data.get(position).getTitleNew2());

            Glide.with(MatchFragment.this).load(data.get(position).getImgUrl()).into(viewHolder.imgUrl);

            return view;
        }

        class ViewHolder
        {
            TextView id,titleNew1,titleNew2;
            ImageView imgUrl;

            public  ViewHolder(View view)
            {
                this.imgUrl=(ImageView)view.findViewById(match_iv);
                this.titleNew1 = (TextView) view.findViewById(R.id.match_tv1);
                this.titleNew2 = (TextView) view.findViewById(R.id.match_tv2);
            }
        }
    }

}

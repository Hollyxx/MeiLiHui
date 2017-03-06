package com.qf.meilihui.basefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.HomeBaseAdapter;
import com.qf.meilihui.app.MyApp;
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
public class HomeFurnishingFragment extends Fragment {
    private ListView  listView;

    private HomeBaseAdapter adapter;
    public HomeFurnishingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_furnishing, container, false);

        listView= (ListView) view.findViewById(R.id.home_furnishing_listview);

        volleyGet(Config.TODAY_FURNISHING);

        return  view;

    }
    public  void volleyGet(String url){
        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("objectf",response.toString());
                List<HomeContent> data = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("eventList");
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        String englishName = jsonObject.getString("englishName");
                        String chineseName = jsonObject.getString("chineseName");
                        String discountText = jsonObject.getString("discountText");
                        String  imageUrl=jsonObject.getString("imageUrl");

//                        Log.i("furn",englishName+i);
//                        Log.i("furn",chineseName+i);
//                        Log.i("furn",discountText+i);
                        data.add(new HomeContent(englishName,imageUrl,chineseName,discountText));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new HomeBaseAdapter(getContext(),data);
                listView.setAdapter(adapter);
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
}

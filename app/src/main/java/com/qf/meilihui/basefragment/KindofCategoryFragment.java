package com.qf.meilihui.basefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.KindofCategoryListAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.CategoryBean;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KindofCategoryFragment extends Fragment {

    private Context context;
    private ListView list;
    private List<CategoryBean> data;
    private KindofCategoryListAdapter adapter;

    public KindofCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kindof_category, container, false);
        list = (ListView) view.findViewById(R.id.list_kind_category);
        data = new ArrayList<>();
        adapter = new KindofCategoryListAdapter(context,data);
        list.setAdapter(adapter);
        initData();

        return view;
    }

    private void initData() {
        StringRequest request = new StringRequest(Config.Category_Kinds, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray array = object.getJSONArray("silos");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject json = array.getJSONObject(i);
                            CategoryBean bean = new CategoryBean();
                            bean.parseJson(json);
                            data.add(bean);
                        }
                        adapter.notifyDataSetChanged();
                    }catch (Exception e){

                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MyApp.getHttpQueue().add(request);
    }

}

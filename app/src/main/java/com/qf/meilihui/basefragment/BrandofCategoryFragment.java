package com.qf.meilihui.basefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.BrandofCategoryListAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.BrandBean;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrandofCategoryFragment extends Fragment {

    private Context context;
    private List<BrandBean> data;
    private List<Integer> images;
    private BrandofCategoryListAdapter adapter;
    private ListView list;
    private String[] title = {"womenLogo", "menLogo", "beautyLogo", "homeLogo", "kidsLogo"};

    public BrandofCategoryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_brandof_category, container, false);
        initView(view);
        initData();
        adapter = new BrandofCategoryListAdapter(context, data, images);
        list.setAdapter(adapter);

        return view;
    }

    private void initData() {
        images = new ArrayList<>();
        data = new ArrayList<>();
        images.add(R.mipmap.sort_brand_women);
        images.add(R.mipmap.sort_brand_men);
        images.add(R.mipmap.sort_brand_beauty);
        images.add(R.mipmap.sort_brand_home);
        images.add(R.mipmap.sort_brand_kids);

        StringRequest request = new StringRequest(Config.Category_Brand, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONObject body = object.getJSONObject("body");
                        int length = title.length;
                        for (int i = 0; i < length; i++) {
                            BrandBean bean = new BrandBean();
                            JSONObject json = body.getJSONObject(title[i]);
                            bean.parseJson(json);
                            data.add(bean);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
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

    private void initView(View view) {
        list = (ListView) view.findViewById(R.id.list_brand_category);
    }

}

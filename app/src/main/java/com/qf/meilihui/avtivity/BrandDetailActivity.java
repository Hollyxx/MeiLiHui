package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.BrandDetailRecyclerAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.BrandDetailBean;
import com.qf.meilihui.bean.HotProductsOfBrandDetailBean;
import com.qf.meilihui.customview.PullToZoomListView;
import com.qf.meilihui.uri.Config;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BrandDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView title;
    private String logoId;
    private List<Object> data;
    private  int page =1;
    private BrandDetailRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_brand_detail_activity);
        title = (TextView) findViewById(R.id.logo_toolbar_brand_detail_activity);
        Intent intent = getIntent();
        logoId = intent.getStringExtra("logoId");
        data = new ArrayList<>();
        adapter = new BrandDetailRecyclerAdapter(this, data);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, OrientationHelper.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        loadData();


    }

    private void updateData(int page) {

        StringRequest request = new StringRequest(Config.Category_Second_Brand_Detail_HotProducts + logoId + "&pageIndex=" + page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject json = new JSONObject(response);
                        JSONObject object = json.getJSONObject("body");
                        HotProductsOfBrandDetailBean parent = new HotProductsOfBrandDetailBean();
                        parent.parseJson(object);
                        data.add(parent);
                        adapter.notifyDataSetChanged();

                    }catch (Exception e){
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

    private void loadData() {

        StringRequest request = new StringRequest(Config.Category_Second_Brand + logoId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                BrandDetailBean bean = gson.fromJson(response, BrandDetailBean.class);
                String brandName = bean.getBody().getBrandDetail().getBrandName();
                title.setText(brandName);
                data.add(bean.getBody().getBrandDetail());
                data.addAll(bean.getBody().getNewProduct());
                adapter.notifyDataSetChanged();
                updateData(page);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MyApp.getHttpQueue().add(request);
    }


    //点击返回按钮，直接finish掉
    public void onClick(View view) {
        finish();
    }
}

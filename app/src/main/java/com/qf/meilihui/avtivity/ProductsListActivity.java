package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.ProductOfKindsGridAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.ProductsOfCategory;
import com.qf.meilihui.bean.ProductsofCategoryBean;
import com.qf.meilihui.uri.Config;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    private TextView title;
    private GridView grid;
    private String url;
    private int page = 1;
    private boolean isBottom = false;
    private List<ProductsOfCategory> data;
    private ProductsofCategoryBean bean;
    private ProductOfKindsGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_products_list);
        Intent intent = getIntent();
        String siloId = intent.getStringExtra("siloId");
        String categoryId = intent.getStringExtra("categoryId");
        String summary = intent.getStringExtra("summary");
        String name = intent.getStringExtra("name");
        url = Config.Category_Second_Kinds + "siloId=" + siloId + "&categoryId=" + categoryId + "&summary=" + summary + "&pageIndex=";
        title = (TextView) findViewById(R.id.title_title_bar_products);
        grid = (GridView) findViewById(R.id.grid_products);
        title.setText(name);
        data = new ArrayList<>();
        adapter = new ProductOfKindsGridAdapter(this, data);
        loadData(page);
        grid.setAdapter(adapter);

        grid.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isBottom && scrollState == SCROLL_STATE_IDLE) {
                    page++;
                    loadData(page);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isBottom = firstVisibleItem + visibleItemCount == totalItemCount;
            }
        });

    }

    private void loadData(int page) {
        StringRequest request = new StringRequest(url + page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    bean = new ProductsofCategoryBean();
                    try {
                        JSONObject o = new JSONObject(response);
                        bean.parseJson(o);
                        List<ProductsOfCategory> items = bean.getItems();
                        data.addAll(items);
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

    public void onClick(View view) {
        finish();
    }
}

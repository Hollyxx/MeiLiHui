package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ScrollView;
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
import com.qf.meilihui.customview.CustomGridView;
import com.qf.meilihui.uri.Config;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
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
    private String categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_products_list);
        final Intent intent = getIntent();
        String siloId = intent.getStringExtra("siloId");
        categoryId = intent.getStringExtra("categoryId");
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

        //点击每个单条进行3级页面跳转，进入商品详情页
        //底部数据需要拼接productsId 和 categoryId
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductsOfCategory product = data.get(position);
                Intent intent1 = new Intent(ProductsListActivity.this,ThirdDetailsActivity.class);
                String  thirdAddress= Config.TODAY_THIRD_CONTENT+product.getProductId();
                String  Hot_recommendation= Config.Hot_recommendation+product.getProductId()+"&categoryId="+ product.getEvent_id();
                intent1.putExtra("Hot_recommendation",Hot_recommendation);
                intent1.putExtra("thirdAddress",thirdAddress);
                intent1.putExtra("price",product.getPrice());
                intent1.putExtra("marketPrice",product.getMarketPrice());
                intent1.putExtra("name",product.getBrandName());
                intent1.putExtra("productName",product.getProductName());
                startActivity(intent1);
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

    public void sortClick(View view) {
        switch (view.getId()) {
            case R.id.price_sort:
                Collections.sort(data);
                adapter.notifyDataSetChanged();
                break;
        }


    }
}

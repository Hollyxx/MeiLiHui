package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.ProductOfKindsGridAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.ProductsOfCategory;
import com.qf.meilihui.bean.ProductsofCategoryBean;
import com.qf.meilihui.customview.CustomLoadingLayout;
import com.qf.meilihui.uri.Config;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeSecondProductsActivity extends AppCompatActivity {

    private TextView title;
    private GridView grid;
    private String url;
    private int page = 1;
    private boolean isBottom = false;
    private List<ProductsOfCategory> data;
    private ProductsofCategoryBean bean;
    private ProductOfKindsGridAdapter adapter;
    private String categoryId;
    private TextView popular_sort, discount_sort, price_sort;
    private ImageView icon_price_sort;
    private boolean flag = false;
    private CustomLoadingLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_products_list);

        initView();
        setLoading();
        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        url = Config.Home_Second_products + categoryId+"&pageIndex =";
        data = new ArrayList<>();
        adapter = new ProductOfKindsGridAdapter(this, data);
        grid.setAdapter(adapter);
        setListener();
        loadData(url,page);
    }

    private void setListener() {
        grid.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isBottom && scrollState == SCROLL_STATE_IDLE) {
                    page++;
                    loadData(url, page);
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
                Intent intent1 = new Intent(HomeSecondProductsActivity.this, ThirdDetailsActivity.class);
                String thirdAddress = Config.TODAY_THIRD_CONTENT + product.getProductId();
                String Hot_recommendation = Config.Hot_recommendation + product.getProductId() + "&categoryId=" + product.getEvent_id();
                intent1.putExtra("Hot_recommendation", Hot_recommendation);
                intent1.putExtra("thirdAddress", thirdAddress);
                intent1.putExtra("price", product.getPrice());
                intent1.putExtra("marketPrice", product.getMarketPrice());
                intent1.putExtra("name", product.getBrandName());
                intent1.putExtra("productName", product.getProductName());
                startActivity(intent1);
            }
        });

    }

    //设置加载动画
    private void setLoading() {
        loading.showLoadUi(false, 0);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title_bar_products);
        loading = (CustomLoadingLayout) findViewById(R.id.loading_layout);
        grid = (GridView) findViewById(R.id.grid_products);
        price_sort = (TextView) findViewById(R.id.price_sort);
        popular_sort = (TextView) findViewById(R.id.popular_sort);
        discount_sort = (TextView) findViewById(R.id.discount_sort);
        icon_price_sort = (ImageView) findViewById(R.id.icon_price_sort);
    }


    //按照不同的东西进行排序
    public void sortClick(View view) {

        switch (view.getId()) {

            case R.id.popular_sort:
                popular_sort.setTextColor(Color.BLACK);
                icon_price_sort.setImageResource(R.mipmap.down_grey);
                price_sort.setTextColor(getResources().getColor(R.color.gray));
                discount_sort.setTextColor(getResources().getColor(R.color.gray));
                page = 1;
                url = Config.Home_Second_products + "&categoryId=" + categoryId + "&pageIndex=";
                data.clear();
                loadData(url, page);
                break;

            case R.id.price_sort:
                price_sort.setTextColor(Color.BLACK);
                popular_sort.setTextColor(getResources().getColor(R.color.gray));
                discount_sort.setTextColor(getResources().getColor(R.color.gray));
                if (!flag) {
                    flag = true;
                    page = 1;
                    icon_price_sort.setImageResource(R.mipmap.up_black);
                    url = Config.Home_Second_products + "&sort=ASC&categoryId=" + categoryId + "&pageIndex=";
                    data.clear();
                    loadData(url, page);
                } else {
                    flag = false;
                    page = 1;
                    icon_price_sort.setImageResource(R.mipmap.down_black);
                    url = Config.Home_Second_products + "&sort=DESC&categoryId=" + categoryId + "&pageIndex=";
                    data.clear();
                    loadData(url, page);
                }

                break;

            case R.id.discount_sort:
                discount_sort.setTextColor(Color.BLACK);
                price_sort.setTextColor(getResources().getColor(R.color.gray));
                popular_sort.setTextColor(getResources().getColor(R.color.gray));
                icon_price_sort.setImageResource(R.mipmap.down_grey);
                page = 1;
                url = Config.Home_Second_products + "&sort=ASC&key=1&categoryId=" + categoryId + "&pageIndex=";
                data.clear();
                loadData(url, page);
                break;

            case R.id.filter_sort:
                break;
        }
    }


    private void loadData(String url, int page) {
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
                        loading.showLoadUi(true, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                dialog.dismiss();
            }
        });
        MyApp.getHttpQueue().add(request);
    }
}

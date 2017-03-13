package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.SecondDetailsAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.TodaySecondDetails;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondDetailsActivity extends AppCompatActivity {

    private String path;
    private int flag = 0;
    private ImageView price;
    private TextView title, tv1, tv2, tv3, tv4;
    private List<String> name;
    private GridView gridView;
    private LinearLayout layout;
    private String categoryId;
    private String web;
    private int page = 1;
    private SecondDetailsAdapter adapter;
    private boolean isBottom = false;
    private List<TodaySecondDetails> data, dataAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second_details);

        Intent intent = getIntent();
        web = intent.getStringExtra("web");
        path = web + page;
        categoryId = intent.getStringExtra("id");
        String englishName = intent.getStringExtra("englishName");
        init();
        dataAll = new ArrayList<>();
        title.setText(englishName);

        volleyGet(path);


    }

    public void init() {
        title = (TextView) findViewById(R.id.title_title_bar_products);
        tv1 = (TextView) findViewById(R.id.popular_sort);
        tv1.setTextColor(Color.BLACK);
        tv2 = (TextView) findViewById(R.id.discount_sort);
        tv3 = (TextView) findViewById(R.id.price_sort);
        tv4 = (TextView) findViewById(R.id.filter_sort);
        price = (ImageView) findViewById(R.id.second_details_iv);
        gridView = (GridView) findViewById(R.id.grid_products);
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.back_title_bar_products:

                finish();

                break;
            case R.id.Deatails_imageview:

                Intent intent = new Intent(getApplicationContext(), BagActivity.class);

                startActivity(intent);
                break;
            case R.id.popular_sort:
                price.setImageResource(R.mipmap.up_grey);
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.GRAY);
                tv3.setTextColor(Color.GRAY);
                volleyGet(path);
                adapter.notifyDataSetChanged();
                break;
            case R.id.discount_sort:
                web = "http://www.mei.com/appapi/event/product/v3?&sort=ASC&key=1&pageIndex=1&categoryId=" + categoryId;
                volleyGet(web);
                adapter.notifyDataSetChanged();
                price.setImageResource(R.mipmap.up_grey);
                tv1.setTextColor(Color.GRAY);
                tv2.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.GRAY);
                break;
            case R.id.price_sort:
                price.setImageResource(R.mipmap.up_black);
                tv1.setTextColor(Color.GRAY);
                tv2.setTextColor(Color.GRAY);
                tv3.setTextColor(Color.BLACK);
                flag++;
                if (flag == 1) {
                    web = "http://www.mei.com/appapi/event/product/v3?&sort=ASC&pageIndex=1&categoryId=" + categoryId;
                    volleyGet(web);
                    adapter.notifyDataSetChanged();
                }
                if (flag == 2) {
                    price.setImageResource(R.mipmap.down_black);
                    web = "http://www.mei.com/appapi/event/product/v3?&sort=DESC&pageIndex=1&categoryId=" + categoryId;
                    volleyGet(web);
                    adapter.notifyDataSetChanged();
                    flag = 0;
                }
                break;
            case R.id.filter_sort:
                tv4.setTextColor(Color.BLACK);
                break;
        }
    }

    public void volleyGet(String url) {
        final JsonObjectRequest objectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Log.i("object", response.toString());
                data = new ArrayList<>();
                name = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("products");
                    for (int i = 0; i < lists.length(); i++) {

                        JSONObject jsonObject = lists.getJSONObject(i);
                        String productId = jsonObject.getString("productId");
                        String productName = jsonObject.getString("productName");

                        String product_type = jsonObject.getString("product_type");
                        String brandName = jsonObject.getString("brandName");
                        String price = jsonObject.getString("price");
                        // Log.i("productName", price);
                        String marketPrice = jsonObject.getString("marketPrice");
                        String imageUrl = jsonObject.getString("imageUrl");
                        String discount = jsonObject.getString("discount");
                        String isRecommend = jsonObject.getString("isRecommend");
                        String saleableQty = jsonObject.getString("saleableQty");
                        data.add(new TodaySecondDetails(productId, productName, price, brandName, price, marketPrice, imageUrl, discount, isRecommend, saleableQty));
                    }
                    dataAll.addAll(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new SecondDetailsAdapter(getApplicationContext(), dataAll, name);
                gridView.setAdapter(adapter);
                gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        if (isBottom && scrollState == SCROLL_STATE_IDLE) {
                            page++;
                            adapter.notifyDataSetChanged();
                            volleyGet(web + page);
                            gridView.smoothScrollToPositionFromTop((page - 1) * 10 * 200, 100, 1000);
                            //gridView.setSelection(((page-1)*9));
                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        isBottom = firstVisibleItem + visibleItemCount == totalItemCount;
                    }
                });
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(SecondDetailsActivity.this, ThirdDetailsActivity.class);
                        String thirdAddress = Config.TODAY_THIRD_CONTENT + data.get(position).getProductId();
                        String Hot_recommendation = Config.Hot_recommendation + data.get(position).getProductId() + "&categoryId=" + categoryId;

                        intent.putExtra("Hot_recommendation", Hot_recommendation);
                        intent.putExtra("thirdAddress", thirdAddress);
                        intent.putExtra("price", data.get(position).getPrice());
                        intent.putExtra("marketPrice", data.get(position).getMarketPrice());
                        intent.putExtra("name", data.get(position).getBrandName());
                        intent.putExtra("productName", data.get(position).getProductName());
                        intent.putExtra("discount", data.get(position).getDiscount());
                        startActivity(intent);
                    }
                });
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("网络请求失败");
            }
        });
        final JsonObjectRequest objectRequest2 = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject object = response.getJSONObject("couponScheme");

                    JSONArray otherCoupon = object.getJSONArray("otherCoupon");
                    for (int i = 0; i < otherCoupon.length(); i++) {

                        JSONObject jsonObject = otherCoupon.getJSONObject(i);
                        String info = jsonObject.getString("couponContent");
                        TextView tv = (TextView) findViewById(R.id.deatails_text);

                        layout = (LinearLayout) findViewById(R.id.deatails_linear);

                        if (info != null) {
                            tv.setText(info);
                        } else if (info.isEmpty() == true) {
                            layout.setVisibility(View.GONE);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("网络请求失败");
            }
        });
        MyApp.getHttpQueue().add(objectRequest);
        MyApp.getHttpQueue().add(objectRequest2);
    }
}

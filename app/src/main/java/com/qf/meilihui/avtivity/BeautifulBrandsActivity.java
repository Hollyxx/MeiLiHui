package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.BeautifulBrandsAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.OverSeaProductList;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BeautifulBrandsActivity extends AppCompatActivity {
    private GridView  gridView;
    private TextView   textview;
    private int total;
    private List<OverSeaProductList> dataProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_beautiful_brands);
        Intent  intent=getIntent();
        String path=intent.getStringExtra("path");
        String name=intent.getStringExtra("name");
        gridView= (GridView) findViewById(R.id.beautiful_brands_gridview);
        textview = (TextView) findViewById(R.id.brands_bar_recommend);
        textview.setText(name);
        volleyGet(path);
    }

    public  void volleyGet(String url){

        final JsonObjectRequest  jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject productInfo = response.getJSONObject("productInfo");
                    String totalPage=productInfo.getString("totalPage");
                    total=Integer.parseInt(totalPage);

                    JSONArray resultList = productInfo.getJSONArray("resultList");
                    dataProduct=new ArrayList<>();
                    for (int i=0;i<resultList.length();i++){
                        JSONObject jsonObject = resultList.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String eventId=jsonObject.getString("eventId");
                        String productName=jsonObject.getString("productName");
                        String productImgUrl=jsonObject.getString("productImgUrl");
                        String brand=jsonObject.getString("brand");
                        String price=jsonObject.getString("price") ;
                        String marketPrice=jsonObject.getString("marketPrice");
                        String discount=jsonObject.getString("discount");
                        dataProduct.add(new OverSeaProductList(id,eventId,productName,productImgUrl,brand,price,marketPrice,discount));;
                    }
                    BeautifulBrandsAdapter adapter=new BeautifulBrandsAdapter(BeautifulBrandsActivity.this,dataProduct);

                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent  intent=new Intent(BeautifulBrandsActivity.this,ThirdDetailsActivity.class);
                            String thirdAddress = Config.TODAY_THIRD_CONTENT +dataProduct.get(position).getId()+ "&categoryId="+ dataProduct.get(position).getEventId();
                            Log.i("thirdAddress",thirdAddress);
                            String Hot_recommendation = Config.Hot_recommendation + dataProduct.get(position).getId() + "&categoryId=" +  dataProduct.get(position).getEventId();

                            intent.putExtra("Hot_recommendation", Hot_recommendation);
                            intent.putExtra("thirdAddress", thirdAddress);
                            intent.putExtra("price", dataProduct.get(position).getPrice());
                            intent.putExtra("marketPrice", dataProduct.get(position).getMarketPrice());
                            intent.putExtra("name", dataProduct.get(position).getBrand());
                            intent.putExtra("productName", dataProduct.get(position).getProductName());
                            intent.putExtra("discount", dataProduct.get(position).getDiscount());

                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApp.getHttpQueue().add(jsonObjectRequest);
    }
    public void  onClick(View  view){
    switch (view.getId())
        {
        case R.id.brands_title_bar_back:
         finish();
        break;
        }
    }


}

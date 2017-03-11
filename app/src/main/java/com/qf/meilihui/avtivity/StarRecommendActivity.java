package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.StarAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.StarRecommendBean;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StarRecommendActivity extends AppCompatActivity {
private ListView listView;
    private ImageView  iv;
    private List<StarRecommendBean>  data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start_recommend);
         volleyGet(Config.Star);
        iv= (ImageView) findViewById(R.id.recommend_title_bar_back);
        listView= (ListView) findViewById(R.id.star_listview);
        listView.setDivider(new ColorDrawable(Color.RED));
        listView.setDividerHeight(1);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public    void  volleyGet(String url){

       final JsonObjectRequest   jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               try {
                   JSONArray starProductList = response.getJSONArray("starProductList");
                   data=new ArrayList<>();
                   for (int i=0;i<starProductList.length();i++){
                       JSONObject jsonObject = starProductList.getJSONObject(i);
                       String id=jsonObject.getString("id");
                       String eventCode=jsonObject.getString("eventCode");
                       String productId=jsonObject.getString("productId");;
                       String eventId=jsonObject.getString("eventId");;
                       String describe=jsonObject.getString("describe");;
                       String productImgUrl=jsonObject.getString("productImgUrl");;
                       String brand=jsonObject.getString("brand");;
                       String price=jsonObject.getString("price");;
                       String marketPrice=jsonObject.getString("marketPrice");;
                       String productName=jsonObject.getString("productName");;

                       data.add(new StarRecommendBean(id,eventCode,productId,eventId,describe,productImgUrl,brand,price,marketPrice,productName));
                   }
                   Log.i("fs",data.size()+";;;");
                   StarAdapter adapter=new StarAdapter(StarRecommendActivity.this,data);

                   listView.setAdapter(adapter);
                   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           Intent intent = new Intent(StarRecommendActivity.this, ThirdDetailsActivity.class);
                           String thirdAddress = Config.TODAY_THIRD_CONTENT + data.get(position).getProductId();
                           String Hot_recommendation = Config.Hot_recommendation + data.get(position).getProductId() + "&categoryId=" + data.get(position).getDescribe();
                           Log.i("thirdAddress",thirdAddress);

                           intent.putExtra("Hot_recommendation", Hot_recommendation);
                           intent.putExtra("thirdAddress", thirdAddress);
                           intent.putExtra("price", data.get(position).getProductName());
                           intent.putExtra("marketPrice", data.get(position).getMarketPrice());
                           intent.putExtra("name", data.get(position).getEventId());
                           intent.putExtra("productName", data.get(position).getPrice());
                           //intent.putExtra("discount", data.get(position).getDiscount());
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
    };
}

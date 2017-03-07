package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.TodaySecondDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondDetailsActivity extends AppCompatActivity {

    private ImageView price;
    private TextView  title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second_details);

        Intent intent=getIntent();
        String web=intent.getStringExtra("web");
        String englishName=intent.getStringExtra("englishName");
        title= (TextView) findViewById(R.id.title_title_bar_products);

        title.setText(englishName);

        volleyGet(web);
        //Log.i("web",web);

    }
    public   void onClick(View  view){

        switch (view.getId()){

            case R.id.back_title_bar_products:

                finish();

                break;
            case R.id.Deatails_imageview:

                Intent intent=new Intent(getApplicationContext(), BagActivity.class);

                startActivity(intent);
                break;
        }
    }

    public   void volleyGet(String url){
        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("object",response.toString());
                final List<TodaySecondDetails> data = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("products");
                    for (int i=0;i<lists.length();i++){

                        JSONObject jsonObject = lists.getJSONObject(i);
                        // String  categoryId=jsonObject.getString(" categoryId");
                        String productId = jsonObject.getString("productId");
                        String productName = jsonObject.getString("productName");
                        String product_type = jsonObject.getString("product_type");
                        String  brandName=jsonObject.getString("brandName");
                        String  price=jsonObject.getString("price");
                        String  marketPrice=jsonObject.getString("marketPrice");
                        String  imageUrl=jsonObject.getString("imageUrl");
                        String  discount=jsonObject.getString("discount");
                        //推荐
                        String isRecommend=jsonObject.getString("isRecommend");
                        //剩余数量
                        String  saleableQty=jsonObject.getString("saleableQty");
                        Log.i("imageUrl",imageUrl);
                        data.add(new TodaySecondDetails(productId,productName,product_type,brandName,price,marketPrice,imageUrl,discount));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("网络请求失败");
            }
        });
        MyApp.getHttpQueue().add(objectRequest);
    }

}

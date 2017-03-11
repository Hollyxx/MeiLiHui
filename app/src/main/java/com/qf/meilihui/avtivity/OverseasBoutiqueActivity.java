package com.qf.meilihui.avtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.OverSeaBoutiqueAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.OverSeaProductList;
import com.qf.meilihui.bean.OverSeaEvent;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OverseasBoutiqueActivity extends AppCompatActivity {
    private List<OverSeaEvent>  dataEvent;
    private List<OverSeaProductList>   dataProduct;
    private ListView  listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_overseas_boutique);

        listview= (ListView) findViewById(R.id.star_listview);
        volleyGet(Config.OverSea);
    }
    public   void  volleyGet(String url){

    final  JsonObjectRequest  jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("overseaEventList");

                    dataEvent=new ArrayList<>();
                    dataProduct=new ArrayList<>();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject event = jsonObject.getJSONObject("event");
                        String id=event.getString("id");
                        String code=event.getString("code");
                        String name=event.getString("name");
                        String englishName=event.getString("englishName");
                        String imgUrl=event.getString("imgUrl");
                        String discount=event.getString("discount");

                        JSONArray productList = jsonObject.getJSONArray("productList");
                        for (int j=0;j<productList.length();j++){
                            JSONObject jsonObject2 = productList.getJSONObject(j);
                            String id2=jsonObject2.getString("id");
                            String  eventId=jsonObject2.getString("eventId");
                            String productName=jsonObject2.getString("productName");
                            String productImgUrl=jsonObject2.getString("productImgUrl");
                            String brand=jsonObject2.getString("brand");
                            String price=jsonObject2.getString("price") ;
                            String marketPrice=jsonObject2.getString("marketPrice");
                            dataProduct.add(new OverSeaProductList(id2,eventId,productName,productImgUrl,brand,price,marketPrice));
                        }
                        dataEvent.add(new OverSeaEvent(id,code,name,englishName,imgUrl,discount,dataProduct));
                    }
                    OverSeaBoutiqueAdapter  adapter=new OverSeaBoutiqueAdapter(OverseasBoutiqueActivity.this,dataEvent);
                    listview.setAdapter(adapter);
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
}

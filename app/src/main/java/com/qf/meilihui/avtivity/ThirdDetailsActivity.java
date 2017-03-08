package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.BasePagerAdapter;
import com.qf.meilihui.adapter.ListInfoAdapter;
import com.qf.meilihui.adapter.PictureListViewAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.InfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThirdDetailsActivity extends AppCompatActivity {

    private List<ImageView>  viewpageimages;
    private ViewPager  viewpager;
    private String thirdAddress;
    private  List<InfoBean>  info;
    private ImageView  thirddetails_Prompt,thirddetails_component_iv;
    private List<String> images,pictures;
    private ListView thirddetails_picture,thirddetails_info;
    private TextView title_brandname,title_price,title_marketPrice,thirddetails_brandname,thirddetails_name,thirddetails_price,thirddetails_marketPrice,thirddetails_discount;
    private TextView Discount_details,Discount_picture,thirddetails_describe,thirddetails_maintain,thirddetails_component_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_third_details);
        init();

        volleyGet(thirdAddress);

    }
    public   void init(){
        Intent  intent=getIntent();
        String name =intent.getStringExtra("name");
        String price=intent.getStringExtra("price");
        thirdAddress=intent.getStringExtra("thirdAddress");
        String marketPrice=intent.getStringExtra("marketPrice");
        String productName=intent.getStringExtra("productName");
        String discount=intent.getStringExtra("discount");

        title_brandname= (TextView) findViewById(R.id.title_brandname);
        title_price= (TextView) findViewById(R.id.title_price);
        title_marketPrice= (TextView) findViewById(R.id.title_marketPrice);
        thirddetails_brandname= (TextView) findViewById(R.id.thirddetails_brandname);
        thirddetails_discount= (TextView) findViewById(R.id.thirddetails_discount);
        thirddetails_marketPrice= (TextView) findViewById(R.id.thirddetails_marketPrice);
        thirddetails_price= (TextView) findViewById(R.id.thirddetails_price);
        thirddetails_name= (TextView) findViewById(R.id.thirddetails_name);
        Discount_details= (TextView) findViewById(R.id.Discount_details);
        Discount_details= (TextView) findViewById(R.id.Discount_details);
        thirddetails_describe= (TextView) findViewById(R.id.thirddetails_describe);
        thirddetails_picture= (ListView) findViewById(R.id.thirddetails_picture);
        thirddetails_Prompt= (ImageView) findViewById(R.id.thirddetails_Prompt);
        thirddetails_component_iv= (ImageView) findViewById(R.id.thirddetails_component_iv);
        thirddetails_maintain= (TextView) findViewById(R.id.thirddetails_maintain);
        thirddetails_info= (ListView) findViewById(R.id.thirddetails_info);
        thirddetails_component_tv= (TextView) findViewById(R.id.thirddetails_component_tv);
        viewpager= (ViewPager) findViewById(R.id.third_details_viewpage);

        title_brandname.setText(name);
        title_marketPrice.setText("￥"+marketPrice);
        title_price.setText("￥"+price);
        thirddetails_brandname.setText(name);
        thirddetails_discount.setText(discount);
        thirddetails_marketPrice.setText("￥"+marketPrice);
        thirddetails_price.setText("￥"+price);
        thirddetails_name.setText(productName);

    }
    public   void volleyGet(String url){
        final JsonObjectRequest  jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject infos = response.getJSONObject("infos");

                    JSONObject description = infos.getJSONObject("description");
                    String design = description.getString("design");
                    thirddetails_describe.setText(design);

                     //产品图片
                    images=new ArrayList<>();
                    JSONArray product_array = description.getJSONArray("product_img1");
                    for (int i=0;i<product_array.length();i++){
                        JSONObject jsonObject = product_array.getJSONObject(i);
                        String picture_url= jsonObject.getString("bigImgUrl");

                        images.add(picture_url);
                    }
                    String priceImageUrl = infos.getString("priceImageUrl");
                    //Log.i("priceImageUrl",priceImageUrl);
                    Glide.with(getApplicationContext()).load(priceImageUrl).into(thirddetails_Prompt);

                    //材质
                    String material_quality_img = description.getString("material_quality_img");
                    if(material_quality_img.isEmpty()==true){
                        thirddetails_component_iv.setVisibility(View.GONE);
                    }else{
                        Glide.with(getApplicationContext()).load(material_quality_img).into(thirddetails_component_iv);
                    }
                    //保养须知
                    String maintenanceList=description.getString("maintenanceList");
                    String substring = maintenanceList.substring(2, maintenanceList.length() - 2);
                    thirddetails_maintain.setText(substring);
                    //详细信息加载
                    JSONArray attributesList = description.getJSONArray("attributesList");
                      info=new ArrayList<>();
                    for (int i=0;i<attributesList.length()-1;i++){
                         JSONObject jsonObject = attributesList.getJSONObject(i);
                         String name=jsonObject.getString("name");
                         String value=jsonObject.getString("value");
                         info.add(new InfoBean(name,value));
                    }
                    //材质信息
                    JSONObject jsonObject = attributesList.getJSONObject(attributesList.length()-1);
                    String component=jsonObject.getString("value");
                    thirddetails_component_tv.setText(component);
                    //viewpage的图片获得
                    JSONArray images = infos.getJSONArray("images");
                    viewpageimages=new ArrayList<>();
                    pictures=new ArrayList<>();

                    for (int i=0;i<images.length();i++){
                        JSONObject jsonObject1 = images.getJSONObject(i);
                        String smallImgUrl=jsonObject1.getString("smallImgUrl");
                        String bigImgUrl=jsonObject1.getString("bigImgUrl");
                        ImageView  iv=new ImageView(getApplicationContext());
                        //iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        Glide.with(getApplicationContext()).load(smallImgUrl).into(iv);
                        pictures.add(bigImgUrl);

                        iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent  intent=new Intent(getApplicationContext(),ViewPagerActivity.class);

                                //intent.putStringArrayListExtra("picture",pictures);
                                startActivity(intent);
                            }
                        });
                        viewpageimages.add(iv);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PictureListViewAdapter  adapter=new PictureListViewAdapter(getApplicationContext(),images);
                thirddetails_picture.setAdapter(adapter);

                ListInfoAdapter adapter2=new ListInfoAdapter(getApplicationContext(),info);
                thirddetails_info.setAdapter(adapter2);

                BasePagerAdapter  adapter3=new BasePagerAdapter(viewpageimages);
                viewpager.setAdapter(adapter3);

                //scrollview嵌套listview  listview需重新得到自己所需的控件高度
                setListViewHeightBasedOnChildren(thirddetails_picture);
                setListViewHeightBasedOnChildren(thirddetails_info);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApp.getHttpQueue().add(jsonObjectRequest);

   }
    public  void onClick(View  view){
        switch (view.getId()){
            case R.id.thirddetails_ensure:
                Intent  intent=new Intent(getApplicationContext(),EnsureActivity.class);
                startActivity(intent);
                break;
            case R.id.third_details_ruler:
                Intent  intent1=new Intent(getApplicationContext(),RulerActivity.class);

                startActivity(intent1);
                break;
            case R.id.title_back:
                finish();
                break;


        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}

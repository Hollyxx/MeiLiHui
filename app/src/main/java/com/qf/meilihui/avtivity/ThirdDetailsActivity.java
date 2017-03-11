package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.BasePagerAdapter;
import com.qf.meilihui.adapter.ListInfoAdapter;
import com.qf.meilihui.adapter.PictureListViewAdapter;
import com.qf.meilihui.adapter.RecycleAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.HotBean;
import com.qf.meilihui.bean.InfoBean;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThirdDetailsActivity extends AppCompatActivity {

    private List<HotBean> data;
    private String logId,name;
    private String hotadress;
    private RecyclerView mRecycleView;
    private TextView type;
    private List<ImageView> viewpageimages;
    private RadioGroup rg;
    private ViewPager viewpager;
    private String thirdAddress;
    private List<InfoBean> info;
    private ImageView thirddetails_Prompt, thirddetails_component_iv;
    private List<String> Images, pictures;
    private ListView thirddetails_picture, thirddetails_info;
    private TextView title_brandname, title_price, title_marketPrice, thirddetails_brandname, thirddetails_name, thirddetails_price, thirddetails_marketPrice, thirddetails_discount;
    private TextView Discount_details, Discount_picture, thirddetails_describe, thirddetails_maintain, thirddetails_component_tv;

    private TextView brandname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_third_details);
        init();

        volleyGet(thirdAddress);
        volleyHot(hotadress);

    }

    public void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        thirdAddress = intent.getStringExtra("thirdAddress");
        String marketPrice = intent.getStringExtra("marketPrice");
        String productName = intent.getStringExtra("productName");
//        String discount = intent.getStringExtra("discount");
        hotadress = intent.getStringExtra("Hot_recommendation");
        brandname= (TextView) findViewById(R.id.brandname);
        brandname.setText(name);

        title_brandname = (TextView) findViewById(R.id.title_brandname);
        title_price = (TextView) findViewById(R.id.title_price);
        title_marketPrice = (TextView) findViewById(R.id.title_marketPrice);
        thirddetails_brandname = (TextView) findViewById(R.id.thirddetails_brandname);
        thirddetails_discount = (TextView) findViewById(R.id.thirddetails_discount);
        thirddetails_marketPrice = (TextView) findViewById(R.id.thirddetails_marketPrice);
        thirddetails_price = (TextView) findViewById(R.id.thirddetails_price);
        thirddetails_name = (TextView) findViewById(R.id.thirddetails_name);
        Discount_details = (TextView) findViewById(R.id.Discount_details);
        Discount_details = (TextView) findViewById(R.id.Discount_details);
        thirddetails_describe = (TextView) findViewById(R.id.thirddetails_describe);
        thirddetails_picture = (ListView) findViewById(R.id.thirddetails_picture);
        thirddetails_Prompt = (ImageView) findViewById(R.id.thirddetails_Prompt);
        thirddetails_component_iv = (ImageView) findViewById(R.id.thirddetails_component_iv);
        thirddetails_maintain = (TextView) findViewById(R.id.thirddetails_maintain);
        thirddetails_info = (ListView) findViewById(R.id.thirddetails_info);
        thirddetails_info.setFocusable(false);
        thirddetails_picture.setFocusable(false);
        thirddetails_component_tv = (TextView) findViewById(R.id.thirddetails_component_tv);
        viewpager = (ViewPager) findViewById(R.id.third_details_viewpage);
        rg = (RadioGroup) findViewById(R.id.viewPager_rg);
        type = (TextView) findViewById(R.id.type);
        mRecycleView = (RecyclerView) findViewById(R.id.my_Recommend);

        title_brandname.setText(name);
        title_marketPrice.setText("￥" + marketPrice);
        title_price.setText("￥" + price);
        thirddetails_brandname.setText(name);
        //thirddetails_discount.setText(discount + "折");
        thirddetails_marketPrice.setText("￥" + marketPrice);
        thirddetails_price.setText("￥" + price);
        thirddetails_name.setText(productName);

    }

    public void volleyHot(String url) {
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String name = response.getString("secondCategoryName");
                    Log.i("name", name);
                    type.setText(name);

                    JSONArray categoryList = response.getJSONArray("categoryList");

                    data = new ArrayList<>();
                    for (int i = 0; i < categoryList.length(); i++) {
                        JSONObject jsonObject = categoryList.getJSONObject(i);
                        String productId = jsonObject.getString("productId");
                        String imgUrl = jsonObject.getString("imgUrl");
                        String product_name = jsonObject.getString("product_name");
                        String brand_name = jsonObject.getString("brand_name");
                        String market_price = jsonObject.getString("market_price");

                        String price = jsonObject.getString("price");
                        String eventId = jsonObject.getString("eventId");

                        data.add(new HotBean(productId, imgUrl, product_name, brand_name, market_price, price, eventId));

                    }

                    RecycleAdapter adapter = new RecycleAdapter(getApplicationContext(), data);

                    mRecycleView.setAdapter(adapter);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);


                    //设置布局管理器
                    mRecycleView.setLayoutManager(manager);

                    //设置增删数据的动画
                    mRecycleView.setItemAnimator(new DefaultItemAnimator());

                    adapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            Intent  intent=new Intent(getApplication(),ThirdDetailsActivity.class);
                            String  thirdAddress= Config.TODAY_THIRD_CONTENT+data.get(position).getProductId();
                            String  Hot_recommendation= Config.Hot_recommendation+data.get(position).getProductId()+"&categoryId="+data.get(position).getEventId();

                            intent.putExtra("Hot_recommendation",Hot_recommendation);
                            intent.putExtra("thirdAddress",thirdAddress);
                            intent.putExtra("price",data.get(position).getPrice());
                            intent.putExtra("marketPrice",data.get(position).getMarket_price());
                            intent.putExtra("name",data.get(position).getBrand_name());
                            intent.putExtra("productName",data.get(position).getProduct_name());
                            //intent.putExtra("discount",name);
                            startActivity(intent);

                        }
                    });

                    StaggeredGridLayoutManager sManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);

                    mRecycleView.setLayoutManager(sManager);

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

    public void volleyGet(String url) {
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject infos = response.getJSONObject("infos");

                    String discount=infos.getString("discount");
                    thirddetails_discount.setText(discount);

                    logId=infos.getString("brandLogoId");
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
                        viewpageimages.add(iv);

                        RadioButton radioButton = new RadioButton(getApplicationContext());
                        radioButton.setButtonDrawable(R.drawable.radiobutton_selector);
                        radioButton.setLayoutParams(new RadioGroup.LayoutParams(20, 20));
                        radioButton.setPadding(20, 10, 20, 10);//上  右  下  左
                        radioButton.setClickable(false);
                        rg.addView(radioButton);
                    }

                    JSONObject description = infos.getJSONObject("description");
                    if(infos.isNull("description")==false){
                        String design = description.getString("design");
                        thirddetails_describe.setText(design);

                        //产品图片
                        Images = new ArrayList<>();
                        JSONArray product_array = description.getJSONArray("product_img1");
                        for (int i = 0; i < product_array.length(); i++) {
                            JSONObject jsonObject = product_array.getJSONObject(i);
                            String picture_url = jsonObject.getString("bigImgUrl");

                            Images.add(picture_url);
                        }
                        PictureListViewAdapter adapter = new PictureListViewAdapter(getApplicationContext(), Images);
                        thirddetails_picture.setAdapter(adapter);

                        //材质
                        String material_quality_img = description.getString("material_quality_img");
                        if (material_quality_img.isEmpty() == true) {
                            thirddetails_component_iv.setVisibility(View.GONE);
                        } else {
                            Glide.with(getApplicationContext()).load(material_quality_img).into(thirddetails_component_iv);
                        }

                        //保养须知
                        String maintenanceList=description.getString("maintenanceList");
                        if(maintenanceList.length()>0){
//                        String substring = maintenanceList.substring(2, maintenanceList.length() - 2);
//                        thirddetails_maintain.setText(substring);
                            thirddetails_maintain.setText(maintenanceList);

                        }else{

                            thirddetails_maintain.setVisibility(View.GONE);
                        }
                        //详细信息加载
                        JSONArray attributesList = description.getJSONArray("attributesList");
                        if(attributesList.length()>0){
                            info=new ArrayList<>();
                            for (int i=0;i<attributesList.length()-1;i++){
                                JSONObject jsonObject = attributesList.getJSONObject(i);
                                String name=jsonObject.getString("name");
                                String value=jsonObject.getString("value");
                                info.add(new InfoBean(name,value));
                            }
                            ListInfoAdapter adapter2=new ListInfoAdapter(getApplicationContext(),info);
                            thirddetails_info.setAdapter(adapter2);

                            //材质信息
                            JSONObject jsonObject = attributesList.getJSONObject(attributesList.length()-1);
                            String component=jsonObject.getString("value");
                            thirddetails_component_tv.setText(component);

                        }else{
                            thirddetails_component_tv.setVisibility(View.GONE);
                        }
                    }

                    String priceImageUrl = infos.getString("priceImageUrl");
                    Glide.with(getApplicationContext()).load(priceImageUrl).into(thirddetails_Prompt);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                PictureListViewAdapter adapter = new PictureListViewAdapter(getApplicationContext(), pictures);
                thirddetails_picture.setAdapter(adapter);

                BasePagerAdapter adapter3 = new BasePagerAdapter(viewpageimages);
                viewpager.setAdapter(adapter3);
                viewpager.setPageTransformer(true,new DepthPageTransformer());
                ((RadioButton) rg.getChildAt(0)).setChecked(true);
                viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        ((RadioButton) rg.getChildAt(position)).setChecked(true);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                viewpager.setOnTouchListener(new View.OnTouchListener() {
                    int flage = 0;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                flage = 0;
                                break;
                            case MotionEvent.ACTION_MOVE:
                                flage = 1;
                                break;
                            case MotionEvent.ACTION_UP:
                                if (flage == 0) {
                                    int item = viewpager.getCurrentItem();
                                    Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
                                    intent.putStringArrayListExtra("picture", (ArrayList<String>) pictures);
                                    intent.putExtra("item", item + "");
                                    startActivity(intent);
                                }
                        }
                        return false;
                    }
                });

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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.thirddetails_ensure:
                Intent intent = new Intent(getApplicationContext(), EnsureActivity.class);
                startActivity(intent);
                break;
            case R.id.third_details_ruler:
                Intent intent1 = new Intent(getApplicationContext(), RulerActivity.class);

                startActivity(intent1);
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.thirddetails_in:
                Intent  intent2=new Intent(this,BrandDetailActivity.class);

                intent2.putExtra("logoId",logId);
                intent2.putExtra("name",name);
                startActivity(intent2);
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
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}

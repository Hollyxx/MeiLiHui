package com.qf.meilihui.basefragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.qf.meilihui.adapter.HomeBaseAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.HeadViewContent;
import com.qf.meilihui.bean.HomeContent;
import com.qf.meilihui.uri.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBeautyFragment extends Fragment {

    private HomeBaseAdapter adapter;
    private  TextView  tv1,tv2,tv3,tv4;
    private ListView  listView;
    private View headView,scrollview;
    private RadioGroup rg;
    private ViewPager viewPager;
    private boolean isStop = false;
    private int position = 0;
    private LinearLayout layout;
    private Handler handler = new Handler();
    private List<ImageView> imagesTop;
    public HomeBeautyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_beauty, container, false);

        listView= (ListView) view.findViewById(R.id.todsy_beauty_listview);

        volleyGet(Config.TODAY_BEAUTIFUL,Config.TODAY_BEAU_HEADVIEW,Config.TODAY_BEAU_SCROLLVIEW);
        return view;
    }
    public  void volleyGet(String url, String path,String brand ){

        scrollview=LayoutInflater.from(getContext()).inflate(R.layout.item_scroll,null,false);
        headView=LayoutInflater.from(getContext()).inflate(R.layout.activity_beantifu_head,null,false);
        layout= (LinearLayout) headView.findViewById(R.id.scroll_layout);
        rg = (RadioGroup) headView.findViewById(R.id.rgb);
        imagesTop = new ArrayList<>();
        viewPager= (ViewPager) headView.findViewById(R.id.viewpager_bea);
        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                List<HomeContent> data = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("eventList");
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        String englishName = jsonObject.getString("englishName");
                        String chineseName = jsonObject.getString("chineseName");
                        String discountText = jsonObject.getString("discountText");
                        String  imageUrl=jsonObject.getString("imageUrl");
                        Log.i("beau",jsonObject.toString()+1);
                        Log.i("furn",englishName+i);
                        Log.i("furn",chineseName+i);
                        Log.i("furn",discountText+i);
                        data.add(new HomeContent(englishName,imageUrl,chineseName,discountText));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new HomeBaseAdapter(getContext(),data);
                listView.setAdapter(adapter);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("网络请求失败");
            }
        });

        final JsonObjectRequest brandRequest=new JsonObjectRequest(brand,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("brandName",response.toString());
                try {
                    JSONArray lists = response.getJSONArray("products");
                    Log.i("brandName",lists.toString());
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        String eventId = jsonObject.getString("eventId");
                        String productId = jsonObject.getString("productId");
                        String brandName = jsonObject.getString("brandName");
                        String productName=jsonObject.getString("productName");
                        String price=jsonObject.getString("headRequest");
                        String picUrl=jsonObject.getString("picUrl");
                        String glsCode=jsonObject.getString("glsCode");
                        String marketPrice=jsonObject.getString("marketPrice");
                        ImageView imageView= (ImageView) scrollview.findViewById(R.id.scroll_iv);
                        tv1= (TextView) scrollview.findViewById(R.id.scroll_tv1);
                        tv2= (TextView) scrollview.findViewById(R.id.scroll_tv2);
                        tv3= (TextView) scrollview.findViewById(R.id.scroll_tv3);
                        tv4= (TextView) scrollview.findViewById(R.id.scroll_tv4);
                        tv1.setText(brandName);
                        tv2.setText(productName);
                        tv3.setText("￥"+price);
                        tv4.setText("￥"+marketPrice);
                        Glide.with(getActivity()).load(picUrl).into(imageView);
                        Log.i("brandName",brandName);
                        layout.addView(scrollview);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("网络请求失败");
            }
        });

        final JsonObjectRequest headRequest=new JsonObjectRequest(path,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("object",response.toString());
                List<HeadViewContent> data = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("banners");
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        String imgUrl = jsonObject.getString("imgUrl");
                        String shareUrl = jsonObject.getString("shareUrl");
                        String imgAndroid = jsonObject.getString("imgAndroid");
                        String  shareContent=jsonObject.getString("shareContent");

                        Log.i("imgAndroid",imgAndroid+i);

                        data.add(new HeadViewContent(imgUrl,shareUrl,shareContent,imgAndroid));
                        if(imgUrl.isEmpty()==false){
                            ImageView imageView = new ImageView(getActivity());
                            Glide.with(getActivity()).load(data.get(i).getImgUrl()).into(imageView);
                            imageView.setImageResource(R.mipmap.bk_mybrand_default);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imagesTop.add(imageView);
                        }
                    }
                    //Log.i("imagesTop",imagesTop.size()+"");
                    BasePagerAdapter adapter=new BasePagerAdapter(imagesTop);
                    viewPager.setAdapter(adapter);
                    listView.addHeaderView(headView);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("网络请求失败");
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (!isStop) {
                    if (position >= imagesTop.size()) {
                        position = 0;
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (position == 0) {
                                viewPager.setCurrentItem(position++, false);
                            } else {
                                viewPager.setCurrentItem(position++, true);
                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        //3, 将请求对象添加到请求队列中
        MyApp.getHttpQueue().add(objectRequest);
        MyApp.getHttpQueue().add(headRequest);
        MyApp.getHttpQueue().add(brandRequest);
    }

}

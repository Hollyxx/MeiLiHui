package com.qf.meilihui.basefragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.qf.meilihui.avtivity.HeadViewDetailsActivity;
import com.qf.meilihui.avtivity.SecondDetailsActivity;
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
public class HomeTodayFragment extends Fragment {
    private List<ImageView> imagesTop;
    private ListView  listView;
    private TextView  tv;
    private View headView;
    private  RadioGroup  rg;
    private ViewPager  viewPager;
    private boolean isStop = false;
    private int position = 0;
    private Handler handler = new Handler();
    //private List<HomeContent>  data;
    public HomeTodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_today, container, false);

        listView= (ListView) view.findViewById(R.id.home_today_listview);

        tv=(TextView) view.findViewById(R.id.my_tv);

        listView.setEmptyView(tv);
        volleyGet(Config.TODAY_CONTENT,Config.TODAY_HEADVIEW);
        return  view;

    }
    private void volleyGet(String url,String path)
    {
        headView=LayoutInflater.from(getContext()).inflate(R.layout.activity_head,null,false);
        rg = (RadioGroup) headView.findViewById(R.id.rg);
        imagesTop = new ArrayList<>();
        viewPager= (ViewPager) headView.findViewById(R.id.viewpager);
        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

               final List<HomeContent> data = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("lists");
                   for (int i=0;i<lists.length();i++){

                       JSONObject jsonObject = lists.getJSONObject(i);
                      // String  categoryId=jsonObject.getString(" categoryId");
                       String englishName = jsonObject.getString("englishName");
                       String chineseName = jsonObject.getString("chineseName");
                       String discountText = jsonObject.getString("discountText");
                       String  imageUrl=jsonObject.getString("imageUrl");
                       String  categoryId=jsonObject.getString("categoryId");

                       data.add(new HomeContent(englishName,imageUrl,chineseName,discountText,categoryId));
                   }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HomeBaseAdapter adapter = new HomeBaseAdapter(getContext(),data);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent  intent=new Intent(getContext(),SecondDetailsActivity.class);

                        String secondUrl=Config.TODAY_SECOND_CONTENT+data.get(position-1).getEventId()+"&pageIndex=1";
                        intent.putExtra("web",secondUrl);
                        Log.i("secondUrl",secondUrl);
                        intent.putExtra("id",data.get(position-1).getEventId());
                        //Log.i("id",data.get(position-1).getEventId());
                        intent.putExtra("englishName", data.get(position-1).getDiscountText());
                        //Log.i("englishName",data.get(position-1).getEventId());
                        startActivity(intent);
                    }
                });
//
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("网络请求失败");
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
                        final String shareUrl = jsonObject.getString("shareUrl");
                        String imgAndroid = jsonObject.getString("imgAndroid");
                        final String  shareContent=jsonObject.getString("shareContent");
                        //Log.i("web",shareUrl);

                        data.add(new HeadViewContent(imgUrl,shareUrl,shareContent,imgAndroid));
                    if(imgUrl.isEmpty()==false){
                        ImageView imageView = new ImageView(getActivity());
                        Glide.with(getActivity()).load(data.get(i).getImgUrl()).into(imageView);
                        imageView.setImageResource(R.mipmap.bk_mybrand_default);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(shareUrl.isEmpty()==false){

                                    Intent intent=new Intent(getActivity(), HeadViewDetailsActivity.class);
                                    intent.putExtra("path",shareUrl);
                                    intent.putExtra("title",shareContent);
                                    startActivity(intent);

                                }
                            }
                        });
                        imagesTop.add(imageView);
                        RadioButton radioButton = new RadioButton(getActivity());
                        radioButton.setButtonDrawable(R.drawable.radiobutton_selector);
                        radioButton.setLayoutParams(new RadioGroup.LayoutParams(20, 20));
                        radioButton.setPadding(20, 0, 20, 5);
                        radioButton.setClickable(false);
                        rg.addView(radioButton);
                   }
                    }
                    Log.i("imagesTop",imagesTop.size()+"");
                    BasePagerAdapter  adapter=new BasePagerAdapter(imagesTop);
                    viewPager.setAdapter(adapter);
                    listView.addHeaderView(headView);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("网络请求失败");
            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        //3, 将请求对象添加到请求队列中
        MyApp.getHttpQueue().add(objectRequest);
        MyApp.getHttpQueue().add(headRequest);
    }

}

package com.qf.meilihui.basefragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
import com.qf.meilihui.avtivity.AllBrandActivity;
import com.qf.meilihui.avtivity.BeautifulBrandsActivity;
import com.qf.meilihui.avtivity.OverseasBoutiqueActivity;
import com.qf.meilihui.avtivity.SecondDetailsActivity;
import com.qf.meilihui.avtivity.StarRecommendActivity;
import com.qf.meilihui.avtivity.ThirdDetailsActivity;
import com.qf.meilihui.bean.BrandWall;
import com.qf.meilihui.bean.HeadViewContent;
import com.qf.meilihui.bean.HomeContent;
import com.qf.meilihui.bean.ScrollBean;
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

    private List<HomeContent> dataAll;
    private HomeBaseAdapter adapter;
    private List<BrandWall> brands;
    private ListView  listView;
    private View headView,scrollview;
    private RadioGroup rg;
    private ViewPager viewPager;
    private boolean isStop = false;
    private int position = 0;
    private  List<String>  num;
    private Handler handler = new Handler();
    private List<ImageView> imagesTop;
    private List<ScrollBean> data;
    private GridView horizontalGridView,gridView;
    private  MyHorizontalListAdapter mAdapter;
    public HomeBeautyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_beauty, container, false);

        listView= (ListView) view.findViewById(R.id.todsy_beauty_listview);
        init();
        volleyGet(Config.TODAY_BEAUTIFUL,Config.TODAY_BEAU_HEADVIEW,Config.TODAY_BEAU_SCROLLVIEW);
        volleyGet(Config.TODAY_BEAU_IM,Config.TODAY_BEAU_GRIDVIEW);
        return view;
    }
    public  void init (){
        scrollview=LayoutInflater.from(getContext()).inflate(R.layout.item_scroll,null,false);
        headView=LayoutInflater.from(getContext()).inflate(R.layout.activity_beantifu_head,null,false);
        horizontalGridView= (GridView) headView.findViewById(R.id.horizontal_gridview);
        gridView= (GridView) headView.findViewById(R.id.brand_gridview);
        rg = (RadioGroup) headView.findViewById(R.id.rgb);
        viewPager= (ViewPager) headView.findViewById(R.id.viewpager_bea);
    }
    public  void volleyGet(String url,String url2){
          final  JsonObjectRequest  objectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                  try {
                      String overseaFileUrl=response.getString("overseaFileUrl");
                      final String starFileUrl=response.getString("starFileUrl");
                      ImageView iv1= (ImageView) headView.findViewById(R.id.iv1);
                      ImageView iv2= (ImageView) headView.findViewById(R.id.iv2);
                      Glide.with(getActivity()).load(overseaFileUrl).into(iv2);
                      Glide.with(getActivity()).load(starFileUrl).into(iv1);
                      iv1.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent  intent=new Intent(getContext(), StarRecommendActivity.class);

                              startActivity(intent);
                          }
                      });
                      iv2.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent  intent=new Intent(getContext(), OverseasBoutiqueActivity.class);

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
        final JsonObjectRequest brandRequest=new JsonObjectRequest(url2,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                num=new ArrayList<>();
                brands = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("brandWall");
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        String  id=jsonObject.getString("id");
                        String brandName = jsonObject.getString("brandName");
                        String brandLogoUrl = jsonObject.getString("brandLogoUrl");
                        String eventId = jsonObject.getString("eventId");

                        num.add(brandLogoUrl);
                       // Log.i("xiao",brandLogoUrl+"......");
                        brands.add(new BrandWall(id,brandName,brandLogoUrl,eventId));
                    }

                    MyGridAdapter adapter=new MyGridAdapter(getContext(),brands);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position<5){
                                Intent  intent=new Intent(getContext(), BeautifulBrandsActivity.class);
                                String path=Config.Beautiful_Brands+data.get(position).getEventId();
                                intent.putExtra("path",path);
                                intent.putExtra("name",data.get(position).getBrandName());
                                startActivity(intent);
                            }else  if(position==5){
                                Intent  intent=new Intent(getContext(), AllBrandActivity.class);
                                intent.putExtra("siloId","2013000100000000003");
                                intent.putExtra("name","美妆");
                                startActivity(intent);
                            }
                        }
                    });

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

        MyApp.getHttpQueue().add(objectRequest);
        MyApp.getHttpQueue().add(brandRequest);
    }
    public  void volleyGet(String url, String path,String brand ){

        imagesTop = new ArrayList<>();
        final JsonObjectRequest objectRequest=new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                 dataAll = new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("eventList");
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);
                        String englishName = jsonObject.getString("englishName");
                        String chineseName = jsonObject.getString("chineseName");
                        String discountText = jsonObject.getString("discountText");
                        String  imageUrl=jsonObject.getString("imageUrl");
                        String  categoryId=jsonObject.getString("categoryId");

                        dataAll.add(new HomeContent(englishName,imageUrl,chineseName,discountText,categoryId));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new HomeBaseAdapter(getContext(),dataAll);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getContext(),SecondDetailsActivity.class);

                        String secondUrl=Config.TODAY_SECOND_CONTENT+dataAll.get(position).getEventId()+"&pageIndex=";
                        intent.putExtra("web",secondUrl);
                        intent.putExtra("id",dataAll.get(position).getEventId());
                        //Log.i("id",data.get(position-1).getEventId());
                        intent.putExtra("englishName", dataAll.get(position).getDiscountText());
                        //Log.i("englishName",data.get(position-1).getEventId());
                        startActivity(intent);
                    }
                });
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
                data=new ArrayList<>();
                try {
                    JSONArray lists = response.getJSONArray("products");
                    Log.i("brandName",lists.toString());
                    for (int i=0;i<lists.length();i++){
                        JSONObject jsonObject = lists.getJSONObject(i);

                        String eventId = jsonObject.getString("eventId");
                        String productId = jsonObject.getString("productId");
                        String brandName = jsonObject.getString("brandName");
                        String productName=jsonObject.getString("productName");
                        String price=jsonObject.getString("price");
                        String picUrl=jsonObject.getString("picUrl");
                        String glsCode=jsonObject.getString("glsCode");
                        String marketPrice=jsonObject.getString("marketPrice");
                        data.add(new ScrollBean(eventId,productId,brandName,productName,marketPrice,price,picUrl,glsCode));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int size = data.size();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((280*6), LinearLayout.LayoutParams.FILL_PARENT);
                horizontalGridView.setLayoutParams(params); //设置GirdView布局参数,横向布局的关键
                horizontalGridView.setColumnWidth(280);
                horizontalGridView.setHorizontalSpacing(15);
                horizontalGridView.setStretchMode(GridView.NO_STRETCH);
                horizontalGridView.setNumColumns(size);
                horizontalGridView.setBackgroundColor(Color.WHITE);

                mAdapter = new MyHorizontalListAdapter();
                horizontalGridView.setAdapter(mAdapter);
                horizontalGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent  intent=new Intent(getContext(), ThirdDetailsActivity.class);

                        String thirdAddress = Config.TODAY_THIRD_CONTENT + data.get(position).getProductId();
                        String Hot_recommendation = Config.Hot_recommendation + data.get(position).getProductId() + "&categoryId=" + data.get(position).getEventId();
                        Log.i("thirdAddress",thirdAddress);

                        intent.putExtra("Hot_recommendation", Hot_recommendation);
                        intent.putExtra("thirdAddress", thirdAddress);
                        intent.putExtra("price", data.get(position).getPrice());
                        intent.putExtra("marketPrice", data.get(position).getMarketPrice());
                        intent.putExtra("name", data.get(position).getBrandName());
                        intent.putExtra("productName", data.get(position).getProductName());

                        startActivity(intent);

                    }
                } );
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

                        data.add(new HeadViewContent(imgUrl,shareUrl,shareContent,imgAndroid));
                        if(imgUrl.isEmpty()==false){
                            ImageView imageView = new ImageView(getActivity());
                            Glide.with(getActivity()).load(data.get(i).getImgUrl()).into(imageView);
                            imageView.setImageResource(R.mipmap.bk_mybrand_default);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imagesTop.add(imageView);

                            RadioButton radioButton = new RadioButton(getActivity());
                            radioButton.setButtonDrawable(R.drawable.radiobutton_selector);
                            radioButton.setLayoutParams(new RadioGroup.LayoutParams(30, 30));
                            radioButton.setPadding(20, 20, 20, 20);
                            radioButton.setClickable(false);
                            rg.addView(radioButton);
                        }
                    }
                    //Log.i("imagesTop",imagesTop.size()+"");
                    BasePagerAdapter adapter=new BasePagerAdapter(imagesTop);
                    viewPager.setAdapter(adapter);
                    ((RadioButton) rg.getChildAt(0)).setChecked(true);
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

    class MyHorizontalListAdapter  extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getActivity(), R.layout.item_scroll, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.scroll_iv);
            TextView tv1= (TextView) convertView.findViewById(R.id.scroll_tv1);
            TextView tv2= (TextView) convertView.findViewById(R.id.scroll_tv2);
            TextView tv3= (TextView) convertView.findViewById(R.id.scroll_tv3);
            TextView tv4= (TextView) convertView.findViewById(R.id.scroll_tv4);
            tv1.setText(data.get(position).getBrandName());
            tv2.setText(data.get(position).getProductName());
            tv3.setText("￥" + data.get(position).getPrice());
            tv4.setText("￥" + data.get(position).getMarketPrice());
//imageView.setImageResource(R.mipmap.app_icon);
            Log.i("kkkk",data.get(position).getPicUrl());
            Glide.with(getActivity()).load(data.get(position).getPicUrl()).into(imageView);

            return convertView;

        }
    }

    class  MyGridAdapter extends  BaseAdapter{
        private Context context;
        private List<BrandWall> brands;

        public MyGridAdapter(Context context, List<BrandWall> brands) {
            this.context = context;
            this.brands = brands;
        }

        @Override
        public int getCount() {
            return brands.size()+1;
        }

        @Override
        public Object getItem(int position) {
            return brands.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_grid_item_list_brand_category,null);
            ImageView iv = (ImageView) v.findViewById(R.id.image_item_grid_list_brand_category);
           if(position==brands.size()) {
               iv.setImageResource(R.mipmap.find_more2);
               iv.setBackgroundColor(Color.WHITE);
               iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
           }else{
               Log.i("num",num.get(position)+position+"?????");
               Glide.with(context).load(brands.get(position).getBrandLogoUrl()).into(iv);
           }
            return v;
        }
    }

}

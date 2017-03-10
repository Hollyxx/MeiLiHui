package com.qf.meilihui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.meilihui.R;
import com.qf.meilihui.avtivity.CubeTransformer;
import com.qf.meilihui.basefragment.HomeBabyFragment;
import com.qf.meilihui.basefragment.HomeBeautyFragment;
import com.qf.meilihui.basefragment.HomeFurnishingFragment;
import com.qf.meilihui.basefragment.HomeMenFragment;
import com.qf.meilihui.basefragment.HomeTodayFragment;
import com.qf.meilihui.basefragment.HomeUpcomingFragment;
import com.qf.meilihui.basefragment.HomeWomenFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private HomeBabyFragment  homeBabyFragment;
    private HomeBeautyFragment  homeBeautyFragment;
    private HomeFurnishingFragment  homeFurnishingFragment;
    private HomeMenFragment  homeMenFragment;
    private HomeTodayFragment  homeTodayFragment;
    private HomeUpcomingFragment  homeUpcomingFragment;
    private HomeWomenFragment  homeWomenFragment;

    private TabLayout  tabLayout;
    private List<Fragment> data;
    private List<String> list_title;//用于存储tablayout的数据
    private ViewPager viewPager;
    private Find_tab_Adapter  find_tab_adapter;
    public static final String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_home, container, false);

        initControls(view);

        return  view;

    }
    private void initControls(View view){
        tabLayout = (TabLayout)view.findViewById(R.id.home_tab);
        viewPager = (ViewPager) view.findViewById(R.id.home_page);

        data=new ArrayList<>();
        list_title=new ArrayList<>();
        //初始化Fragment
        homeBabyFragment =new HomeBabyFragment();
        homeBeautyFragment=new HomeBeautyFragment();
        homeFurnishingFragment=new HomeFurnishingFragment();
        homeMenFragment=new HomeMenFragment();
        homeTodayFragment=new HomeTodayFragment();
        homeUpcomingFragment=new HomeUpcomingFragment();
        homeWomenFragment =new HomeWomenFragment();

        tabLayout.setTabTextColors(Color.GRAY,Color.BLACK);

        data.add(homeTodayFragment);
        data.add(homeWomenFragment);
        data.add(homeMenFragment);
        data.add(homeBeautyFragment);
        data.add(homeFurnishingFragment);
        data.add(homeBabyFragment);
        data.add(homeUpcomingFragment);


        list_title.add("今日热门");
        list_title.add("女士");
        list_title.add("男士");
        list_title.add("美妆");
        list_title.add("家居");
        list_title.add("婴童");
        list_title.add("即将上新");

        find_tab_adapter= new Find_tab_Adapter(getActivity().getSupportFragmentManager(), data,list_title);
        viewPager.setAdapter(find_tab_adapter);
         //切换特效
        viewPager.setPageTransformer(true, new CubeTransformer());
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);

        //设置小滑块的高度
        //tabLayout.setSelectedTabIndicatorHeight(5);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()!=list_title.size()) {
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
    class Find_tab_Adapter extends FragmentPagerAdapter {

        private List<Fragment> list_fragment;                         //fragment列表
        private List<String> list_Title;                              //tab名的列表


        public Find_tab_Adapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
            super(fm);
            this.list_fragment = list_fragment;
            this.list_Title = list_Title;
        }

        @Override
        public Fragment getItem(int position) {

            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            Log.e(TAG, "getCount: "+list_title.size() );
            return list_fragment.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position);
        }
    }
}

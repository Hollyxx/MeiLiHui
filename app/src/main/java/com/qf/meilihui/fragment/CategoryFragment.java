package com.qf.meilihui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.meilihui.R;
import com.qf.meilihui.adapter.CategoryPagerAdapter;
import com.qf.meilihui.basefragment.BrandofCategoryFragment;
import com.qf.meilihui.basefragment.KindofCategoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private TabLayout tab;
    private ViewPager pager;
    private Context context;
    private List<Fragment> fragments;
    private List<String> title_tab;
    private CategoryPagerAdapter adapter;
    private FragmentManager manager;
    public static final String TAG = "CategoryFragment";

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        manager = getFragmentManager();
        initData();
        initView(view);
        adapter = new CategoryPagerAdapter(manager, fragments, title_tab);


        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        return view;
    }

    private void initData() {
        title_tab = new ArrayList<>();
        title_tab.add("类别");
        title_tab.add("品牌");

        fragments = new ArrayList<>();

        KindofCategoryFragment fragment = new KindofCategoryFragment();
        fragments.add(fragment);
        BrandofCategoryFragment fragment1 = new BrandofCategoryFragment();
        fragments.add(fragment1);
    }

    private void initView(View view) {
        pager = (ViewPager) view.findViewById(R.id.pager_category);
        tab = (TabLayout) view.findViewById(R.id.tab_category);
        tab.setTabTextColors(R.color.gray,R.color.black);
    }

}

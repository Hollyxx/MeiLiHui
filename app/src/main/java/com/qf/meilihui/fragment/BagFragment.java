package com.qf.meilihui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.qf.meilihui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BagFragment extends Fragment {
    public static final String TAG = "BagFragment";
    private LinearLayout linearLayout;
    private ListView listView;
    public BagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_bag, container, false);

        listView= (ListView) view.findViewById(R.id.bag_listview);
        linearLayout= (LinearLayout) view.findViewById(R.id.bag_linearlayout);

        listView.setEmptyView(linearLayout);
        return view;
    }

}

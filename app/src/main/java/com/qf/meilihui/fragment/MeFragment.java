package com.qf.meilihui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qf.meilihui.R;
import com.qf.meilihui.avtivity.RegisterActivity;
import com.qf.meilihui.avtivity.SignActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {
    public static final String TAG = "MeFragment";

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_me, container, false);
        Button button= (Button) v.findViewById(R.id.button_login);
        Button button1= (Button) v.findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent   intent=new Intent(getContext(), SignActivity.class);

                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent   intent=new Intent(getContext(), RegisterActivity.class);

                startActivity(intent);
            }
        });

        return   v;

    }
}

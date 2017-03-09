package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.AllBrandListAdapter;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.bean.AllBrandBean;
import com.qf.meilihui.bean.Brand;
import com.qf.meilihui.uri.Config;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllBrandActivity extends AppCompatActivity {

    private ListView list;
    private List<Brand> data;
    private TextView title;
    private AllBrandListAdapter adapter;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_brand);

        list = (ListView) findViewById(R.id.list_all_brand);
        title = (TextView) findViewById(R.id.title_all_brand);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String siloId = intent.getStringExtra("siloId");
        data = new ArrayList<>();
        adapter = new AllBrandListAdapter(this, data);
        loadData(siloId);
        title.setText(name + "品牌");
        list.setAdapter(adapter);
    }

    private void loadData(String siloId) {

        StringRequest request = new StringRequest(Config.All_Brand + siloId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject object = new JSONObject(response);
                        AllBrandBean bean = new AllBrandBean();
                        bean.parseJson(object);
                        data.addAll(bean.getItems());
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MyApp.getHttpQueue().add(request);

    }


    public void onClick(View view) {
        finish();
    }

    public void searchClick(View view) {
        int id = view.getId();
        TextView text = (TextView) view;
        String name = text.getText().toString();

        switch (id) {
            case R.id.search_A:
                Search(name);
                break;
            case R.id.search_B:
                Search(name);
                break;
            case R.id.search_C:
                Search(name);
                break;
            case R.id.search_D:
                Search(name);
                break;
            case R.id.search_E:
                Search(name);
                break;
            case R.id.search_F:
                Search(name);
                break;
            case R.id.search_G:
                Search(name);
                break;
            case R.id.search_H:
                Search(name);
                break;
            case R.id.search_I:
                Search(name);
                break;
            case R.id.search_J:
                Search(name);
                break;
            case R.id.search_K:
                Search(name);
                break;
            case R.id.search_L:
                Search(name);
                break;
            case R.id.search_M:

                break;
            case R.id.search_N:
                Search(name);
                break;
            case R.id.search_O:
                Search(name);
                break;
            case R.id.search_P:
                Search(name);
                break;
            case R.id.search_Q:
                Search(name);
                break;
            case R.id.search_R:
                Search(name);
                break;
            case R.id.search_S:
                Search(name);
                break;
            case R.id.search_T:
                Search(name);
                break;
            case R.id.search_U:
                Search(name);
                break;

            case R.id.search_V:
                Search(name);
                break;

            case R.id.search_W:
                Search(name);
                break;

            case R.id.search_X:
                Search(name);
                break;

            case R.id.search_Y:
                Search(name);
                break;

            case R.id.search_Z:
                Search(name);
                break;

            case R.id.search_bottom:
                list.setSelection(data.size()-1);
                break;
        }

    }

    private void Search(String firstCap) {
        if (data.size() > 1) {
            for (int i = 0; i < data.size(); i++) {
                Brand brand = data.get(i);
                String name = brand.getBrandName();
                if (name.startsWith(firstCap)) {
                    flag = i;
                    break;
                }
            }
        }
        list.setSelection(flag);
    }
}

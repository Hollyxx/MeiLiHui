package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.qf.meilihui.R;
import com.qf.meilihui.app.MyApp;
import com.qf.meilihui.uri.Config;

public class ProductsListActivity extends AppCompatActivity {

    private TextView title;
    private GridView grid;
    private String url;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_products_list);
        Intent intent = getIntent();
        String siloId = intent.getStringExtra("siloId");
        String categoryId = intent.getStringExtra("categoryId");
        String summary = intent.getStringExtra("summary");
        String name = intent.getStringExtra("name");
        url = Config.Category_Second_Kinds+"siloId="+siloId+"&categoryId="+categoryId+"&summary="+summary+"&pageIndex=";
        title = (TextView) findViewById(R.id.title_title_bar_products);
        grid = (GridView) findViewById(R.id.grid_products);
        title.setText(name);

        initData(page);


    }

    private void initData(int page) {
        StringRequest request = new StringRequest(url+page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
}

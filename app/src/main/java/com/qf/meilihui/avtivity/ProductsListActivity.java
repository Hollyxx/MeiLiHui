package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.qf.meilihui.R;

public class ProductsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        Intent intent = getIntent();
        String siloId = intent.getStringExtra("siloId");
        String categoryId = intent.getStringExtra("categoryId");
        String summary = intent.getStringExtra("summary");
    }
}

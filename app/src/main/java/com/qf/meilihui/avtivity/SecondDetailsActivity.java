package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.meilihui.R;

public class SecondDetailsActivity extends AppCompatActivity {

    private ImageView price;
    private TextView  title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second_details);

        Intent intent=getIntent();
        String web=intent.getStringExtra("web");
        String englishName=intent.getStringExtra("englishName");
        title= (TextView) findViewById(R.id.title_title_bar_products);

        title.setText(englishName);

    }
    public   void onClick(View  view){

        switch (view.getId()){

            case R.id.back_title_bar_products:

                finish();

                break;
        }
    }
}

package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.adapter.BasePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private List<ImageView>  data;
    private ViewPager viewpager;
    private RadioGroup  rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_page);
        viewpager= (ViewPager) findViewById(R.id.viewPagerActivity_id);
        rg= (RadioGroup) findViewById(R.id.viewPagerActivity_rg);
        Intent intent = getIntent();
        ArrayList<String> picture = intent.getStringArrayListExtra("picture");

        String  x= intent.getStringExtra("item");
        Log.i("x",x);

        data=new ArrayList<>();

        for (int i=0;i<picture.size();i++){

            ImageView iv= new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(picture.get(i)).into(iv);
            data.add(iv);
            RadioButton radioButton = new RadioButton(this);
            radioButton.setButtonDrawable(R.drawable.radiobutton_selector);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(30, 30));
            radioButton.setPadding(20, 20, 20, 20);
            radioButton.setClickable(false);
            rg.addView(radioButton);
        }
        BasePagerAdapter  adapter=new BasePagerAdapter(data);
        viewpager.setAdapter(adapter);

        int item = Integer.parseInt(x);
        viewpager.setCurrentItem(item);
        ((RadioButton) rg.getChildAt(item)).setChecked(true);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    }
}

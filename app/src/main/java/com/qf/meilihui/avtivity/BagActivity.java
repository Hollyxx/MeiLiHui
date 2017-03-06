package com.qf.meilihui.avtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.qf.meilihui.R;

public class BagActivity extends AppCompatActivity {

    private ListView  listView;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bag);

        listView= (ListView) findViewById(R.id.bag_activity_listview);
        layout= (LinearLayout) findViewById(R.id.bag_linearlayout);

        listView.setEmptyView(layout);

    }
    public void  onClick(View  view){
      switch (view.getId()){
          case R.id.bag_back:
             finish();
              break;
      }
    }
}

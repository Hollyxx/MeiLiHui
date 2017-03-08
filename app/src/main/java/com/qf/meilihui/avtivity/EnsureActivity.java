package com.qf.meilihui.avtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.qf.meilihui.R;

public class EnsureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ensure);

    }
    public   void  onClick(View view){
        switch (view.getId()){

            case R.id.ensure_close:

                finish();
                break;
        }
    }
}

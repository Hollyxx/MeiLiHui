package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.qf.meilihui.R;

public class MailBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mail_box);
    }

    public   void onClick(View view){
        switch (view.getId()){


            case   R.id.mail_title_bar_back:
                finish();
                break;
            case R.id.mail_title_bar_login:
                Intent  intent=new Intent(this,SignActivity.class);

                startActivity(intent);
                finish();
                break;
            case R.id.phone:
                Intent  intent1=new Intent(this,RegisterActivity.class);

                startActivity(intent1);
                finish();
                break;
        }
    }
}

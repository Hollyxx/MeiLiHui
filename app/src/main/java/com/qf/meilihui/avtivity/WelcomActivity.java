package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.qf.meilihui.MainActivity;
import com.qf.meilihui.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcom);
        final Intent it = new Intent(this, MainActivity.class); //下一步转向Mainctivity
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(it);
                finish();
            }
        };
        timer.schedule(task, 1000 * 3); //3秒后跳转
    }
}

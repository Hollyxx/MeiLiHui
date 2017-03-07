package com.qf.meilihui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

import com.qf.meilihui.fragment.BagFragment;
import com.qf.meilihui.fragment.CategoryFragment;
import com.qf.meilihui.fragment.HomeFragment;
import com.qf.meilihui.fragment.MatchFragment;
import com.qf.meilihui.fragment.MeFragment;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment currentFragment;
    private RadioGroup radioGroup;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
        radioGroup.setOnCheckedChangeListener(this);
    }

    public RadioGroup getRadioGroup(){
        return radioGroup;
    }

    private void init() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_main_activity);
        fragmentManager = getSupportFragmentManager();
        radioGroup.getChildAt(0).performClick();
        showFragment(HomeFragment.TAG, HomeFragment.class);
    }

    public void showFragment(String tag, Class<? extends Fragment> className){

        fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        currentFragment = fragmentManager.findFragmentByTag(tag);
        if (currentFragment != null) {

            fragmentTransaction.show(currentFragment);
        } else {
            try {
                currentFragment = className.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            fragmentTransaction.add(R.id.fragment_container_main, currentFragment, tag);
        }
        fragmentTransaction.commit();
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_1:
                showFragment(HomeFragment.TAG,HomeFragment.class);
                break;
            case R.id.rb_2:
                showFragment(CategoryFragment.TAG,CategoryFragment.class);
                break;
            case R.id.rb_3:
                showFragment(MatchFragment.TAG,MatchFragment.class);
                break;
            case R.id.rb_4:
                showFragment(BagFragment.TAG,BagFragment.class);
                break;
            case R.id.rb_5:
                showFragment(MeFragment.TAG,MeFragment.class);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        builder = new AlertDialog.Builder(this);
        alertDialog = builder.create();
        View view = LayoutInflater.from(this).inflate(R.layout.item_back, null, false);
        alertDialog.setView(view);
        if (alertDialog != null) {
            alertDialog.show();
        }
    }
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                if (alertDialog != null) {
                    alertDialog.hide();
                }
                break;
            case R.id.btn2:
                finish();
                break;
            case R.id.btn3:
                //模拟home键返回  实现最小化
//                Intent intent = new Intent(Intent.ACTION_MAIN);//指定跳到系统桌面,*ACTION_MAIN：应用程序入口点
//                intent.addCategory(Intent.CATEGORY_HOME);随系统启动而运行
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);默认的跳转类型,会重新创建一个新的Activity
//                startActivity(intent);
                moveTaskToBack(isFinishing());
                alertDialog.dismiss();
                break;
        }
    }
}

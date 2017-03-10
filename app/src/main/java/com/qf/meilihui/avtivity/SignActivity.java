package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.qf.meilihui.R;

public class SignActivity extends AppCompatActivity {
private EditText  username,password;
    private ImageView  delate,icon;
    private  boolean  flag;
    private CheckBox look;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign);
        username= (EditText) findViewById(R.id.user_name);
        password= (EditText) findViewById(R.id.user_passWard);
        delate= (ImageView) findViewById(R.id.sign_delate);
        look= (CheckBox) findViewById(R.id.sign_look);
        icon= (ImageView) findViewById(R.id.app);
        String name=username.getText().toString();

        if(name==null){
            icon.setVisibility(View.GONE);
        }
        look.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
        look.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(look.isChecked()==false){
                   password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                   look.setText("隐藏");
               }else{
                   password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                   look.setText("显示");
               }
           }
       });

    }
    public  void   onClick(View view){
        switch (view.getId()){
            case R.id.title_bar_new:
                Intent  intent=new Intent(this,RegisterActivity.class);

                startActivity(intent);
                finish();
                break;
            case R.id.back_title_bar:

                finish();
                break;
            case R.id.sign_delate:
                username.setText("");
                delate.setVisibility(View.GONE);
                break;

        }
    }
}

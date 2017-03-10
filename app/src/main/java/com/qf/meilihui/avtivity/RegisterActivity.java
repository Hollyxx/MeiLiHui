package com.qf.meilihui.avtivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qf.meilihui.R;

public class RegisterActivity extends AppCompatActivity {

    private Handler  handler=new Handler();
    private  int time=59;
    private CheckBox  look;
    private String length;
    private TextView  send,password;
    private EditText  phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        look= (CheckBox) findViewById(R.id.register_look);
        send= (TextView) findViewById(R.id.register_Verification);
        password= (TextView) findViewById(R.id.register_password);
        phoneNumber= (EditText) findViewById(R.id.register_phone_number);

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

    public   void onClick(View view ){

        switch (view.getId()){
            case R.id.title_bar_back:
               finish();
                break;
            case R.id.title_bar_login:
                Intent  intent1=new Intent(this,SignActivity.class);

                startActivity(intent1);
                finish();
                break;
            case R.id.mailbox:

                Intent  intent=new Intent(this,MailBoxActivity.class);

                startActivity(intent);
                finish();
                break;
            case R.id.register_Verification:
                length=phoneNumber.getText().toString();
                Toast.makeText(this,length,Toast.LENGTH_LONG).show();
                if(length.length()==11){
                    new  Thread(){
                        @Override
                        public void run() {

                            for (int i=0;i<60;i++){
                                send.setClickable(false);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(time>=1){
                                            send.setText(time+"后重新获取");
                                        }else {
                                            send.setText("重新获取");
                                            time=59;
                                            send.setClickable(true);
                                        }
                                    }
                                });
                                try {
                                    sleep(1000);
                                    time--;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }.start();
                }else{
                    Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}

package com.qf.meilihui.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qf.meilihui.R;

/**
 * Created by invoker on 2017/3/11.
 */

public class CustomDialog extends Dialog {

    private Context context;
    private static CustomDialog dialog;
    private ImageView image;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;

    }

    public static CustomDialog getDialog(Context context){
        dialog = new CustomDialog(context,R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus&&dialog!=null){
            image= (ImageView) dialog.findViewById(R.id.image_dialog_loading);
            AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();
            animationDrawable.start();
        }

    }
}

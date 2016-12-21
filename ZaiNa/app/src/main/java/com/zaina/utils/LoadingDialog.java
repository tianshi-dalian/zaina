package com.zaina.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zaina.R;

/**
 * 自定义dialog
 * CustomDialog
 *
 * @author tianshi
 * @time 2016/12/7 17:57
 */

public class LoadingDialog extends Dialog {


    public LoadingDialog(Context context) {
        this(context, 0);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_02, null);
        Window window = getWindow();
        WindowManager.LayoutParams ly = window.getAttributes();
        ly.gravity = Gravity.CENTER;
        ly.width = getWindow().getWindowManager().getDefaultDisplay().getWidth() / 7;
        ly.height = getWindow().getWindowManager().getDefaultDisplay().getWidth() / 7;
        window.setAttributes(ly);

        //点击外部不可关闭
        this.setCancelable(false);

        setContentView(view);
    }
}

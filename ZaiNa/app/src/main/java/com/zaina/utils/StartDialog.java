package com.zaina.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zaina.R;
import com.zaina.activity.LoginActivity;

/**
 * 自定义dialog
 * CustomDialog
 *
 * @author tianshi
 * @time 2016/12/7 17:57
 */

public class StartDialog extends Dialog {
    private Context context;
    private int windowsWidth;
    private int windowsHeight;


    public StartDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        createDialog();
    }

    public void createDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_04, null);
        this.setContentView(view);
        TextView tv_01 = (TextView) view.findViewById(R.id.tv_01);
        tv_01.setOnClickListener(tv_listens);
        Window windows = getWindow();
        WindowManager.LayoutParams lp = windows.getAttributes();
        windows.setWindowAnimations(R.style.anim_dialog01);
        lp.gravity = Gravity.CENTER;
        lp.width = windows.getWindowManager().getDefaultDisplay().getWidth() / 2;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windows.setAttributes(lp);
    }

    View.OnClickListener tv_listens = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, LoginActivity.class);
            Activity activity = (Activity) context;
            activity.startActivity(intent);
            activity.finish();
            dismiss();

        }
    };

}

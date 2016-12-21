package com.zaina.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zaina.R;

/**
 * 自定义dialog
 * CustomDialog
 *
 * @author tianshi
 * @time 2016/12/7 17:57
 */

public class CustomDialog extends Dialog {
    private Context context;
    private int windowsWidth;
    private int windowsHeight;


    public CustomDialog(Context context) {
        this(context, 0);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_01, null);
        //获取屏幕宽度、高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowsWidth = wm.getDefaultDisplay().getWidth();
        windowsHeight = wm.getDefaultDisplay().getHeight();
        //设置dialog子屏幕中的位置
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setWindowAnimations(R.style.anim_dialog);
        //点击外部不可关闭
        this.setCancelable(false);
        //居下
        lp.gravity = Gravity.BOTTOM;
        //dialog高度
        lp.height = windowsHeight / 3;
        //dialog宽度
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        setContentView(view);
        TextView tv_01 = (TextView) view.findViewById(R.id.tv_01);
        TextView tv_02 = (TextView) view.findViewById(R.id.tv_02);
        TextView tv_03 = (TextView) view.findViewById(R.id.tv_03);
        //拍照
        tv_01.setOnClickListener(tv_01_listener);
        //相册
        tv_02.setOnClickListener(tv_02_listener);
        //取消
        tv_03.setOnClickListener(tv_03_listener);
    }

    View.OnClickListener tv_01_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    View.OnClickListener tv_02_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    View.OnClickListener tv_03_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };
}

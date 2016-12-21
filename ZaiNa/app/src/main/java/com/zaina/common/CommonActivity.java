package com.zaina.common;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.utils.ToastUtil;
import com.zaina.R;
import com.zaina.activity.RegisteredActivity;
import com.zaina.utils.LoadingDialog;


//import android.widget.ImageView;
//
//import com.android_demo.R;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;

/**
 * CommonActivity
 *
 * @author tianshi
 * @time 2016/11/9 13:52
 */

public class CommonActivity extends Activity {
    public static LoadingDialog loading = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading = new LoadingDialog(this, R.style.dialog_01);
    }

    /**
     * 加载图片
     *
     * @param activity
     * @param imageUrl
     * @param imageView
     */
//    public void showImage(Activity activity, String imageUrl, ImageView imageView) {
//        Glide.with(activity)
//                .load(imageUrl)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .dontAnimate()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(imageView);
//
//
//    }

    /**
     * 获取当前时间
     *
     * @return
     */
//    public String getTime() {
//        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
//    }
    public void reTurn(View view) {
        finish();
    }

    /**
     * 网络连接失败，请检查网络
     */
    public Handler errorConnect = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (loading.isShowing()) {
                loading.dismiss();
            }
            ToastUtil.shortToast(CommonActivity.this, "网络连接失败，请检查网络");
        }
    };

}

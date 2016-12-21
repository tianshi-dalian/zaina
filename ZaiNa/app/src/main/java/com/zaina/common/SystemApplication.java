package com.zaina.common;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.zaina.R;
import com.zaina.activity.welcome.WelcomeActivity;
import com.zaina.utils.LoadingDialog;

import adaptation.AbAppConfig;
import cn.smssdk.SMSSDK;
import okhttp3.OkHttpClient;

/**
 * SystemApplication
 *
 * @author tianshi
 * @time 2016/11/9 14:27
 */

public class SystemApplication extends Application {
    public static OkHttpClient okHttpClient;


    @Override
    public void onCreate() {
        super.onCreate();
        //logger初始化
        loggerInit(true);
        //UI适配初始化
        windowsAdapterInit(1080, 1920);
        //OkHttp初始化
        okHttpInit();
        //短信初始化
        SMSSDK.initSDK(this, "19aad2b813828", "1e1359b77ab120313587bfb7d1bdddf7");


    }


    /**
     * log初始化方法
     *
     * @param power 上线时将此变量设置为false便不再打印log，易于安全
     */
    public void loggerInit(boolean power) {
        if (power) {
            //logger初始化
            Logger.init("tag").logLevel(LogLevel.FULL);
        } else {
            //logger初始化
            Logger.init("tag").logLevel(LogLevel.NONE);
        }

    }

    /**
     * 屏幕适配初始化
     * 屏幕适配方法，宽度，高度要与美工设计的手机屏幕尺寸相同
     */
    public void windowsAdapterInit(int width, int height) {
        AbAppConfig.setUI_WIDTH(width);
        AbAppConfig.setUI_HEIGHT(height);
    }

    /**
     * okhttp初始化
     */
    public void okHttpInit() {
        okHttpClient = new OkHttpClient();
    }


    /**
     * 加载图片
     *
     * @param activity
     * @param imageUrl
     * @param imageView
     */
    public static void showImage(Activity activity, String imageUrl, ImageView imageView) {
        Glide.with(activity)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);


    }
}

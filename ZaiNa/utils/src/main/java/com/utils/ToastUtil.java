package com.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * ToastUtil
 *
 * @author tianshi
 * @time 2016/11/7 10:56
 */

public class ToastUtil {
    private static Toast mToast = null;

    /**
     * 自定义时间Toast
     *
     * @param context
     * @param text
     * @param duration
     */
    public static void timeToast(Context context, String text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }

        mToast.show();
    }

    /**
     * 短时间Toast
     *
     * @param context
     * @param text
     */
    public static void shortToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }

        mToast.show();
    }

    /**
     * 长时间Toast
     *
     * @param context
     * @param text
     */
    public static void longToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
        }

        mToast.show();
    }
}

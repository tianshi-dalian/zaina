package com.zaina.activity.welcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.zaina.activity.LoginActivity;
import com.zaina.activity.MainActivty;
import com.zaina.common.CommonActivity;

/**
 * 判断是否是第一次登陆
 * IsFirstActivity
 *
 * @author tianshi
 * @time 2016/12/14 14:44
 */

public class IsFirstActivity extends CommonActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //查看是否是第一次登陆
        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone", "");
        if (!"".equals(phone)) {
            Intent intent = new Intent(this, MainActivty.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        finish();
    }
}

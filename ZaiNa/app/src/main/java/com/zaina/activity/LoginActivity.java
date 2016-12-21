package com.zaina.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.utils.ToastUtil;
import com.zaina.R;
import com.zaina.entity.Usertable;
import com.zaina.common.CommonActivity;
import com.zaina.wsdl.A00S002Wsdl;

import adaptation.AbViewUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zaina.common.Constant.ZERO;

/**
 * 登录页
 * LoginActivity
 *
 * @author tianshi
 * @time 2016/12/5 12:00
 */

public class LoginActivity extends CommonActivity {
    @BindView(R.id.title_tv_01)
    TextView titleTv01;
    @BindView(R.id.tv_03)
    TextView tv03;
    @BindView(R.id.et_01)
    EditText et01;
    @BindView(R.id.et_02)
    EditText et02;
    @BindView(R.id.iv_02)
    ImageView iv02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AbViewUtil.scaleContentView((LinearLayout) findViewById(R.id.rootLayout));

    }

    @OnClick({R.id.title_tv_01, R.id.tv_03, R.id.iv_02})
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 注册点击事件
             */
            case R.id.title_tv_01:
                Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                startActivity(intent);
                break;
            /**
             * 登录点击事件
             */
            case R.id.tv_03:
                if ("".equals(et01.getText().toString().trim())) {
                    ToastUtil.shortToast(LoginActivity.this, "请输入账号");
                    return;
                } else if ("".equals(et02.getText().toString().trim())) {
                    ToastUtil.shortToast(LoginActivity.this, "请输入密码");
                    return;
                } else {
                    loading.show();
                    threadLogin = new LoginThread();
                    threadLogin.start();
                }
                break;
            /**
             * 记住密码
             */
            case R.id.iv_02:
                break;
        }
    }

    public class LoginThread extends Thread {
        @Override
        public void run() {
            try {
                Usertable bean = new Usertable();
                //用户名
                bean.setAccount(et01.getText().toString().trim());
                //密码
                bean.setPassword(et02.getText().toString().trim());
                A00S002Wsdl wsdl = new A00S002Wsdl();
                bean = wsdl.login(bean);
                Message msg = new Message();
                msg.obj = bean;
                loginHandler.sendMessage(msg);
            } catch (Exception e) {
                Logger.e("异常开始", e);
                errorConnect.sendEmptyMessage(0);
            }


        }
    }

    public LoginThread threadLogin;
    Handler loginHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Usertable bean = (Usertable) msg.obj;
            loading.dismiss();
            if (ZERO.equals(bean.getStateCode())) {
                saveShared(et01.getText().toString().trim());
                Intent intent = new Intent(LoginActivity.this, MainActivty.class);
                startActivity(intent);
                finish();
            } else {
                ToastUtil.shortToast(LoginActivity.this, bean.getStateMsg());
            }

        }
    };

    public void saveShared(String phone) {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("phone", phone);
        editor.commit();
    }
}

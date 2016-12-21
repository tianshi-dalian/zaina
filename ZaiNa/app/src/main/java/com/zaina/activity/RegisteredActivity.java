package com.zaina.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.utils.PhoneFormat;
import com.utils.ToastUtil;
import com.zaina.R;
import com.zaina.entity.Usertable;
import com.zaina.common.CommonActivity;
import com.zaina.utils.CircleImageView;
import com.zaina.utils.CustomDialog;
import com.zaina.wsdl.A00S001Wsdl;

import adaptation.AbViewUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

import static com.zaina.common.Constant.ZERO;
/**
 * 短信验证第三方www.mob.com
 * 获取可用国家列表 SMSSDK.getSupportedCountries();
 */

/**
 * 注册页面
 * RegisteredActivity
 *
 * @author tianshi
 * @time 2016/12/5 15:37
 */

public class RegisteredActivity extends CommonActivity {
    //上传头像
    @BindView(R.id.iv_01)
    CircleImageView iv01;
    //获取验证码
    @BindView(R.id.bt_01)
    TextView bt01;
    //注册
    @BindView(R.id.bt_02)
    TextView bt02;
    //同意条款
    @BindView(R.id.iv_02)
    ImageView iv02;
    //账号输入框
    @BindView(R.id.et_01)
    EditText et01;
    //密码输入框
    @BindView(R.id.et_02)
    EditText et02;
    @BindView(R.id.et_03)
    EditText et03;
    //当前倒计时的秒数
    public int seconds;
    //是否点击同意条款
    public boolean clause = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        AbViewUtil.scaleContentView((LinearLayout) findViewById(R.id.rootLayout));


    }

    /**
     * 所有点击事件集合
     *
     * @param view
     */
    @OnClick({R.id.iv_01, R.id.bt_01, R.id.bt_02, R.id.iv_02})
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 添加头像
             */
            case R.id.iv_01:
                new CustomDialog(RegisteredActivity.this, R.style.dialog_01).show();
                break;
            /**
             * 获取验证码按钮
             */
            case R.id.bt_01:
                //注册短信回调
                SMSSDK.registerEventHandler(eh);
                //验证需要发送的电话号格式是否正确
                if (!PhoneFormat.isMobile(et01.getText().toString())) {
                    ToastUtil.shortToast(RegisteredActivity.this, "请确认输入的手机号是否正确");
                } else {

                    //验证码按钮不可点击
                    bt01.setEnabled(false);
                    //将手机号发送给第三方。参数1：区号（例如中国：86），参数2：需要发送的电话号码，参数3：短信发送前的监听，开发者可以在里面处理业务操作
                    SMSSDK.getVerificationCode("86", et01.getText().toString(), onSendMessageHandler);
                    //开启倒计时线程
                    timingThread = new TimingThread();
                    timingThread.start();
                }
                break;
            /**
             * 注册按钮
             */
            case R.id.bt_02:
                //如果没同意
                if ("".equals(et01.getText().toString().trim())) {
                    ToastUtil.shortToast(RegisteredActivity.this, "请输入账号");
                    return;
                } else if ("".equals(et02.getText().toString().trim())) {
                    ToastUtil.shortToast(RegisteredActivity.this, "请输入密码");
                    return;
                } else if ("".equals(et03.getText().toString().trim())) {
                    ToastUtil.shortToast(RegisteredActivity.this, "请填写验证码");
                } else if (!clause) {
                    ToastUtil.shortToast(RegisteredActivity.this, "请先同意条款");
                    return;
                }
                //注册短信回调
                SMSSDK.registerEventHandler(eh);
                loading.show();
                SMSSDK.submitVerificationCode("86", et01.getText().toString().trim(), et03.getText().toString().trim());
                break;
            /**
             * 同意条款按钮
             */
            case R.id.iv_02:
                if (!clause) {
                    iv02.setImageResource(R.mipmap.xuanze_red);
                    clause = true;
                } else {
                    iv02.setImageResource(R.mipmap.xuanze_hui);
                    clause = false;
                }

                break;
        }
    }

    /**
     * 发送短信监听
     */
    EventHandler eh = new EventHandler() {
        //        afterEvent在操作结束时被触发
        @Override
        public void afterEvent(int event, int result, Object data) {
            //回调完成
            if (result == SMSSDK.RESULT_COMPLETE) {
                //提交验证码成功（手机号与验证码校验成功）
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    // TODO: 2016/12/8
                    Usertable bean = new Usertable();
                    //用户名
                    bean.setAccount(et01.getText().toString().trim());
                    //密码
                    bean.setPassword(et02.getText().toString().trim());


                    try {
                        A00S001Wsdl wsdl = new A00S001Wsdl();
                        bean = wsdl.registered(bean);
                        Message msg = new Message();
                        msg.obj = bean;
                        responseHandler.sendMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.e("异常开始", e);
                        errorConnect.sendEmptyMessage(0);
                    }
                    SMSSDK.unregisterEventHandler(eh);
                    //获取验证码成功
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    SMSSDK.unregisterEventHandler(eh);
                    //返回支持发送验证码的国家列表
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    SMSSDK.unregisterEventHandler(eh);
                }
            } else {
                ((Throwable) data).printStackTrace();
                errorHandler.sendEmptyMessage(0);
                SMSSDK.unregisterEventHandler(eh);
            }
        }
    };
    //发送短信前监听
    OnSendMessageHandler onSendMessageHandler = new OnSendMessageHandler() {

        @Override
        public boolean onSendMessage(String s, String s1) {
            return false;
        }
    };

    /**
     * 短信倒计时线程
     */
    public class TimingThread extends Thread {
        @Override
        public void run() {
            try {
                //默认从60s开始倒计时
                seconds = 60;
                while (seconds > 0) {
                    timingHandler.sendEmptyMessage(0);
                    TimingThread.sleep(1000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public TimingThread timingThread;
    /**
     * 短信倒计时handler
     */
    Handler timingHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            bt01.setText(seconds - 1 + "");
            seconds = seconds - 1;
            if (seconds == 0) {
                bt01.setText("获取验证码");
                bt01.setEnabled(true);
            }
        }
    };
    /**
     * 请确认手机号格式是否有误
     */
    Handler errorHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (loading.isShowing()) {
                loading.dismiss();
            }
            ToastUtil.shortToast(RegisteredActivity.this, "请确认手机号格式是否有误");
            bt01.setEnabled(true);
        }
    };
    /**
     * 点击注册按钮从服务器获取数据handler
     */
    Handler responseHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            loading.dismiss();
            Usertable bean = (Usertable) msg.obj;
            if (ZERO.equals(bean.getStateCode())) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisteredActivity.this);
                alertDialog.setCancelable(false);
                alertDialog.setTitle("提示");
                alertDialog.setMessage("注册成功");
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alertDialog.create().show();
            } else {
                ToastUtil.shortToast(RegisteredActivity.this, bean.getStateMsg());
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

}

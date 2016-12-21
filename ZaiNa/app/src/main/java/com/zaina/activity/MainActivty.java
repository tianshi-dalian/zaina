package com.zaina.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zaina.R;
import com.zaina.activity.fragment.Fragment_01;
import com.zaina.activity.fragment.Fragment_02;
import com.zaina.activity.fragment.Fragment_03;
import com.zaina.activity.fragment.Fragment_04;
import com.zaina.activity.services.PositionService;
import com.zaina.common.CommonActivity;

import adaptation.AbViewUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页
 * MainActivty
 *
 * @author tianshi
 * @time 2016/12/5 11:58
 */

public class MainActivty extends CommonActivity {
    @BindView(R.id.iv_01)
    ImageView iv01;
    @BindView(R.id.tv_01)
    TextView tv01;
    @BindView(R.id.iv_02)
    ImageView iv02;
    @BindView(R.id.tv_02)
    TextView tv02;
    @BindView(R.id.iv_03)
    ImageView iv03;
    @BindView(R.id.tv_03)
    TextView tv03;
    @BindView(R.id.iv_04)
    ImageView iv04;
    @BindView(R.id.tv_04)
    TextView tv04;
    Fragment_01 fragment_01;
    Fragment_02 fragment_02;
    Fragment_03 fragment_03;
    Fragment_04 fragment_04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AbViewUtil.scaleContentView((LinearLayout) findViewById(R.id.rootLayout));
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment_01 = new Fragment_01();
        fragmentTransaction.add(R.id.frame_layout_01, fragment_01);
        fragmentTransaction.commit();
        //默认界面样式
        changeStyle(0);
//        开启service实时上传定位
        Intent intent = new Intent(MainActivty.this, PositionService.class);
        startService(intent);
    }

    /**
     * 改变ui样式
     *
     * @param position
     */
    public void changeStyle(int position) {
        switch (position) {
            case 0:
                iv01.setImageResource(R.mipmap.shouye_red);
                tv01.setTextColor(Color.parseColor("#1A6600"));
                iv02.setImageResource(R.mipmap.lianxiren_hui);
                tv02.setTextColor(Color.parseColor("#000000"));
                iv03.setImageResource(R.mipmap.faxian_hui);
                tv03.setTextColor(Color.parseColor("#000000"));
                iv04.setImageResource(R.mipmap.wode_hui);
                tv04.setTextColor(Color.parseColor("#000000"));
                break;
            case 1:
                iv01.setImageResource(R.mipmap.shouye_hui);
                tv01.setTextColor(Color.parseColor("#000000"));
                iv02.setImageResource(R.mipmap.lianxiren_red);
                tv02.setTextColor(Color.parseColor("#1A6600"));
                iv03.setImageResource(R.mipmap.faxian_hui);
                tv03.setTextColor(Color.parseColor("#000000"));
                iv04.setImageResource(R.mipmap.wode_hui);
                tv04.setTextColor(Color.parseColor("#000000"));
                break;
            case 2:
                iv01.setImageResource(R.mipmap.shouye_hui);
                tv01.setTextColor(Color.parseColor("#000000"));
                iv02.setImageResource(R.mipmap.lianxiren_hui);
                tv02.setTextColor(Color.parseColor("#000000"));
                iv03.setImageResource(R.mipmap.faxian_red);
                tv03.setTextColor(Color.parseColor("#1A6600"));
                iv04.setImageResource(R.mipmap.wode_hui);
                tv04.setTextColor(Color.parseColor("#000000"));
                break;
            case 3:
                iv01.setImageResource(R.mipmap.shouye_hui);
                tv01.setTextColor(Color.parseColor("#000000"));
                iv02.setImageResource(R.mipmap.lianxiren_hui);
                tv02.setTextColor(Color.parseColor("#000000"));
                iv03.setImageResource(R.mipmap.faxian_hui);
                tv03.setTextColor(Color.parseColor("#000000"));
                iv04.setImageResource(R.mipmap.wode_red);
                tv04.setTextColor(Color.parseColor("#1A6600"));
                break;
        }


    }


    @OnClick({R.id.ll_01, R.id.ll_02, R.id.ll_03, R.id.ll_04})
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.ll_01:
                changeStyle(0);
                if (null != fragment_01) {
                    if (!fragment_01.isVisible()) {
                        fragmentTransaction.show(fragment_01);
                    }
                } else {
                    fragment_01 = new Fragment_01();
                    fragmentTransaction.add(R.id.frame_layout_01, fragment_01);
                }
                if (null != fragment_02 && fragment_02.isVisible()) {
                    fragmentTransaction.hide(fragment_02);
                }
                if (null != fragment_03 && fragment_03.isVisible()) {
                    fragmentTransaction.hide(fragment_03);
                }
                if (null != fragment_04 && fragment_04.isVisible()) {
                    fragmentTransaction.hide(fragment_04);
                }
                fragmentTransaction.commit();
                break;
            case R.id.ll_02:
                changeStyle(1);
                if (null != fragment_02) {
                    if (!fragment_02.isVisible()) {
                        fragmentTransaction.show(fragment_02);
                    }
                } else {
                    fragment_02 = new Fragment_02();
                    fragmentTransaction.add(R.id.frame_layout_01, fragment_02);
                }
                if (null != fragment_01 && fragment_01.isVisible()) {
                    fragmentTransaction.hide(fragment_01);
                }
                if (null != fragment_03 && fragment_03.isVisible()) {
                    fragmentTransaction.hide(fragment_03);
                }
                if (null != fragment_04 && fragment_04.isVisible()) {
                    fragmentTransaction.hide(fragment_04);
                }
                fragmentTransaction.commit();
                break;
            case R.id.ll_03:
                changeStyle(2);
                if (null != fragment_03) {
                    if (!fragment_03.isVisible()) {
                        fragmentTransaction.show(fragment_03);
                    }
                } else {
                    fragment_03 = new Fragment_03();
                    fragmentTransaction.add(R.id.frame_layout_01, fragment_03);
                }
                if (null != fragment_01 && fragment_01.isVisible()) {
                    fragmentTransaction.hide(fragment_01);
                }
                if (null != fragment_02 && fragment_02.isVisible()) {
                    fragmentTransaction.hide(fragment_02);
                }
                if (null != fragment_04 && fragment_04.isVisible()) {
                    fragmentTransaction.hide(fragment_04);
                }
                fragmentTransaction.commit();
                break;
            case R.id.ll_04:
                changeStyle(3);
                if (null != fragment_04) {
                    if (!fragment_04.isVisible()) {
                        fragmentTransaction.show(fragment_04);
                    }
                } else {
                    fragment_04 = new Fragment_04();
                    fragmentTransaction.add(R.id.frame_layout_01, fragment_04);
                }
                if (null != fragment_01 && fragment_01.isVisible()) {
                    fragmentTransaction.hide(fragment_01);
                }
                if (null != fragment_02 && fragment_02.isVisible()) {
                    fragmentTransaction.hide(fragment_02);
                }
                if (null != fragment_03 && fragment_03.isVisible()) {
                    fragmentTransaction.hide(fragment_03);
                }
                fragmentTransaction.commit();
                break;
        }
    }
}

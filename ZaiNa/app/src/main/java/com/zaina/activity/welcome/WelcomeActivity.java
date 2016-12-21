package com.zaina.activity.welcome;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zaina.R;
import com.zaina.adapter.WelcomePageAdapter;
import com.zaina.common.CommonActivity;
import com.zaina.utils.StartDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;

/**
 * 欢迎页
 * WelcomeActivity
 *
 * @author tianshi
 * @time 2016/12/5 11:59
 */

public class WelcomeActivity extends CommonActivity {
    @BindView(R.id.vp_01)
    ViewPager vp01;
    @BindView(R.id.ll_01)
    LinearLayout ll01;
    //存储轮播图
    int[] imglist = new int[]{R.drawable.welcome_01, R.drawable.welcome_02, R.drawable.welcome_03};
    //存储圆点
    List<ImageView> list = new ArrayList<>();
    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        //初始化
        init();
        WelcomePageAdapter adapter = new WelcomePageAdapter(this, imglist);
        vp01.setAdapter(adapter);
        vp01.addOnPageChangeListener(vp_listen);
    }


    /**
     * 圆点初始化
     */
    public void init() {
        //添加圆点
        for (int i = 0; i < imglist.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.yuandian_img);
            list.add(imageView);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10, 0, 10, 0);
            ll01.addView(imageView, lp);

        }
        list.get(0).setImageResource(R.mipmap.yuandian_hui);
    }

    /**
     * viewpage滑动监听
     */
    ViewPager.OnPageChangeListener vp_listen = new ViewPager.OnPageChangeListener() {
        //position:当前点击的画面，positionOffset:当前滑动的百分比，positionOffsetPixels：当前页面偏移的像素位置
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //页面滑动完成后调用
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < list.size(); i++) {
                if (i == position) {
                    list.get(i).setImageResource(R.mipmap.yuandian_hui);
                } else {
                    list.get(i).setImageResource(R.mipmap.yuandian_img);
                }
            }
            //是否是最后一张图片
            if (position == list.size() - 1) {
                //弹出diaglog
                StartDialog dialog = new StartDialog(WelcomeActivity.this, R.style.dialog_01);
                dialog.show();
            }


        }

        // 0什么都没做，1正在滑动 2滑动完毕
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}

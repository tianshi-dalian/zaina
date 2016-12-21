package com.zaina.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zaina.R;
import com.zaina.activity.BaiduMapActivity;
import com.zaina.common.SystemApplication;
import com.zaina.entity.VFriendsAndtel;

import java.util.ArrayList;
import java.util.List;

import adaptation.AbViewUtil;

/**
 * 首页圆形好友图片适配器
 * ViewPageAdapter
 *
 * @author tianshi
 * @time 2016/12/9 17:54
 */

public class WelcomePageAdapter extends PagerAdapter {
    private Activity mActivity;
    private LayoutInflater mInflater;
    private int[] mData;
    private List<View> viewList;

    public WelcomePageAdapter(Activity mActivity, int[] mData) {
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
        this.mData = mData;
        viewList = new ArrayList<View>();
        for (int i = 0; i < mData.length; i++) {
            viewList.add(mActivity.getLayoutInflater().inflate(R.layout.item_welcome_viewpage, null));
        }
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        AbViewUtil.scaleContentView((LinearLayout) viewList.get(position).findViewById(R.id.rootLayout));
        View view = viewList.get(position);
        ImageView iv_01 = (ImageView) view.findViewById(R.id.iv_01);
        iv_01.setBackgroundResource(mData[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}

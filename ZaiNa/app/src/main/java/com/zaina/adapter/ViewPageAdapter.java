package com.zaina.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.zaina.entity.Friendstable;
import com.zaina.entity.Rotationmap;
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

public class ViewPageAdapter extends PagerAdapter {
    private Activity mActivity;
    private LayoutInflater mInflater;
    private List<Rotationmap> mData;
    private List<View> viewList;
    private ViewPager vp_01;

    public ViewPageAdapter(Activity mActivity, List<Rotationmap> mData) {
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
        this.mData = mData;
        viewList = new ArrayList<View>();
        for (int i = 0; i < mData.size(); i++) {
            viewList.add(mActivity.getLayoutInflater().inflate(R.layout.item_viewpage, null));
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = viewList.get(position);
        ImageView iv_01 = (ImageView) view.findViewById(R.id.iv_01);
        SystemApplication.showImage(mActivity, mData.get(position).getImageurl(), iv_01);
        container.addView(viewList.get(position));
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}

package com.zaina.activity.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;
import com.zaina.R;
import com.zaina.adapter.ViewPageAdapter;
import com.zaina.bean.ExtRotationmap;
import com.zaina.common.CommonFragment;
import com.zaina.wsdl.A00S003Wsdl;

import adaptation.AbViewUtil;

import static com.zaina.common.Constant.ZERO;

/**
 * 首页Fragment
 * Fragment_01
 *
 * @author tianshi
 * @time 2016/12/9 15:21
 */

public class Fragment_03 extends CommonFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_03, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}

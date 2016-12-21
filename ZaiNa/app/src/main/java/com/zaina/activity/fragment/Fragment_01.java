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

public class Fragment_01 extends CommonFragment {
    private String telPhone;
    private View view;
    private ViewPager vp_01;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_01, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AbViewUtil.scaleContentView((LinearLayout) view.findViewById(R.id.rootLayout));
        vp_01 = (ViewPager) view.findViewById(R.id.vp_01);
        loading.show();
        threadRotationmapList = new RotationmapListThread();
        threadRotationmapList.start();
    }

    /**
     * 获取首页轮播图线程
     */
    public class RotationmapListThread extends Thread {
        @Override
        public void run() {
            try {
                ExtRotationmap bean = rotationmapList();
                Message message = new Message();
                message.obj = bean;
                rotationmapListHandler.sendMessage(message);
            } catch (Exception e) {
                Logger.e("异常开始", e);
                errorConnect.sendEmptyMessage(0);
            }
        }
    }

    public RotationmapListThread threadRotationmapList;

    /**
     * 获取首页轮播图Handler
     */
    Handler rotationmapListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ExtRotationmap extRotationmap = (ExtRotationmap) msg.obj;
            if (ZERO.equals(extRotationmap.getStateCode())) {
                ViewPageAdapter adapter = new ViewPageAdapter(getActivity(), extRotationmap.getList());
                vp_01.setAdapter(adapter);

            }
            loading.dismiss();
        }
    };

    /**
     * 获取首页轮播图方法
     *
     * @return
     * @throws Exception
     */
    public ExtRotationmap rotationmapList() throws Exception {
        A00S003Wsdl wsdl = new A00S003Wsdl();
        ExtRotationmap extRotationmap = new ExtRotationmap();
        return wsdl.selectRotationmap(extRotationmap);
    }

}

package com.zaina.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zaina.R;
import com.zaina.common.CommonFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页Fragment
 * Fragment_01
 *
 * @author tianshi
 * @time 2016/12/9 15:21
 */

public class Fragment_04 extends CommonFragment {
    @BindView(R.id.tv_01)
    TextView tv01;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_04, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}

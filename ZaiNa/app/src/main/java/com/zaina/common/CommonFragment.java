package com.zaina.common;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.utils.ToastUtil;
import com.zaina.R;
import com.zaina.utils.LoadingDialog;


/**
 * CommonFragment
 *
 * @author tianshi
 * @time 2016/12/9 16:53
 */

public class CommonFragment extends Fragment {
    public static LoadingDialog loading = null;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loading = new LoadingDialog(getActivity(), R.style.dialog_01);
    }

    /**
     * 网络连接失败，请检查网络
     */
    public Handler errorConnect = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (loading.isShowing()) {
                loading.dismiss();
            }
            ToastUtil.shortToast(getActivity(), "网络连接失败，请检查网络");
        }
    };

}

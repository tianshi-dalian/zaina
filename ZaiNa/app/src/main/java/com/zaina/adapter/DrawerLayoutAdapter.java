package com.zaina.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zaina.R;

import java.util.List;

import adaptation.AbViewUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 左侧侧滑菜单适配器
 * DrawerLayoutAdapter
 *
 * @author tianshi
 * @time 2016/12/9 14:25
 */

public class DrawerLayoutAdapter extends BaseAdapter {
    private Activity mActivity;
    private LayoutInflater mInflater;
    private List<String> mData;
    private ListView mListView;

    public DrawerLayoutAdapter(Activity mActivity, ListView mListView, List<String> mData) {
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
        this.mData = mData;
        mData = mData;
        this.mListView = mListView;
    }

    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_drawerlayout, null);
            viewHolder = new ViewHolder(convertView);
            // 调整每个Item占屏幕的百分比
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mListView.getHeight() / 5);
            convertView.setLayoutParams(lp);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv01.setText(mData.get(position));

        return convertView;
    }


    /**
     * 优化机制
     */
    static class ViewHolder {
        @BindView(R.id.tv_01)
        TextView tv01;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            AbViewUtil.scaleContentView((LinearLayout) view.findViewById(R.id.rootLayout));

        }

    }
}

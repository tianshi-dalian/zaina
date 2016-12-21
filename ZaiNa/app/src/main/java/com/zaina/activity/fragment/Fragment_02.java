package com.zaina.activity.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.zaina.R;
import com.zaina.activity.BaiduMapActivity;
import com.zaina.bean.ExtFriendstable;
import com.zaina.common.CommonFragment;
import com.zaina.entity.VFriendsAndtel;
import com.zaina.utils.IndexListview.CharacterParser;
import com.zaina.utils.IndexListview.ClearEditText;
import com.zaina.utils.IndexListview.PinyinComparator;
import com.zaina.utils.IndexListview.SideBar;
import com.zaina.utils.IndexListview.SortAdapter;
import com.zaina.utils.IndexListview.SortModel;
import com.zaina.wsdl.A00S003Wsdl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adaptation.AbViewUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zaina.common.Constant.ZERO;

/**
 * 联系人
 * Fragment_02
 *
 * @author tianshi
 * @time 2016/12/16 10:06
 */

public class Fragment_02 extends CommonFragment {
    @BindView(R.id.filter_edit)
    ClearEditText filterEdit;
    @BindView(R.id.country_lvcountry)
    ListView countryLvcountry;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidrbar)
    SideBar sidrbar;
    //视图
    private View view;
    private SortAdapter adapter;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    List<VFriendsAndtel> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_02, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AbViewUtil.scaleContentView((LinearLayout) view.findViewById(R.id.rootLayout));
        initViews();
        loading.show();
        threadFriendsList = new FriendsListThread();
        threadFriendsList.start();

    }

    public ExtFriendstable friendsList() throws Exception {
        A00S003Wsdl wsdl = new A00S003Wsdl();
        ExtFriendstable bean = new ExtFriendstable();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_APPEND);
        bean.setTelPhone(sharedPreferences.getString("phone", ""));
        return wsdl.friendsList(bean);
    }

    /**
     * 获取好友列表
     */
    public class FriendsListThread extends Thread {
        @Override
        public void run() {
            try {
                Message message = new Message();
                message.obj = friendsList();
                friendsListHandler.sendMessage(message);
            } catch (Exception e) {
                Logger.e("start", e);
                errorConnect.sendEmptyMessage(0);
            }

        }
    }

    public FriendsListThread threadFriendsList;

    /**
     * 处理获取到的好友列表数据
     */
    Handler friendsListHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ExtFriendstable bean = (ExtFriendstable) msg.obj;
            if (ZERO.equals(bean.getStateCode())) {
                list = filledData(bean.getFriendList());
                // 根据a-z进行排序源数据
                Collections.sort(list, pinyinComparator);
                adapter = new SortAdapter(getActivity(), list);
                countryLvcountry.setAdapter(adapter);
            }
            loading.dismiss();
        }
    };

    private void initViews() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        sidrbar.setTextView(dialog);
        //设置右侧触摸监听
        sidrbar.setOnTouchingLetterChangedListener(touchingLetterChangedListener);
        //listview点击事件
        countryLvcountry.setOnItemClickListener(itemClickListener);
        //根据输入框输入值的改变来过滤搜索
        filterEdit.addTextChangedListener(textWatcher);
    }

    /**
     * 为ListView填充数据
     *
     * @param list
     * @return
     */
    private List<VFriendsAndtel> filledData(List<VFriendsAndtel> list) {
        for (int i = 0; i < list.size(); i++) {
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(list.get(i).getUsername());
            //存储全拼
            list.get(i).setQuanpin(pinyin.toUpperCase());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                list.get(i).setUserNameLetter(sortString.toUpperCase());
            } else {
                list.get(i).setUserNameLetter("#");
            }
        }
        return list;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<VFriendsAndtel> filterDateList = new ArrayList<VFriendsAndtel>();
        filterStr = filterStr.toUpperCase();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = list;

        } else {
            filterDateList.clear();

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUsername().indexOf(filterStr) != -1 || list.get(i).getQuanpin().indexOf(filterStr) != -1) {
                    filterDateList.add(list.get(i));
                }
//            filterDateList.clear();
//            for (SortModel sortModel : SourceDateList) {
//                String name = sortModel.getName();
//                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
//                    filterDateList.add(sortModel);
//                }
//            }
            }
            // 根据a-z进行排序
//        Collections.sort(filterDateList, pinyinComparator);
        }
        adapter.updateListView(filterDateList);
    }

    /**
     * 设置右侧监听事件
     */
    SideBar.OnTouchingLetterChangedListener touchingLetterChangedListener = new SideBar.OnTouchingLetterChangedListener() {

        @Override
        public void onTouchingLetterChanged(String s) {
            //该字母首次出现的位置
            int position = adapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                countryLvcountry.setSelection(position);
            }
        }
    };
    /**
     * listview点击事件
     */
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Intent intent = new Intent(getActivity(), BaiduMapActivity.class);
            intent.putExtra("uuid", ((VFriendsAndtel) adapter.getItem(position)).getFrienduuid());
            startActivity(intent);
        }
    };
    /**
     * edittext输入框监听
     */
    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
            filterData(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}

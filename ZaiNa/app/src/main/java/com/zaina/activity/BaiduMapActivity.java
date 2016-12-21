package com.zaina.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.orhanobut.logger.Logger;
import com.utils.ToastUtil;
import com.zaina.R;
import com.zaina.bean.ExtPositiontable;
import com.zaina.common.CommonActivity;
import com.zaina.entity.Positiontable;
import com.zaina.wsdl.A00S003Wsdl;

import adaptation.AbViewUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zaina.common.Constant.ZERO;

/**
 * Created by Administrator on 2016/12/12.
 */

public class BaiduMapActivity extends CommonActivity {
    @BindView(R.id.bmapView)
    MapView bmapView;
    @BindView(R.id.tv_01)
    TextView tv01;
    private BaiduMap mBaiduMap;
    //定位人电话号
    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        AbViewUtil.scaleContentView((LinearLayout) findViewById(R.id.rootLayout));
        ButterKnife.bind(this);
        uuid = getIntent().getStringExtra("uuid");
        tv01.setText("查看位置");
        mBaiduMap = bmapView.getMap();

    }

    @Override
    protected void onStart() {
        super.onStart();
        loading.show();
        threadConnectServer = new ConnectServerThread();
        threadConnectServer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        bmapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        bmapView.onPause();
    }

    /**
     * 获取数据线程
     */
    public class ConnectServerThread extends Thread {
        @Override
        public void run() {
            try {
                ExtPositiontable bean = connectServer();
                Message msg = new Message();
                msg.obj = bean;
                connectServerHandler.sendMessage(msg);

            } catch (Exception e) {
                Logger.e("异常开始", e);
                errorConnect.sendEmptyMessage(0);

            }

        }
    }

    public ConnectServerThread threadConnectServer;

    /**
     * 请求数据方法
     *
     * @return
     */
    public ExtPositiontable connectServer() throws Exception {
        ExtPositiontable bean = new ExtPositiontable();
        bean.setUuid(uuid);
        A00S003Wsdl wsdl = new A00S003Wsdl();
        bean = wsdl.selectPosition(bean);
        return bean;

    }

    Handler connectServerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ExtPositiontable bean = (ExtPositiontable) msg.obj;
            if (ZERO.equals(bean.getStateCode())) {
//                //添加地图标注
//                //定义Maker坐标点
//                LatLng point = new LatLng(Double.valueOf(bean.getLatitude()), Double.valueOf(bean.getLongitude()));
//                //构建Marker图标
//                BitmapDescriptor bitmap = BitmapDescriptorFactory
//                        .fromResource(R.mipmap.dian);
//                //构建MarkerOption，用于在地图上添加Marker
//                OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
//                //在地图上添加Marker，并显示
//                mBaiduMap.addOverlay(option);
                // 开启定位图层
                mBaiduMap.setMyLocationEnabled(true);
                MyLocationData locData = new MyLocationData.Builder().accuracy(0).direction(100).latitude(Double.valueOf(bean.getLatitude()))
                        .longitude(Double.valueOf(bean.getLongitude())).build();
                mBaiduMap.setMyLocationData(locData);
                LatLng latLng = new LatLng(Double.valueOf(bean.getLatitude()), Double.valueOf(bean.getLongitude()));
                //定义地图状态
                MapStatus mMapStatus = new MapStatus.Builder()
                        .target(latLng)
                        .zoom(19)
                        .build();
//定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//改变地图状态
                mBaiduMap.setMapStatus(mMapStatusUpdate);
            } else {
                ToastUtil.shortToast(BaiduMapActivity.this, bean.getStateMsg());
            }
            loading.dismiss();

        }
    };

}

package com.zaina.activity.services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.orhanobut.logger.Logger;
import com.zaina.bean.ExtPositiontable;
import com.zaina.wsdl.A00S003Wsdl;

/**
 * 获取实时位置sercice
 * PositionService
 *
 * @author tianshi
 * @time 2016/12/12 13:58
 */

public class PositionService extends Service {
    //    private PositionBind positionBind = new PositionBind();
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean isPosition = true;
    //返回结果
    private BDLocation bdLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        //百度地图初始化
        initLocation();
        mLocationClient.start();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //    public class PositionBind extends Binder {
//        public void initPosition() {
//
//        }
//    }

    /**
     * 连接服务器方法
     *
     * @return
     * @throws Exception
     */
    public ExtPositiontable connectionService() throws Exception {
        SharedPreferences mySharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        ExtPositiontable bean = new ExtPositiontable();
        //纬度
        bean.setLatitude(String.valueOf(bdLocation.getLatitude()));
        //经度
        bean.setLongitude(String.valueOf(bdLocation.getLongitude()));
        bean.setTelPhone(mySharedPreferences.getString("phone", ""));
        A00S003Wsdl wsdl = new A00S003Wsdl();
        bean = wsdl.insertPosition(bean);
        return bean;

    }

    /**
     * 初始化百度定位参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationMode.Hight_Accuracy);
        option.setCoorType("BD09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            bdLocation = location;
            //如果出现异常{}
            if (bdLocation.getLocType() == BDLocation.TypeServerError || location.getLocType() == BDLocation.TypeNetWorkException || location.getLocType() == BDLocation.TypeCriteriaException) {
                mLocationClient.stop();
                mLocationClient.start();

            } else {
                threadPosition = new PositionThread();
                threadPosition.start();
            }
            Logger.i("tag", location.getAddrStr().toString());

        }
    }

    public class PositionThread extends Thread {
        @Override
        public void run() {
            try {
                ExtPositiontable bean = connectionService();
                mLocationClient.stop();
                PositionThread.sleep(20000);
                mLocationClient.start();

            } catch (Exception e) {
                Logger.e("异常开始", e);
                mLocationClient.stop();
                mLocationClient.start();
            }


        }
    }

    public PositionThread threadPosition;


}

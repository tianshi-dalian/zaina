package com.zaina.common;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static com.zaina.common.SystemApplication.okHttpClient;


/**
 * CommonWsdl
 *
 * @author tianshi
 * @time 2016/11/9 13:57
 */

public abstract class CommonWsdl {
    //    public static String IP = "http://192.168.31.222:8080/zaina_wsdl/";
    public static String IP = "http:/60.205.205.149:80/zaina/";
    //命名空间
    private String nameSpace;
    //返回结果集
    private Response response;

    /**
     * post请求
     *
     * @param request 传入bean
     * @param <T>
     * @return
     * @throws IOException
     */
//    public <T> T getResponse(T request) throws IOException {
//        String json = JSON.toJSONString(request);
//        FormBody.Builder builder = new FormBody.Builder();
//        //添加参数
//        builder.add("request", json);
//        FormBody body = builder.build();
//        String url = IP + nameSpace;
//        Request sendRequest = new Request.Builder().url(url).post(body).build();
//        response = okHttpClient.newCall(sendRequest).execute();
//        String result = response.body().string();
//        Logger.json(result);
//        T response = (T) JSON.parseObject(result, request.getClass());
//        return response;
//    }

    /**
     * get请求
     */
    public <T> T getResponse(T request) throws Exception {
        String json = JSON.toJSONString(request);
        //设置连接超时
        okHttpClient.newBuilder().connectTimeout(3000, TimeUnit.MILLISECONDS).build();
        //读取超时
        okHttpClient.newBuilder().readTimeout(10000, TimeUnit.MILLISECONDS).build();
        //写入超时
//        okHttpClient.newBuilder().writeTimeout(3000, TimeUnit.MILLISECONDS).build();
        //url:请求地址
        Request sendRequest = new Request.Builder().url(IP + nameSpace + "?request=" + json).build();
        Call call = okHttpClient.newCall(sendRequest);
        //访问后台并获得返回值
        response = call.execute();
        //成功
        //注意：response.body().string();只可以调用一次，断点调试时不要将他鼠标右键 add to wahches
        String result = response.body().string();
        Logger.json(result);
        T response = (T) JSON.parseObject(result, request.getClass());
        if (null == response) {
            throw new Exception();
        }

        return response;

    }

    /**
     * 设置命名空间
     *
     * @param nameSpace
     */
    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public static void setIP(String IP) {
        CommonWsdl.IP = IP;
    }
    /**
     * 网络连接失败
     */

}

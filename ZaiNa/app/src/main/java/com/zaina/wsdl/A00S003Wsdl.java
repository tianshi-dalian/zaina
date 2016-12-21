package com.zaina.wsdl;

import com.zaina.bean.ExtFriendstable;
import com.zaina.bean.ExtPositiontable;
import com.zaina.bean.ExtRotationmap;
import com.zaina.common.CommonWsdl;
import com.zaina.entity.Positiontable;
import com.zaina.entity.Usertable;

import java.io.IOException;

/**
 * A00S003Wsdl
 *
 * @author tianshi
 * @time 2016/12/8 15:01
 */

public class A00S003Wsdl extends CommonWsdl {
    /**
     * 获取首页信息
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public ExtFriendstable friendsList(ExtFriendstable bean) throws Exception {
        super.setNameSpace("A00S003/friendsList");
        return super.getResponse(bean);
    }

    /**
     * 上传当前位置
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public ExtPositiontable insertPosition(ExtPositiontable bean) throws Exception {
        super.setNameSpace("A00S003/insertPosition");
        return super.getResponse(bean);
    }

    /**
     * 根据电话号获取最后一次的位置
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public ExtPositiontable selectPosition(ExtPositiontable bean) throws Exception {
        super.setNameSpace("A00S003/selectPosition");
        return super.getResponse(bean);
    }

    /**
     * 获取首页轮播图
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public ExtRotationmap selectRotationmap(ExtRotationmap bean) throws Exception {
        super.setNameSpace("A00S003/selectRotationmap");
        return super.getResponse(bean);
    }
}

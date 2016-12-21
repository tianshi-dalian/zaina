package com.zaina.wsdl;

import com.zaina.entity.Usertable;
import com.zaina.common.CommonWsdl;

import java.io.IOException;

/**
 * A00S001Wsdl
 *
 * @author tianshi
 * @time 2016/12/8 15:01
 */

public class A00S001Wsdl extends CommonWsdl {
    /**
     * 注册用户
     *
     * @param bean
     * @return
     * @throws IOException
     */
    public Usertable registered(Usertable bean) throws Exception {
        super.setNameSpace("A00S001/registered");
        return super.getResponse(bean);
    }
}

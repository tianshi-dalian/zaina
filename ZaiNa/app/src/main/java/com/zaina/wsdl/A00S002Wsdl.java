package com.zaina.wsdl;

import com.zaina.entity.Usertable;
import com.zaina.common.CommonWsdl;

import java.io.IOException;

/**
 * A00S002Wsdl
 *
 * @author tianshi
 * @time 2016/12/8 15:01
 */

public class A00S002Wsdl extends CommonWsdl {
    /**
     * 登录
     *
     * @param bean
     * @return
     * @throws IOException
     */
    public Usertable login(Usertable bean) throws Exception {
        super.setNameSpace("A00S002/login");
        return super.getResponse(bean);
    }
}

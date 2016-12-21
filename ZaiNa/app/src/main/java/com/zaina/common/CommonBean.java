package com.zaina.common;

/**
 * CommonBean
 *
 * @author tianshi
 * @time 2016/11/9 13:59
 */

public class CommonBean {
    /**
     * 0:成功,1失败
     */
    private String stateCode;
    /**
     * 消息
     */
    private String stateMsg;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateMsg() {
        return stateMsg;
    }

    public void setStateMsg(String stateMsg) {
        this.stateMsg = stateMsg;
    }
}

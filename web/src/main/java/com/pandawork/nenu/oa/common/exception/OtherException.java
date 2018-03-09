package com.pandawork.nenu.oa.common.exception;

import com.pandawork.core.common.exception.IBizExceptionMes;

/**
 * OtherException
 * 动态创建异常信息类
 *
 * @author wlm
 * @date 2016/5/21 19:03
 */
public class OtherException implements IBizExceptionMes {

    private String errMsg;
    private int code;

    public OtherException(String errMsg, int code) {
        this.errMsg = errMsg;
        this.code = code;
    }

    @Override
    public String getMes() {
        return this.errMsg;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}

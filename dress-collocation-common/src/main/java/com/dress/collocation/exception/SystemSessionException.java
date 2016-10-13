package com.dress.collocation.exception;

/**
 * Description: 用户不存在异常
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/13
 * Time: 14:03
 * Version: 1.0
 **/
public class SystemSessionException extends SystemException {

    public SystemSessionException() {
        super("当前登录用户不存在");
    }

}

package com.dress.collocation.exception;

/**
 * Description: 业务异常
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/13
 * Time: 14:03
 * Version: 1.0
 **/
public class SystemBizException extends SystemException {
    public SystemBizException() {
        super();
    }

    public SystemBizException(String message) {
        super(message);
    }

    public SystemBizException(String message, Throwable cause) {
        super(message, cause);
    }


}

package com.dress.collocation.exception;

/**
 * Description:基础异常
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/13
 * Time: 13:54
 * Version: 1.0
 **/
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 174841398690789156L;

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }


    public String toString(){
        Throwable cause = super.getCause();
        if(cause == null || cause == this){
            return super.toString();
        }else{
            return super.toString() + " [See nested exception: " + cause + "]";
        }
    }
}

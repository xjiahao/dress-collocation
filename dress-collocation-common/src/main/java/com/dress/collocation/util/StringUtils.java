package com.dress.collocation.util;

/**
 * Description:
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/12
 * Time: 11:43
 * Version: 1.0
 **/
public class StringUtils {

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(Object str) {
        return (str != null && !"".equals(str));
    }
}

package com.dress.collocation.util;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Description:
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/28
 * Time: 22:58
 * Version: 1.0
 **/
public class Base64Utils {

    public static final Logger LOGGER = Logger.getLogger(Base64Utils.class);

    /**
     * 编码
     * @param bstr bstr
     * @return
     */
    public static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * 解码
     * @param str str
     * @return
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            LOGGER.error("解码失败",e);
        }
        return bt;
    }
}

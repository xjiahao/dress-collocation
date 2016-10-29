package com.dress.collocation.util;

import java.security.MessageDigest;

/**
 * Description:
 * Created by xuejiahao on 2016/10/24.
 */
public class Md5Utils {

    public static String MD5(String plainText, String key) {
        return MD5(plainText + key);
    }

    public static String MD5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            //生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组更新摘要。
            md.update(plainText.getBytes());
            //通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            //生成具体的md5密码到buf数组
            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5("111111","441542820@qq.com"));
    }

}

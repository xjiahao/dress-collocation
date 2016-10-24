package com.dress.collocation.util;

import java.util.Random;

/**
 * Description:
 * Created by xuejiahao on 2016/10/23.
 */
public class VerificationCodeUtils {

    /**
     * 构造6位数字验证码
     * @return
     */
    public static String buildVerificationCode(){
        Random random = new Random();
        StringBuilder verificationCode = new StringBuilder();
        for(int i = 0 ; i < 6 ; i++){
            verificationCode.append(random.nextInt(10));
        }
        return verificationCode.toString();
    }
}

package com.dress.collocation.vo;

import com.dress.collocation.constants.VerificationCodeType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/28
 * Time: 22:02
 * Version: 1.0
 **/
public class GetVerificationCodeVo {

    @NotEmpty
    private String userName;
    @NotNull
    private VerificationCodeType verificationCodeType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public VerificationCodeType getVerificationCodeType() {
        return verificationCodeType;
    }

    public void setVerificationCodeType(VerificationCodeType verificationCodeType) {
        this.verificationCodeType = verificationCodeType;
    }
}

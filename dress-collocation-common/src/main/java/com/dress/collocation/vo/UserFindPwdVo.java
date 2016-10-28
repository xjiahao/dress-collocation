package com.dress.collocation.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Description:找回密码vo
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/28
 * Time: 22:35
 * Version: 1.0
 **/
public class UserFindPwdVo {

    @NotEmpty
    private String userName;
    @NotEmpty
    private String verificationCode;
    @NotEmpty
    private String newPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
